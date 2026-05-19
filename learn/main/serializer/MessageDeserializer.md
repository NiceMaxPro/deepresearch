# MessageDeserializer.java

自定义 Jackson `JsonDeserializer`，用于反序列化 Spring AI 的 `Message` 对象。它在静态初始化块中维护了一个按消息类型（USER、ASSISTANT、SYSTEM、TOOL）为键的 `MessageFactory` lambda 表达式的 `ConcurrentHashMap`，采用工厂方法模式进行实例创建。在反序列化过程中，它从 JSON 节点中提取 `messageType`（依次检查 `messageType`、`type` 和 `role` 字段）、`content`、`metadata`、`toolCalls` 和 `toolResponses`，然后委托给相应的工厂方法。纯文本节点默认为 `UserMessage`，未知类型则回退到 USER 并记录一条警告日志。
