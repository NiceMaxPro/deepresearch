# RrfHybridElasticsearchRetriever.java

一个混合 Elasticsearch 文档检索器，结合了 BM25 文本搜索和 KNN 向量搜索，使用倒数排名融合（RRF）进行结果排序。它实现 Spring AI 的 `DocumentRetriever` 接口，并包装了底层的 Elasticsearch Java 客户端。当启用混合模式时，它对 `"embedding"` 字段执行 KNN 搜索，对 `"content"` 字段执行 BM25 `match` 查询，然后应用 Elasticsearch 内置的 RRF 排名聚合来合并两个结果集。可配置的关键参数包括 `rrfWindowSize`、`rrfRankConstant`（RRF 的 `k` 常数）、`bm25Boost` 和 `knnBoost`，均来自 `RagProperties`。该类在其 `retrieve()` 重载方法上还支持可选的 ES 过滤查询参数，使调用方能够按元数据字段（如 `source_type` 或 `session_id`）限定搜索范围。
