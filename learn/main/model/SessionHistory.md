# SessionHistory.java

一个会话记录类，用于存储用户与 LLM 之间的交互结果。它包含三个核心字段：`graphId`（会话和线程的复合标识符）、`userQuery`（用户的原始问题）和 `report`（生成的研究报告）。关键方法 `convertToMessage()` 将存储的查询和报告转换为 Spring AI 的 `UserMessage` 和 `AssistantMessage` 类型，使会话历史能够作为上下文反馈到后续的 LLM 交互中。该类通过内部 `Builder` 类使用建造者模式，提供了 `sessionId()` 和 `threadId()` 等便捷方法，用于逐步构建 `GraphId`。
