# SearchPlatformSelectionService.java

此 `@Service` 根据智能体类型和查询智能选择最合适的搜索平台，通过 `smart.agent.enabled` 条件启用。它首先检查 `SmartAgentProperties` 中是否已配置平台映射；如果没有，则使用 AI 驱动的 `ChatClient` 动态选择平台。`selectSearchPlatforms` 方法返回一个 `SearchEnum` 值列表——对于工具调用平台（OpenAlex、OpenTripMap、Wikipedia、WorldBank Data、Google Scholar），使用 `SearchEnum.TAVILY` 作为占位符；对于传统搜索引擎，则会根据 `enabledSearchEngines` 配置列表验证其可用性。当首选平台不可用时，回退逻辑会添加默认搜索引擎（Tavily、Aliyun、Baidu、SerpAPI）。`getSearchStrategyDescription` 方法委托给 `SmartAgentUtil` 以获取可读的搜索策略描述。
