/*
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.ai.example.deepresearch.config;

import com.alibaba.cloud.ai.example.deepresearch.config.rag.RagProperties;
import com.alibaba.cloud.ai.example.deepresearch.dispatcher.*;
import com.alibaba.cloud.ai.example.deepresearch.memory.ShortTermMemoryRepository;
import com.alibaba.cloud.ai.example.deepresearch.model.enums.ParallelEnum;

import com.alibaba.cloud.ai.example.deepresearch.node.*;
import com.alibaba.cloud.ai.example.deepresearch.service.RagNodeService;
import com.alibaba.cloud.ai.example.deepresearch.service.SessionContextService;
import com.alibaba.cloud.ai.example.deepresearch.service.multiagent.QuestionClassifierService;
import com.alibaba.cloud.ai.example.deepresearch.service.ReportService;
import com.alibaba.cloud.ai.example.deepresearch.service.multiagent.SearchPlatformSelectionService;
import com.alibaba.cloud.ai.example.deepresearch.service.multiagent.SmartAgentDispatcherService;

import com.alibaba.cloud.ai.example.deepresearch.serializer.DeepResearchStateSerializer;
import com.alibaba.cloud.ai.example.deepresearch.service.InfoCheckService;
import com.alibaba.cloud.ai.example.deepresearch.service.SearchFilterService;
import com.alibaba.cloud.ai.example.deepresearch.service.multiagent.ToolCallingSearchService;
import com.alibaba.cloud.ai.example.deepresearch.util.ReflectionProcessor;
import com.alibaba.cloud.ai.graph.GraphRepresentation;
import com.alibaba.cloud.ai.graph.KeyStrategy;
import com.alibaba.cloud.ai.graph.KeyStrategyFactory;
import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.StateGraph;
import com.alibaba.cloud.ai.graph.exception.GraphStateException;
import com.alibaba.cloud.ai.graph.state.strategy.ReplaceStrategy;
import com.alibaba.cloud.ai.toolcalling.jinacrawler.JinaCrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.cloud.ai.graph.StateGraph.END;
import static com.alibaba.cloud.ai.graph.StateGraph.START;
import static com.alibaba.cloud.ai.graph.action.AsyncEdgeAction.edge_async;
import static com.alibaba.cloud.ai.graph.action.AsyncNodeAction.node_async;

import com.alibaba.cloud.ai.example.deepresearch.service.McpProviderFactory;

/**
 * @author yingzi
 * @since 2025/5/17 17:10
 */
@Configuration
@EnableConfigurationProperties({ DeepResearchProperties.class, PythonCoderProperties.class,
		McpAssignNodeProperties.class, RagProperties.class, ReflectionProperties.class, SmartAgentProperties.class,
		ShortTermMemoryProperties.class })
public class DeepResearchConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(DeepResearchConfiguration.class);

	@Autowired
	private ChatClient coderAgent;

	@Autowired
	private ChatClient researchAgent;

	@Autowired
	private ChatClient reporterAgent;

	@Autowired
	private ChatClient backgroundAgent;

	@Autowired
	private ChatClient coordinatorAgent;

	@Autowired
	private ChatClient plannerAgent;

	@Autowired
	private ChatClient reflectionAgent;

	@Autowired
	private ChatClient shortMemoryAgent;

	@Autowired
	private ChatClient.Builder rewriteAndMultiQueryChatClientBuilder;

	@Autowired
	private DeepResearchProperties deepResearchProperties;

	@Autowired
	private ReflectionProperties reflectionProperties;

	@Autowired
	private ShortTermMemoryProperties shortTermMemoryProperties;

	@Autowired
	private ShortTermMemoryRepository shortTermMemoryRepository;

	@Autowired(required = false)
	private MessageWindowChatMemory messageWindowChatMemory;

	@Autowired(required = false)
	private JinaCrawlerService jinaCrawlerService;

	@Autowired
	private RagProperties ragProperties;

	@Autowired
	private ReportService reportService;

	@Autowired
	private SessionContextService sessionContextService;

	@Autowired(required = false)
	private McpProviderFactory mcpProviderFactory;

	@Autowired
	private InfoCheckService infoCheckService;

	@Autowired
	private SearchFilterService searchFilterService;

	// 可选的工具调用服务（智能平台用）
	@Autowired(required = false)
	private ToolCallingSearchService toolCallingSearchService;

	@Autowired(required = false)
	private QuestionClassifierService questionClassifierService;

	@Autowired(required = false)
	private SearchPlatformSelectionService searchPlatformSelectionService;

	@Autowired(required = false)
	private SmartAgentDispatcherService smartAgentDispatcher;

	@Autowired
	private SmartAgentProperties smartAgentProperties;

	@Autowired
	private RagNodeService ragNodeService;

	@Bean
	public ReflectionProcessor reflectionProcessor() {
		if (!reflectionProperties.isEnabled()) {
			return null; // Return null if reflection mechanism is not enabled
		}
		// Use dedicated reflection agent
		return new ReflectionProcessor(reflectionAgent, reflectionProperties.getMaxAttempts());
	}

	@Bean
	public StateGraph deepResearch(ChatClient researchAgent) throws GraphStateException {

		KeyStrategyFactory keyStrategyFactory = () -> {
			HashMap<String, KeyStrategy> keyStrategyHashMap = new HashMap<>();

			// ============================================================
			// 条件边路由控制 —— 各 Dispatcher 读这些 key 决定下一跳
			// ============================================================

			// ShortUserRoleMemoryDispatcher: 值为 "coordinator" 或 END
			keyStrategyHashMap.put("short_user_role_next_node", new ReplaceStrategy());
			// CoordinatorDispatcher: 值为 "rewrite_multi_query" 或 END
			keyStrategyHashMap.put("coordinator_next_node", new ReplaceStrategy());
			// RewriteAndMultiQueryDispatcher: 值为 "background_investigator" / "user_file_rag" / END
			keyStrategyHashMap.put("rewrite_multi_query_next_node", new ReplaceStrategy());
			// BackgroundInvestigationDispatcher: 值为 "planner" / "reporter" / END
			keyStrategyHashMap.put("background_investigation_next_node", new ReplaceStrategy());
			// （预留）planner 节点的条件路由 key
			keyStrategyHashMap.put("planner_next_node", new ReplaceStrategy());
			// InformationDispatcher: 值为 "human_feedback" / "research_team" / "planner" / "reporter" / END
			keyStrategyHashMap.put("information_next_node", new ReplaceStrategy());
			// HumanFeedbackDispatcher: 值为 "research_team" / "planner" / END
			keyStrategyHashMap.put("human_next_node", new ReplaceStrategy());
			// ResearchTeamDispatcher: 值为 "professional_kb_decision" / "parallel_executor" / END
			keyStrategyHashMap.put("research_team_next_node", new ReplaceStrategy());

			// ============================================================
			// 用户输入参数 —— ChatRequestProcess 在请求入口注入
			// ============================================================

			// 用户原始问题文本
			keyStrategyHashMap.put("query", new ReplaceStrategy());
			// RewriteAndMultiQueryNode 产出的多角度搜索查询列表
			keyStrategyHashMap.put("optimize_queries", new ReplaceStrategy());
			// 会话线程 ID，用于关联多轮对话 & 报告存取
			keyStrategyHashMap.put("thread_id", new ReplaceStrategy());
			// CoordinatorNode 设置：true=走深度研究管线，false=直接聊天回复
			keyStrategyHashMap.put("enable_deepresearch", new ReplaceStrategy());
			// HumanFeedbackNode 读取：true=跳过人工确认直接执行计划
			keyStrategyHashMap.put("auto_accepted_plan", new ReplaceStrategy());
			// 计划执行的最大迭代轮次上限
			keyStrategyHashMap.put("plan_max_iterations", new ReplaceStrategy());
			// 单次计划最大研究步骤数
			keyStrategyHashMap.put("max_step_num", new ReplaceStrategy());
			// 用户传入的 MCP 服务器配置（JSON），运行时动态合并到静态配置
			keyStrategyHashMap.put("mcp_settings", new ReplaceStrategy());
			// RewriteAndMultiQueryNode 读取：每次扩展生成的查询数量
			keyStrategyHashMap.put("optimize_query_num", new ReplaceStrategy());
			// 用户上传的文件资源列表，触发 user_file_rag 分支
			keyStrategyHashMap.put("user_upload_file", new ReplaceStrategy());
			// 会话 ID，GraphProcess 用于管理多轮图执行的生命周期
			keyStrategyHashMap.put("session_id", new ReplaceStrategy());

			// HumanFeedbackNode 读取：用户是否已提交反馈（布尔标记）
			keyStrategyHashMap.put("feedback", new ReplaceStrategy());
			// HumanFeedbackNode 读取：用户反馈的具体文本内容
			keyStrategyHashMap.put("feedback_content", new ReplaceStrategy());

			// ============================================================
			// 专业知识库决策 —— ProfessionalKbDecisionNode / ProfessionalKbDispatcher
			// ============================================================

			// ProfessionalKbDecisionNode 设置，ProfessionalKbDispatcher 读取：是否需要查询知识库
			keyStrategyHashMap.put("use_professional_kb", new ReplaceStrategy());
			// ProfessionalKbDecisionNode 设置：选中的知识库列表
			keyStrategyHashMap.put("selected_knowledge_bases", new ReplaceStrategy());

			// ============================================================
			// 节点输出产物 —— 上游节点写入，下游节点读取
			// ============================================================

			// BackgroundInvestigationNode 产出：背景调查报告文本
			keyStrategyHashMap.put("background_investigation_results", new ReplaceStrategy());
			// BackgroundInvestigationNode 产出：搜索到的网站信息列表
			keyStrategyHashMap.put("site_information", new ReplaceStrategy());
			// ResearcherNode / CoderNode 汇总写入，ReporterNode 读取：所有研究步骤的执行结果
			keyStrategyHashMap.put("output", new ReplaceStrategy());
			// 当前计划迭代计数（每轮重规划 +1）
			keyStrategyHashMap.put("plan_iterations", new ReplaceStrategy());
			// InformationNode 产出，PlannerNode / ResearcherNode 读取：当前的结构化 Plan 对象
			keyStrategyHashMap.put("current_plan", new ReplaceStrategy());
			// ReflectionProcessor 写入：反思审查结果（通过/不通过 + 改进建议）
			keyStrategyHashMap.put("observations", new ReplaceStrategy());
			// ReporterNode 产出：最终 Markdown 格式的研究报告全文
			keyStrategyHashMap.put("final_report", new ReplaceStrategy());
			// PlannerNode 产出：LLM 原始输出的规划文本（InformationNode 解析前）
			keyStrategyHashMap.put("planner_content", new ReplaceStrategy());

			// ============================================================
			// 并行节点输出槽位 —— researcher_0..N 和 coder_0..M 各自写自己的槽位
			// 避免并行写入 output 时互相覆盖，各自先写独立 key，最后汇总
			// ============================================================
			for (int i = 0; i < deepResearchProperties
					.getParallelNodeCount()
					.get(ParallelEnum.RESEARCHER.getValue()); i++) {
				keyStrategyHashMap.put(ParallelEnum.RESEARCHER.getValue() + "_content_" + i, new ReplaceStrategy());
			}
			for (int i = 0; i < deepResearchProperties.getParallelNodeCount().get(ParallelEnum.CODER.getValue()); i++) {
				keyStrategyHashMap.put(ParallelEnum.CODER.getValue() + "_content_" + i, new ReplaceStrategy());
			}

			return keyStrategyHashMap;
		};

		StateGraph stateGraph = new StateGraph("deep research", keyStrategyFactory,
				new DeepResearchStateSerializer(OverAllState::new))
			.addNode("short_user_role_memory",
					node_async(new ShortUserRoleMemoryNode(shortMemoryAgent, shortTermMemoryProperties,
							shortTermMemoryRepository)))
			.addNode("coordinator",
					node_async(new CoordinatorNode(coordinatorAgent, sessionContextService, messageWindowChatMemory,
							shortTermMemoryProperties)))
			.addNode("rewrite_multi_query",
					node_async(new RewriteAndMultiQueryNode(rewriteAndMultiQueryChatClientBuilder,
							shortTermMemoryRepository, shortTermMemoryProperties)))
			.addNode("background_investigator",
					node_async(new BackgroundInvestigationNode(jinaCrawlerService, infoCheckService,
							searchFilterService, questionClassifierService, searchPlatformSelectionService,
							smartAgentProperties, backgroundAgent, sessionContextService, toolCallingSearchService)))
			.addNode("user_file_rag", ragNodeService.createUserFileRagNode())
			.addNode("planner", node_async((new PlannerNode(plannerAgent))))
			.addNode("professional_kb_decision",
					node_async(new ProfessionalKbDecisionNode(researchAgent, ragProperties)))
			.addNode("professional_kb_rag", ragNodeService.createProfessionalKbRagNode())
			.addNode("information", node_async((new InformationNode())))
			.addNode("human_feedback", node_async(new HumanFeedbackNode()))
			.addNode("research_team", node_async(new ResearchTeamNode()))
			.addNode("parallel_executor", node_async(new ParallelExecutorNode(deepResearchProperties)))
			.addNode("reporter", node_async(new ReporterNode(reporterAgent, reportService, sessionContextService,
					messageWindowChatMemory, shortTermMemoryProperties)));

		// 添加并行节点块
		configureParallelNodes(stateGraph);

		stateGraph.addEdge(START, "short_user_role_memory")
			.addConditionalEdges("short_user_role_memory", edge_async(new ShortUserRoleMemoryDispatcher()),
					Map.of("coordinator", "coordinator", END, END))
			.addConditionalEdges("coordinator", edge_async(new CoordinatorDispatcher()),
					Map.of("rewrite_multi_query", "rewrite_multi_query", END, END))
			.addConditionalEdges("rewrite_multi_query", edge_async(new RewriteAndMultiQueryDispatcher()),
					Map.of("background_investigator", "background_investigator", "user_file_rag", "user_file_rag", END,
							END))
			.addConditionalEdges("background_investigator", edge_async(new BackgroundInvestigationDispatcher()),
					Map.of("reporter", "reporter", "planner", "planner", END, END))
			.addEdge("user_file_rag", "background_investigator")
			.addEdge("planner", "information")
			.addConditionalEdges("information", edge_async(new InformationDispatcher()),
					Map.of("reporter", "reporter", "human_feedback", "human_feedback", "planner", "planner",
							"research_team", "research_team", END, END))
			.addConditionalEdges("human_feedback", edge_async(new HumanFeedbackDispatcher()),
					Map.of("planner", "planner", "research_team", "research_team", END, END))
			.addConditionalEdges("research_team", edge_async(new ResearchTeamDispatcher()),
					Map.of("professional_kb_decision", "professional_kb_decision", "parallel_executor",
							"parallel_executor", END, END))
			.addConditionalEdges("professional_kb_decision", edge_async(new ProfessionalKbDispatcher()),
					Map.of("professional_kb_rag", "professional_kb_rag", "reporter", "reporter", END, END))
			.addEdge("professional_kb_rag", "reporter")
			.addEdge("reporter", END);

		GraphRepresentation graphRepresentation = stateGraph.getGraph(GraphRepresentation.Type.PLANTUML,
				"workflow graph");

		logger.info("\n\n");
		logger.info(graphRepresentation.content());
		logger.info("\n\n");

		return stateGraph;
	}

	private void configureParallelNodes(StateGraph stateGraph) throws GraphStateException {
		addResearcherNodes(stateGraph);

		addCoderNodes(stateGraph);
	}

	private void addResearcherNodes(StateGraph stateGraph) throws GraphStateException {
		ReflectionProcessor reflectionProcessor = reflectionProcessor();
		for (int i = 0; i < deepResearchProperties.getParallelNodeCount()
			.get(ParallelEnum.RESEARCHER.getValue()); i++) {
			String nodeId = "researcher_" + i;
			stateGraph.addNode(nodeId,
					node_async(new ResearcherNode(researchAgent, String.valueOf(i), reflectionProcessor,
							mcpProviderFactory, searchFilterService, smartAgentDispatcher, smartAgentProperties,
							jinaCrawlerService)));
			stateGraph.addEdge("parallel_executor", nodeId).addEdge(nodeId, "research_team");
		}
	}

	private void addCoderNodes(StateGraph stateGraph) throws GraphStateException {
		ReflectionProcessor reflectionProcessor = reflectionProcessor();
		for (int i = 0; i < deepResearchProperties.getParallelNodeCount().get(ParallelEnum.CODER.getValue()); i++) {
			String nodeId = "coder_" + i;
			stateGraph.addNode(nodeId,
					node_async(new CoderNode(coderAgent, String.valueOf(i), reflectionProcessor, mcpProviderFactory)));
			stateGraph.addEdge("parallel_executor", nodeId).addEdge(nodeId, "research_team");
		}
	}

}
