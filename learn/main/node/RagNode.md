# RagNode

该节点执行检索增强生成（RAG）流水线，使用索引文档来回答查询。它支持两种检索路径：新的统一 `HybridRagProcessor`（在一次调用中处理检索前处理、检索和后处理）和传统的基于策略的方法（多个 `RetrievalStrategy` 实例独立检索文档，然后由 `FusionStrategy` 合并结果）。检索到相关文档后，该节点构建一个上下文字符串，并通过 `ragAgent` 流式传输 LLM 生成的响应，应用 180 秒超时和最多 2 次重试以增强弹性。流式输出被包装为 `Flux<GraphResponse<StreamingOutput>>` 并存储在键 `rag_content` 下。
