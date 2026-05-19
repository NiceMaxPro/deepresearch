# ReportResponse.java

一个泛型 Java record，作为研究报告端点的标准 API 响应包装器。它包含四个字段：`threadId`（标识对话）、`status`（状态，取值为 "success"、"notfound" 或 "error"）、`message`（描述性字符串）以及 `data`（泛型载荷，在 JSON 中序列化为 `report_information`）。三个静态工厂方法提供了清晰的构造路径：`success(threadId, message, data)` 用于成功检索、`notfound(threadId, message)` 用于给定线程无结果存在的情况、`error(threadId, message)` 用于失败情况。与通用的 `ApiResponse` 不同，该类将 `threadId` 提升为顶层字段，以便客户端直接进行路由。
