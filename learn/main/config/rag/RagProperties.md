# RagProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.rag`，是 RAG 系统的核心配置。包含多个内部配置类：全局开关 `enabled`、`vectorStoreType`（simple/elasticsearch）、超时和重试配置；`Simple`（简单向量存储路径）；`Pipeline`（查询扩展、翻译、HyDE 假设文档嵌入、后处理、TopK、相似度阈值、重排序等 Pipeline 参数）；`Data`（初始加载位置和定时扫描配置）；`Elasticsearch`（索引名、维度、连接 URI、混合搜索 BM25+KNN RRF 配置）；`ProfessionalKnowledgeBases`（知识库列表，每个库包含 API 配置、提供者类型等）；`TextSplitter`（文本分割块大小、重叠等参数）。
