# SmartAgentDispatcherService.java

此 `@Component` 编排完整的智能体调度流水线：问题分类、搜索平台选择和智能体分配，通过 `smart.agent.enabled` 条件启用。`dispatchToAgent(String question, OverAllState state)` 方法首先调用 `QuestionClassifierService.classifyQuestion` 来确定智能体类型，然后调用 `SearchPlatformSelectionService.selectSearchPlatforms` 来选择合适的搜索后端，最后通过私有的 `selectAgent` switch 表达式将智能体类型映射到特定的 `@Qualifier` 命名的 `ChatClient`。它返回一个 `AgentDispatchResult`，包含选中的 ChatClient、智能体类型、搜索平台列表、搜索策略描述、成功标志和可选的错误信息。在发生任何异常时，它会回退到 `researchAgent`，使用 `GENERAL_RESEARCH` 类型和 `TAVILY` 搜索引擎。
