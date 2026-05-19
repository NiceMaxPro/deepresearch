# ProfessionalKbApiClient.java

一个接口，定义了专业知识库 API 客户端的契约。它声明了三个方法：`search()` 用于对知识库执行查询并返回 `KbSearchResult` 记录列表，`getProvider()` 用于标识提供者类型（例如 `"dashscope"` 或 `"custom"`），以及 `isAvailable()` 用于检查客户端是否已正确配置并准备好使用。实现包括用于阿里 DashScope 知识库服务的 `DashScopeKbApiClient` 和用于通用 REST API 知识库的 `CustomKbApiClient`。`ProfessionalKbApiClientFactory` 根据配置属性创建此接口的实例。
