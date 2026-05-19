# SessionContextService.java

此接口定义了管理聊天会话上下文的契约，用于跟踪会话与关联的图执行线程之间的关系。它声明了添加会话历史条目（`addSessionHistory`）、获取属于某个会话的所有线程 ID（`getGraphThreadIds`）、获取会话中特定线程的报告（`getReports`）以及获取最近 N 个报告（`getRecentReports`）等方法。`getRecentReports(String sessionId)` 的重载默认方法默认返回最近 5 个报告。该接口由 `InMemorySessionContextService` 实现。
