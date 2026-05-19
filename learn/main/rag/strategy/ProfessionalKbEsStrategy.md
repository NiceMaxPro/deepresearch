# ProfessionalKbEsStrategy.java

一种搜索存储在 Elasticsearch 中的专业知识库的检索策略。它实现 `RetrievalStrategy` 接口，策略名称为 `"professionalKbEs"`。`retrieve()` 方法简单地委托给 `HybridRagProcessor.process()`，传入将 `source_type` 设置为 `PROFESSIONAL_KB_ES`、`session_id` 设置为固定值 `"professional_kb_es"` 的选项。这确保了混合 RAG 处理器的过滤逻辑将搜索范围限定为专门作为专业知识库内容导入的文档。该策略本身是一个薄包装层，将策略抽象与统一的 RAG 处理流水线桥接起来。
