# SearchBeanUtil.java

Spring `@Service`，将配置的搜索引擎列表与 Spring `ApplicationContext` 桥接起来，以提供搜索服务 Bean 实例。其构造函数接收应用上下文和 `DeepResearchProperties`（包含配置的 `searchList`）。`getSearchService` 通过 `SearchUtil.getSearchService` 查找给定 `SearchEnum` 对应的 `SearchService` Bean，但仅在该枚举存在于已配置的搜索列表中时才返回——确保仅使用显式启用的搜索插件。`getFirstAvailableSearch` 遍历配置列表，返回第一个在上下文中实际可用的服务 Bean 对应的枚举，在特定引擎无法使用时提供回退机制。
