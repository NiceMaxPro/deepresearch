# SourceTypeEnum.java

一个枚举，定义了 RAG（检索增强生成）系统中三种支持的数据源类型的标识符。三个值分别是 `USER_UPLOAD`（用于用户上传的文件）、`PROFESSIONAL_KB_ES`（用于存储在 Elasticsearch 中的专业知识库）和 `PROFESSIONAL_KB_API`（用于通过外部 API 访问的专业知识库）。每个枚举常量都带有一个小写字符串值（`"user_upload"`、`"professional_kb_es"`、`"professional_kb_api"`），在整个代码库中用于元数据过滤和来源类型识别。其他组件（如检索策略和混合 RAG 处理器）引用此枚举来将搜索范围限定到正确的数据源。
