# ShortTermMemoryProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.short-term-memory`。管理短期记忆系统的配置：`enabled`（全局开关）、`memoryType`（存储类型，目前支持 `IN_MEMORY`）、`userRoleMemory`（角色记忆配置，包含 `guideScope` 引导范围 NONE/ONCE/EVERY、`updateSimilarityThreshold` 更新相似度阈值、`historyUserMessagesNum` 历史消息数量）和 `conversationMemory`（对话记忆配置，`maxMessages` 最大消息数 100）。定义内部枚举 `GuideScope` 和 `MemoryType`。
