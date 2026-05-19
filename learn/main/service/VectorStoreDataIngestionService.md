# VectorStoreDataIngestionService.java

此 `@Service` 处理将文档导入向量存储以用于 RAG（检索增强生成）目的。它使用 Apache Tika（`TikaDocumentReader`）解析多种文档格式（PDF、DOCX、Markdown 等），使用可配置的 `TokenTextSplitter` 对内容进行分块，并在存储前为每个分块添加元数据。该服务支持三种不同的导入流水线：用户文件上传（标记为 `source_type=USER_UPLOAD` 以及会话/用户 ID）、专业知识库 ES 上传（标记为 `source_type=PROFESSIONAL_KB_ES`、kb_id、kb_name）以及通用资源导入。通过不同的方法（`batchProcessAndStore` 与 `batchProcessAndStoreResources`）同时支持 `MultipartFile` 和 Spring `Resource` 输入类型，并为专业知识库上传提供并行变体。元数据增强包括标题提取（首行，最多 50 个字符）、时间戳、分块 ID 以及文件级属性。
