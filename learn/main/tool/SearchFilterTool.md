# SearchFilterTool.java

一个工具类，包装 `SearchFilterService` 以提供按网站信任权重排序的过滤式网络搜索能力。其 `searchFilterTool` 方法接受查询字符串并委托给 `SearchFilterService.queryAndFilter`，后者使用配置的 `SearchEnum` 搜索引擎（例如 Tavily、Baidu、SerpAPI）应用基于信任的过滤（由 `isEnabledFilter` 布尔值控制）。结果以 `SearchContentWithWeight` 对象列表的形式返回，允许下游消费者同时查看内容及其关联的信任评分。当过滤器启用时，不被信任的网站结果将被过滤掉。
