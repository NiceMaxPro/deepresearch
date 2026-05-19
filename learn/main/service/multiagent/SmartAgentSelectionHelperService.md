# SmartAgentSelectionHelperService.java

此辅助服务将 `SmartAgentDispatcherService` 包装起来，添加了可用性检查和回退逻辑，为图执行节点中的智能体选择提供了一个更安全的入口点。`selectSmartAgent` 方法首先通过 `AgentIntegrationUtil.isSmartAgentAvailable()` 验证智能体功能是否可用，如果不可用则返回回退结果。当可用时，它委托给 `SmartAgentDispatcherService.dispatchToAgent()`，并将成功/失败映射到 `AgentSelectionResult` 对象。`intelligentSearchSelection` 方法执行统一的分类和平台选择，返回一个 `SmartAgentUtil.SearchSelectionResult`，指示该平台是使用工具调用还是传统搜索，以及选中的 `SearchEnum`、`SearchPlatform` 和 `AgentType`。两个方法在发生任何错误时都会优雅地回退到默认配置。
