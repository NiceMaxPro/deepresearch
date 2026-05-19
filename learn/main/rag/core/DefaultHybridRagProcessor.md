# DefaultHybridRagProcessor.java

`HybridRagProcessor` 接口的主要实现，提供统一的 RAG（检索增强生成）处理流水线，集成了检索前查询变换、混合 Elasticsearch 检索和检索后文档处理。它根据 `RagProperties` 中的配置标志有条件地初始化多个子组件：用于查询扩展的 `MultiQueryExpander`、用于查询翻译的 `TranslationQueryTransformer`、用于假设文档嵌入的 `HyDeTransformer`、用于混合 BM25+KNN 搜索的 `RrfHybridElasticsearchRetriever`、用于后处理的 `DocumentSelectFirstProcess` 以及用于重排序的 `RrfFusionStrategy`。

`process()` 方法编排完整的流水线：首先调用 `preProcess()` 来扩展/翻译/变换查询，然后调用 `buildFilterExpression()` 从元数据选项（`source_type`、`session_id`、`user_id`）构建 Elasticsearch 过滤查询，接着通过 `hybridRetrieve()` 对混合检索器或标准向量存储执行搜索，最后通过 `postProcess()` 去重结果并可选择性地应用 RRF 重排序。该类使用 `@ConditionalOnProperty` 注解，仅在应用程序配置中启用 RAG 时激活。
