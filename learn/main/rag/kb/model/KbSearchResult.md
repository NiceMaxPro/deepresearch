# KbSearchResult.java

一个 Java `record`，表示来自专业知识库 API 的单个搜索结果。它包含六个字段：`id`（文档标识符）、`title`（文档标题）、`content`（正文文本）、`url`（来源 URL）、`score`（相关性评分）和 `metadata`（用于附加属性的灵活映射）。提供三个便捷的静态工厂方法：`empty()` 返回所有字段为 null 的结果，`of(title, content)` 创建仅包含标题和内容的最小结果，`of(id, title, content, url, score)` 创建不包含元数据的结果。此记录作为通用数据传输对象，将不同知识库 API 的不同响应格式与 RAG 流水线的其余部分解耦。
