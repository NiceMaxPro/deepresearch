# ToolCallingSearchService.java

此 `@Service` 通过外部工具调用 API 为不同领域执行专用的搜索查询，通过 `smart.agent.enabled` 条件启用。它通过构造函数注入六个可选的 `SearchService` 实现：OpenAlex（学术论文）、OpenTripMap 和 TripAdvisor（旅行/生活方式）、Wikipedia（百科知识）、WorldBank Data（统计数据）和 Google Scholar（学术引用）。`performToolCallingSearch(SearchPlatform, String)` 方法根据平台枚举路由到相应的服务，调用其 `query` 方法，并将 `SearchService.SearchContent` 结果转换为标准化的 map 列表，包含 title、content、url、weight（始终为 "1.0"）和 source（平台名称）键。如果平台没有匹配的服务或查询失败，则返回空列表。
