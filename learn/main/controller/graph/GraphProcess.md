# GraphProcess.java

一个核心的图执行辅助类，管理深度研究工作流中已编译图运行的完整生命周期。它维护线程安全的 `ConcurrentHashMap` 集合来追踪每个会话的图 ID（`sessionCountMap`）和正在运行的图任务（`graphTaskFutureMap`），通过一个包含 10 个工作线程的固定线程池支持并行执行和取消操作。该类处理三个主要关注点：从会话字符串创建新的图 ID（`createNewGraphId`）；处理来自 `Flux` 或 `AsyncGenerator` 源的流式 `NodeOutput`，将其反序列化为 SSE 事件发送给前端（`processStream`）；以及停止/取消正在运行的图执行（`stopGraph`）。在构建输出内容时，它区分 LLM 流式节点和普通节点：流式节点使用 `buildLLMNodeContent` 并配合 `StreamNodePrefixEnum` 进行匹配，而普通节点则使用 `buildNormalNodeContent` 并配合 `NodeNameEnum` 路由来提取相应的状态数据（查询内容、规划器内容、研究团队内容、最终报告等）。
