# DocumentSelectFirstProcess.java

Spring AI 的 `DocumentPostProcessor` 接口的一个简单实现，仅返回输入列表中的第一个文档，丢弃所有其他文档。如果输入的文档列表为空，则返回空列表；否则，将第一个元素包装在单元素列表中返回。当 RAG 流水线配置了 `postProcessingSelectFirstEnabled` 开关时，使用此后处理器，提供一种简单的"取最顶部结果"策略，作为更复杂的重排序或融合方法的轻量级替代方案。
