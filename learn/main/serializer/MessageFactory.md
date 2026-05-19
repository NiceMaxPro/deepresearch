# MessageFactory.java

一个 `@FunctionalInterface`，定义了创建 Spring AI `Message` 实例的工厂契约。它声明了一个 `create` 方法，接受 `textContent`（String）、`metadata`（Map）、`toolCalls`（`AssistantMessage.ToolCall` 列表）和 `toolResponses`（`ToolResponseMessage.ToolResponse` 列表）参数。该接口被 `MessageDeserializer` 用于将消息类型检测与对象构造解耦，允许每种消息类型（USER、ASSISTANT、SYSTEM、TOOL）以 lambda 表达式的形式注册到反序列化器的静态工厂映射中。
