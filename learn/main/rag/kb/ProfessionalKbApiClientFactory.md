# ProfessionalKbApiClientFactory.java

一个 Spring `@Component` 工厂类，负责根据应用程序配置创建 `ProfessionalKbApiClient` 实例。它从 `RagProperties` 中读取知识库定义，并实例化相应的客户端实现：`DashScopeKbApiClient` 用于 `"dashscope"` 提供者，`CustomKbApiClient` 用于 `"custom"`（也是默认的回退选项）。该工厂提供两个方法：`createClient()` 用于根据知识库配置构建单个客户端，`createClients()` 用于批量创建所有启用的 API 类型知识库，并以知识库 ID 为键的 map 形式返回。仅处理 `type == "api"` 且 `enabled == true` 的知识库；基于 Elasticsearch 的知识库会被跳过。该类仅在通过 `spring.ai.alibaba.deepresearch.rag.enabled` 属性启用 RAG 时才条件激活。
