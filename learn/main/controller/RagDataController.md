# RagDataController.java

一个多功能控制器，用于通过文件上传管理 RAG（检索增强生成）数据导入。它暴露以下端点：`POST /api/rag/upload`（基础单文件导入）、`POST /api/rag/user/batch-upload`（批量文件处理，附带会话/用户元数据和分块计数）、`POST /api/rag/professional-kb/upload`（将单个文件上传到 Elasticsearch 中指定的专业知识库）、以及 `POST /api/rag/professional-kb/batch-upload`（批量上传到专业知识库）。所有端点均委托 `VectorStoreDataIngestionService` 进行文件处理、向量存储导入和 Elasticsearch 索引，返回结构化的 JSON 响应，包含分块计数、文件名和知识库标识符等元数据。
