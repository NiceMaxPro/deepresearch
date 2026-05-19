# MemoryConfig.java

Spring `@Configuration` 类，配置短期记忆的会话窗口记忆。通过 `@ConditionalOnProperty` 仅在 `short-term-memory.enabled=true` 时启用。创建 `MessageWindowChatMemory` Bean，使用 `InMemoryChatMemoryRepository` 作为存储后端，最大消息数从 `ShortTermMemoryProperties.conversationMemory.maxMessages` 读取（默认 100）。为 Coordinator 和 Reporter 等节点提供滑动窗口式的对话记忆能力。
