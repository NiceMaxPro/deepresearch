# DeepResearch 项目学习路线

## 学习理念

这个项目的核心是一条 **状态图执行管线**：用户提出问题 → 图节点逐级处理 → 最终生成研究报告。建议按照**数据流向**学习，从入口到出口追踪一条请求的完整生命周期，遇到不认识的类时再横向深入。

全局管线（来自 `DeepResearchConfiguration.java`）：

```
START → short_user_role_memory → coordinator → rewrite_multi_query → user_file_rag
  → background_investigator → planner → information → human_feedback
  → research_team → parallel_executor → (researcher_0..N + coder_0..N) → research_team
  → professional_kb_decision → professional_kb_rag → reporter → END
```

---

## 第一阶段：入口与骨架（5 个文件，约 30 分钟）

理解项目如何启动以及核心配置长什么样。

| 顺序 | 文件 | 要搞清楚的问题 |
|------|------|---------------|
| 1 | `learn/main/DeepResearchApplication.md` | Spring Boot 入口，什么都没做，只是启动 |
| 2 | `learn/main/config/DeepResearchConfiguration.md` | **这是最重要的文件**。整个管线在这里组装。画出节点连接图 |
| 3 | `learn/main/config/DeepResearchProperties.md` | 有哪些可配置项？并行节点数、最大迭代次数 |
| 4 | `learn/main/resources/application.md` | 实际配置文件长什么样，启用了哪些功能 |
| 5 | `learn/main/resources/model-config.md` | 16 个 Agent 角色分别用哪个模型 |

**检查点**：能徒手画出节点流程图，说出每个节点的职责。

---

## 第二阶段：请求如何进入系统（4 个文件，约 20 分钟）

| 顺序 | 文件 | 要搞清楚的问题 |
|------|------|---------------|
| 6 | `learn/main/controller/ChatController.md` | SSE 流式聊天端点，`/chat/stream` |
| 7 | `learn/main/controller/request/ChatRequestProcess.md` | 请求参数如何标准化、初始状态如何构建 |
| 8 | `learn/main/controller/graph/GraphProcess.md` | **核心执行器**：如何编译图、启动流式处理、管理会话 |
| 9 | `learn/main/controller/RedirectController.md` | SPA 前端路由（了解即可） |

**检查点**：能说出一个 HTTP POST `/chat/stream` 请求经历了哪些步骤才进入图执行。

---

## 第三阶段：管线核心节点（7 个文件，约 60 分钟）

这是项目的心脏。按照管线顺序学习：

| 顺序 | 文件 | 它在管线中的位置与职责 |
|------|------|------------------------|
| 10 | `learn/main/node/CoordinatorNode.md` | **入口路由**：判断是简单聊天还是需要深度研究。检测 tool call |
| 11 | `learn/main/node/RewriteAndMultiQueryNode.md` | 查询重写 + 多查询扩展，把一个模糊问题变成多个精准搜索词 |
| 12 | `learn/main/node/BackgroundInvestigationNode.md` | **第一轮搜索**：用重写后的查询去搜索，生成背景调查报告 |
| 13 | `learn/main/node/PlannerNode.md` | 基于背景调查生成结构化的研究计划 |
| 14 | `learn/main/node/InformationNode.md` | 解析 Planner 的 LLM 输出，转为结构化的 `Plan` 对象 |
| 15 | `learn/main/node/HumanFeedbackNode.md` | **人机协同**：展示计划给用户确认，支持反馈和重新规划 |
| 16 | `learn/main/node/ReporterNode.md` | **最终输出**：汇总所有中间结果，生成最终研究报告 |

**检查点**：能详细解释从 Coordinator 到 Reporter 每一步的输入输出和决策逻辑。

---

## 第四阶段：并行执行与研究闭环（4 个文件，约 30 分钟）

| 顺序 | 文件 | 要搞清楚的问题 |
|------|------|---------------|
| 17 | `learn/main/node/ResearchTeamNode.md` | 轻量路由节点：检查步骤完成状态，决定下一步 |
| 18 | `learn/main/node/ParallelExecutorNode.md` | 如何把计划步骤轮询分配给多个并行 Researcher/Coder |
| 19 | `learn/main/node/ResearcherNode.md` | **研究执行者**：执行单个研究步骤，含搜索、反思、引用 |
| 20 | `learn/main/node/CoderNode.md` | **代码执行者**：执行含代码的计划步骤，Docker 沙箱运行 Python |

**检查点**：理解 ParallelExecutor → N×Researcher + M×Coder → ResearchTeam 的并行执行循环。

---

## 第五阶段：条件路由与调度器（8 个文件，约 30 分钟）

每个 Dispatcher 决定管线中"下一步走哪个节点"。

| 顺序 | 文件 | 路由决策 |
|------|------|---------|
| 21 | `learn/main/dispatcher/ShortUserRoleMemoryDispatcher.md` | 有历史角色记忆 → coordinator，否则 → END |
| 22 | `learn/main/dispatcher/CoordinatorDispatcher.md` | 需要深度研究 → rewrite_multi_query，否则 → END |
| 23 | `learn/main/dispatcher/RewriteAndMultiQueryDispatcher.md` | 有搜索查询 → background_investigator，有文件 → user_file_rag |
| 24 | `learn/main/dispatcher/BackgroundInvestigationDispatcher.md` | 需要深入研究 → planner，快速回答 → reporter |
| 25 | `learn/main/dispatcher/InformationDispatcher.md` | 计划通过 → human_feedback 或 research_team，需重规划 → planner |
| 26 | `learn/main/dispatcher/HumanFeedbackDispatcher.md` | 用户接受 → research_team，用户反馈 → planner |
| 27 | `learn/main/dispatcher/ResearchTeamDispatcher.md` | 有专业 KB → professional_kb_decision，否则 → parallel_executor |
| 28 | `learn/main/dispatcher/ProfessionalKbDispatcher.md` | 需要查知识库 → professional_kb_rag，否则 → reporter |

**检查点**：能理解每条 `addConditionalEdges` 的路由逻辑。

---

## 第六阶段：Agent 创建与工具系统（7 个文件，约 40 分钟）

| 顺序 | 文件 | 要搞清楚的问题 |
|------|------|---------------|
| 29 | `learn/main/agents/AgentsConfiguration.md` | **所有 Agent 的 ChatClient Bean 定义**。每个 Agent 绑定什么 prompt、什么工具 |
| 30 | `learn/main/agents/AgentModelsConfiguration.md` | 模型配置如何从 JSON 加载并注册为 Bean |
| 31 | `learn/main/tool/PlannerTool.md` | 计划工具：LLM 通过它发出"我已完成规划"的信号 |
| 32 | `learn/main/tool/SearchFilterTool.md` | 搜索工具：LLM 用它执行带信任权重的网络搜索 |
| 33 | `learn/main/tool/PythonReplTool.md` | Python 沙箱：Docker 容器内执行代码，默认无网络 |
| 34 | `learn/main/agents/McpAssignNodeConfiguration.md` | MCP 协议：如何给节点动态分配外部工具服务器 |
| 35 | `learn/main/agents/ObservationConfiguration.md` | 工具调用的可观测性日志 |

**检查点**：能说出每个 Agent 绑定了哪些工具和 prompt，理解 Agent 创建流程。

---

## 第七阶段：服务层（选读 8 个核心文件，约 45 分钟）

服务层文件较多，挑核心的看：

| 顺序 | 文件 | 为什么重要 |
|------|------|-----------|
| 36 | `learn/main/service/SearchInfoService.md` | 搜索编排：重试、Jina 爬取、多平台聚合 |
| 37 | `learn/main/service/SearchFilterService.md` | 搜索结果按信任权重排序过滤 |
| 38 | `learn/main/service/ReportService.md` | 报告存储接口 |
| 39 | `learn/main/service/ReportMemoryService.md` | 内存中存报告（Redis 不可用时的回退） |
| 40 | `learn/main/service/ReportRedisService.md` | Redis 存报告 |
| 41 | `learn/main/service/InfoCheckService.md` | AI 审查搜索结果敏感度/相关性 |
| 42 | `learn/main/service/multiagent/QuestionClassifierService.md` | 问题分类 → 智能 Agent 类型 |
| 43 | `learn/main/service/multiagent/SmartAgentDispatcherService.md` | 智能 Agent 调度管线 |

其余 service 文件（`McpService`, `ExportService`, `SessionContextService`, `RagNodeService`, `VectorStoreDataIngestionService`, `LocalConfigSearchFilterService`, `McpProviderFactory` 等）可在需要时查阅。

---

## 第八阶段：RAG 子系统（选读 6 个核心文件，约 30 分钟）

| 顺序 | 文件 | 在 RAG 流程中的角色 |
|------|------|--------------------|
| 44 | `learn/main/rag/core/HybridRagProcessor.md` | RAG 处理器接口 |
| 45 | `learn/main/rag/core/DefaultHybridRagProcessor.md` | 默认实现：检索 + 重排序 + 上下文构建 |
| 46 | `learn/main/rag/strategy/RetrievalStrategy.md` | 检索策略接口 |
| 47 | `learn/main/rag/strategy/ProfessionalKbApiStrategy.md` | API 方式检索专业知识库 |
| 48 | `learn/main/rag/strategy/ProfessionalKbEsStrategy.md` | Elasticsearch 方式检索专业知识库 |
| 49 | `learn/main/rag/retriever/RrfHybridElasticsearchRetriever.md` | BM25 + KNN 混合检索，RRF 融合 |

其余 RAG 文件（kb 客户端、transformer、post 处理）按需查阅。

---

## 第九阶段：模型与数据结构（按需查阅，约 30 分钟）

数据流经系统的载体，建议先看 `Plan` 和 `ReflectionResult`，其余随用随查：

| 优先级 | 文件 | 说明 |
|--------|------|------|
| 高 | `learn/main/model/dto/Plan.md` | 研究计划结构（步骤列表、当前步骤等） |
| 高 | `learn/main/model/dto/ReflectionResult.md` | 反思评估结果（通过/不通过、反馈） |
| 高 | `learn/main/model/enums/NodeNameEnum.md` | 所有节点名称枚举（含显示标题） |
| 中 | `learn/main/model/enums/ParallelEnum.md` | 并行 Agent 类型（RESEARCHER/CODER） |
| 中 | `learn/main/model/multiagent/AgentType.md` | 智能 Agent 类型和对应 prompt 路径 |
| 中 | `learn/main/model/multiagent/SearchPlatform.md` | 搜索平台到 Agent 类型的路由 |
| 中 | `learn/main/model/dto/memory/ShortUserRoleExtractResult.md` | 用户角色记忆提取结果 |
| 低 | `learn/main/model/req/ChatRequest.md` | 聊天请求参数 |
| 低 | `learn/main/model/ApiResponse.md` | 统一 API 响应包装 |
| 低 | 其余 model 文件 | 各种 DTO、枚举、请求/响应对象 |

---

## 第十阶段：Prompt 工程（选读，约 30 分钟）

理解每个 Agent 被赋予了什么样的系统指令：

| 文件 | 对应的 Agent |
|------|-------------|
| `learn/main/resources/prompts/coordinator.md` | CoordinatorNode |
| `learn/main/resources/prompts/researcher.md` | ResearcherNode, BackgroundInvestigationNode |
| `learn/main/resources/prompts/background.md` | BackgroundInvestigationNode（背景调查） |
| `learn/main/resources/prompts/planner.md` | PlannerNode（研究计划生成） |
| `learn/main/resources/prompts/reporter.md` | ReporterNode（报告撰写） |
| `learn/main/resources/prompts/coder.md` | CoderNode（代码执行） |
| `learn/main/resources/prompts/reflection.md` | ReflectionProcessor（质量反思） |
| `learn/main/resources/prompts/rag.md` | RAG 问答 |
| `learn/main/resources/prompts/buildInteractiveHtmlPrompt.md` | 交互式 HTML 报告 |
| `learn/main/resources/prompts/backgroundInfoCheck.md` | 内容安全检查 |

---

## 第十一阶段：序列化与工具类（按需查阅，约 20 分钟）

| 文件 | 作用 |
|------|------|
| `learn/main/serializer/DeepResearchStateSerializer.md` | 图状态序列化为字节数组 |
| `learn/main/serializer/DeepResearchDeserializer.md` | 图状态反序列化 |
| `learn/main/util/ReflectionProcessor.md` | 质量反思处理器 |
| `learn/main/util/ReflectionUtil.md` | 反思状态检查工具方法 |
| `learn/main/util/StateUtil.md` | 图状态读写辅助 |
| `learn/main/util/export/HtmlGenerationUtil.md` | Markdown → HTML |
| `learn/main/util/export/FormatConversionUtil.md` | HTML → PDF |
| `learn/main/util/mcp/McpClientUtil.md` | MCP 客户端创建 |
| `learn/main/util/multiagent/AgentPromptTemplateUtil.md` | 智能 Agent prompt 加载 |

---

## 第十二阶段：测试（4 个文件，约 20 分钟）

| 文件 | 测试了什么 |
|------|-----------|
| `learn/test/controller/graph/GraphProcessExceptionHandlingTest.md` | 图执行的异常处理 |
| `learn/test/tool/PythonReplToolBasisTest.md` | Python 沙箱无网络模式 |
| `learn/test/tool/PythonReplToolNetworkTest.md` | Python 沙箱联网模式 |
| `learn/test/tool/SearchFilterServiceTest.md` | 搜索排序和过滤 |

---

## 总览

| 阶段 | 文件数 | 累计时间 | 核心问题 |
|------|--------|---------|---------|
| 一：入口与骨架 | 5 | 30min | 项目怎么启动、管线长什么样 |
| 二：请求入口 | 4 | 20min | HTTP 请求如何变成图执行 |
| 三：管线核心节点 | 7 | 60min | 每个节点做什么、输入输出是什么 |
| 四：并行执行 | 4 | 30min | Researcher/Coder 如何并行工作 |
| 五：条件路由 | 8 | 30min | 图如何决定下一步走哪个节点 |
| 六：Agent 与工具 | 7 | 40min | Agent 如何创建、绑定什么工具 |
| 七：服务层 | 8 | 45min | 核心业务逻辑在哪 |
| 八：RAG | 6 | 30min | 检索增强生成如何工作 |
| 九：数据结构 | ~10 | 30min | 数据在系统中如何流转 |
| 十：Prompt 工程 | 10 | 30min | 每个 Agent 被赋予了怎样的指令 |
| 十一：序列化与工具 | ~9 | 20min | 状态如何持久化、辅助工具 |
| 十二：测试 | 4 | 20min | 关键组件的测试覆盖 |
| **合计** | **~82** | **~6.5h** | |

## 学习建议

1. **一到六阶段是必读**（约 35 个文件，3.5 小时），覆盖了从请求进入到报告生成的完整主流程。
2. 读代码时始终保持"数据流向"意识：一个 HTTP 请求携带什么状态进来，在图节点间如何被读写、转换，最终如何变成报告。
3. 核心文件只有两个：`DeepResearchConfiguration.java`（管线组装图）和 `GraphProcess.java`（执行引擎）。如果在某一步卡住，回到这两个文件重新追踪。
4. 遇到 `OverAllState` 的读写时，去 `StateUtil.java` 查状态 key 的定义。
5. Prompt 文件和对应的 Node 文件 **对照着读**，才能理解 AI 的行为设计意图。
