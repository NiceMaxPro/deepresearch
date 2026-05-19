# DeepResearch 管线流转流程图

```mermaid
flowchart TD
    START([START]) --> short_user_role_memory

    subgraph phase1["① 用户记忆提取"]
        short_user_role_memory["<b>ShortUserRoleMemoryNode</b><br/>提取/更新用户角色记忆"]
    end

    short_user_role_memory -->|"有记忆"| coordinator
    short_user_role_memory -->|"无记忆/跳过"| END1([END])

    subgraph phase2["② 入口路由"]
        coordinator["<b>CoordinatorNode</b><br/>判断：简单聊天 or 深度研究？"]
    end

    coordinator -->|"检测到 tool call<br/>→ 需要深度研究"| rewrite_multi_query
    coordinator -->|"无 tool call<br/>→ 直接回复"| END2([END])

    subgraph phase3["③ 查询重写"]
        rewrite_multi_query["<b>RewriteAndMultiQueryNode</b><br/>压缩上下文 + 查询重写<br/>+ 多角度查询扩展"]
    end

    rewrite_multi_query -->|"有搜索查询"| background_investigator
    rewrite_multi_query -->|"有用户文件"| user_file_rag
    rewrite_multi_query -->|"无查询/异常"| END3([END])

    subgraph phase4["④ 用户文件RAG"]
        user_file_rag["<b>RagNode</b><br/>（用户文件模式）<br/>检索用户上传的文档"]
    end

    user_file_rag --> background_investigator

    subgraph phase5["⑤ 背景调查"]
        background_investigator["<b>BackgroundInvestigationNode</b><br/>多源搜索 + 信息审核<br/>+ 智能Agent选择<br/>生成背景调查报告"]
    end

    background_investigator -->|"快速回答即可"| reporter
    background_investigator -->|"需要深入研究"| planner
    background_investigator -->|"异常"| END4([END])

    subgraph phase6["⑥ 计划生成"]
        planner["<b>PlannerNode</b><br/>基于背景调查 + 反馈<br/>+ RAG结果<br/>生成结构化研究计划"]
    end

    planner --> information

    subgraph phase7["⑦ 计划解析"]
        information["<b>InformationNode</b><br/>反序列化LLM输出<br/>→ 结构化 Plan 对象<br/>含重试逻辑"]
    end

    information -->|"计划完备 → 进入执行"| human_feedback
    information -->|"计划不完整 → 重规划"| planner
    information -->|"直接报告"| reporter
    information -->|"异常"| END5([END])
    information -->|"跳过反馈 → 直接执行"| research_team

    subgraph phase8["⑧ 人机协同"]
        human_feedback["<b>HumanFeedbackNode</b><br/>展示计划给用户确认<br/>支持反馈驱动重新规划<br/>有迭代次数上限"]
    end

    human_feedback -->|"用户接受计划"| research_team
    human_feedback -->|"用户反馈 → 重新规划"| planner
    human_feedback -->|"超时/异常"| END6([END])

    subgraph phase9["⑨ 研究团队路由"]
        research_team["<b>ResearchTeamNode</b><br/>检查步骤完成状态<br/>决定进入哪个执行分支"]
    end

    research_team -->|"有专业知识库需求"| professional_kb_decision
    research_team -->|"需要执行研究步骤"| parallel_executor
    research_team -->|"全部完成/异常"| END7([END])

    subgraph phase10["⑩ 并行执行"]
        parallel_executor["<b>ParallelExecutorNode</b><br/>按轮询分配计划步骤<br/>→ N个Researcher + M个Coder<br/>保证研究先于编码执行"]
    end

    subgraph parallel_nodes["并行工作节点（动态创建 researcher_0..N, coder_0..M）"]
        researcher_0["<b>ResearcherNode</b><br/>web搜索 + 智能Agent选择<br/>+ 反思机制 + 引用追踪"]
        researcher_1["<b>ResearcherNode</b>"]
        researcher_N["<b>ResearcherNode</b>"]
        coder_0["<b>CoderNode</b><br/>Docker沙箱执行Python<br/>+ MCP工具回调<br/>+ 反思机制"]
        coder_1["<b>CoderNode</b>"]
        coder_M["<b>CoderNode</b>"]
    end

    parallel_executor --> researcher_0
    parallel_executor --> researcher_1
    parallel_executor --> researcher_N
    parallel_executor --> coder_0
    parallel_executor --> coder_1
    parallel_executor --> coder_M

    researcher_0 --> research_team
    researcher_1 --> research_team
    researcher_N --> research_team
    coder_0 --> research_team
    coder_1 --> research_team
    coder_M --> research_team

    subgraph phase11["⑪ 专业知识库"]
        professional_kb_decision["<b>ProfessionalKbDecisionNode</b><br/>LLM判断需要查询<br/>哪些专业知识库"]
        professional_kb_rag["<b>RagNode</b><br/>（专业知识库模式）<br/>API / ES 检索知识库"]
    end

    professional_kb_decision -->|"匹配到知识库"| professional_kb_rag
    professional_kb_decision -->|"不需要/无匹配"| reporter
    professional_kb_decision -->|"异常"| END8([END])

    professional_kb_rag --> reporter

    subgraph phase12["⑫ 报告生成"]
        reporter["<b>ReporterNode</b><br/>汇总所有中间结果<br/>（背景调查 + 研究步骤<br/>+ RAG结果 + 反思历史）<br/>生成最终研究报告<br/>保存到会话历史 & 短期记忆"]
    end

    reporter --> END9([END])

    %% ===== 样式 =====
    style START fill:#4CAF50,color:#fff
    style END1 fill:#f44336,color:#fff
    style END2 fill:#f44336,color:#fff
    style END3 fill:#f44336,color:#fff
    style END4 fill:#f44336,color:#fff
    style END5 fill:#f44336,color:#fff
    style END6 fill:#f44336,color:#fff
    style END7 fill:#f44336,color:#fff
    style END8 fill:#f44336,color:#fff
    style END9 fill:#4CAF50,color:#fff

    style coordinator fill:#FF9800,color:#000
    style rewrite_multi_query fill:#03A9F4,color:#fff
    style background_investigator fill:#03A9F4,color:#fff
    style planner fill:#9C27B0,color:#fff
    style information fill:#607D8B,color:#fff
    style human_feedback fill:#E91E63,color:#fff
    style research_team fill:#FF5722,color:#fff
    style parallel_executor fill:#FF5722,color:#fff
    style reporter fill:#4CAF50,color:#fff
    style professional_kb_decision fill:#00BCD4,color:#000
    style professional_kb_rag fill:#00BCD4,color:#000
    style user_file_rag fill:#00BCD4,color:#000
    style short_user_role_memory fill:#795548,color:#fff
```

---

## 关键路由决策汇总

| 节点 | Dispatcher | 路由逻辑 |
|------|-----------|---------|
| short_user_role_memory | ShortUserRoleMemoryDispatcher | 读取 `short_user_role_next_node` 状态值 → coordinator 或 END |
| coordinator | CoordinatorDispatcher | 检测 LLM 响应中是否有 tool call → 有则 rewrite_multi_query，无则 END（直接聊天） |
| rewrite_multi_query | RewriteAndMultiQueryDispatcher | 有 `optimize_queries` → background_investigator；有 `user_upload_file` → user_file_rag |
| background_investigator | BackgroundInvestigationDispatcher | `enable_deepresearch=true` → planner；false → reporter（快速回答） |
| information | InformationDispatcher | Plan 状态完备 → human_feedback 或 research_team；不完整 → planner（重规划循环） |
| human_feedback | HumanFeedbackDispatcher | 用户接受或跳过（`auto_accepted_plan`）→ research_team；用户反馈 → planner |
| research_team | ResearchTeamDispatcher | `use_professional_kb=true` → professional_kb_decision；否则 → parallel_executor |
| professional_kb_decision | ProfessionalKbDispatcher | `selected_knowledge_bases` 非空 → professional_kb_rag；为空 → reporter |

---

## 两条典型路径

### 路径 A：快速回答（不进入深度研究）
```
START → short_user_role_memory → coordinator → END
```
coordinator 判断不需要 tool call，直接聊天回复。

### 路径 B：完整深度研究
```
START → short_user_role_memory → coordinator → rewrite_multi_query
  → background_investigator → planner → information → human_feedback
  → research_team → parallel_executor → researcher_X / coder_X
  → research_team → professional_kb_decision → professional_kb_rag
  → reporter → END
```
