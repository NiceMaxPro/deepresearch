# HybridRagProcessor.java

一个接口，定义了统一 RAG 处理流水线的契约。它声明了五个方法：`process()` 用于执行从查询到最终文档列表的完整端到端流程，`preProcess()` 用于查询扩展和翻译，`hybridRetrieve()` 用于通过过滤表达式对 Elasticsearch 或向量存储执行搜索，`postProcess()` 用于去重和重排序，以及 `buildFilterExpression()` 用于从元数据上下文（如 `session_id`、`user_id` 和 `source_type`）构建 Elasticsearch 过滤查询。唯一的实现是 `DefaultHybridRagProcessor`，它将所有 RAG 子组件整合为一个连贯的工作流。该接口清晰地分离了关注点，使 RAG 流水线的每个阶段都可以独立定制。
