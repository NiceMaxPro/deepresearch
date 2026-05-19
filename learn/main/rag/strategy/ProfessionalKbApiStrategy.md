# ProfessionalKbApiStrategy.java

一种通过外部 API 搜索专业知识库的检索策略。它实现 `RetrievalStrategy` 接口，策略名称为 `"professionalKbApi"`。初始化时，它使用 `ProfessionalKbApiClientFactory` 为配置中定义的所有已启用的 API 类型知识库创建 API 客户端实例。在检索过程中，它通过 `HybridRagProcessor` 运行查询预处理（扩展、翻译），然后遍历选定的知识库（可以是 `selected_knowledge_bases` 选项中指定的知识库，也可以是所有可用的知识库），调用每个客户端的 `search()` 方法，并将 `KbSearchResult` 记录转换为带有适当元数据（`source_type`、`kb_id`、`kb_name`、`api_provider` 等）的 Spring AI `Document` 对象。最后，它将后处理（去重、重排序）委托回 `HybridRagProcessor`。它还暴露了 `getAvailableKnowledgeBases()` 用于监控，以及 `reinitializeClients()` 用于在配置更改后热重载。
