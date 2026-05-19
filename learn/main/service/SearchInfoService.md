# SearchInfoService.java

此服务编排网页搜索，并集成了重试逻辑、内容增强和工具调用支持。主要的 `searchInfo` 方法最多调用 `SearchFilterService.queryAndFilter` 三次，每次重试间隔 500 毫秒，然后对每个结果进行增强：从域名中提取网站 favicon URL，并在 `JinaCrawlerService` 可用且 URL 有效时，通过 Jina 获取完整页面内容，而非依赖搜索摘要。重载的 `searchInfo` 方法接受一个 `SearchPlatform` 参数，对于工具调用平台（OpenAlex、OpenTripMap、Wikipedia 等）会路由到 `ToolCallingSearchService.performToolCallingSearch`，如果工具调用失败则回退到传统搜索。结果以包含 title、weight、url、icon 和 content 键的 map 列表形式返回。
