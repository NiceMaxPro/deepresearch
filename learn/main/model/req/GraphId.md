# GraphId.java

一个实现了 `Serializable` 接口的 Java record，作为图工作流操作的复合键。它将 `sessionId` 和 `threadId` 组合成一个单一标识符，两者都使用 `@JsonProperty` 注解。该 record 在整个系统中用于将图执行状态与特定的用户会话和对话线程相关联，在 `SessionHistory`、`ChatRequest` 以及各种图状态映射中作为字段出现。
