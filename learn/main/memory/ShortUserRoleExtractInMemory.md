# ShortUserRoleExtractInMemory.java

一个 Spring `@Component`，使用 `ConcurrentHashMap` 提供 `ShortTermMemoryRepository` 接口的线程安全内存实现。它维护三个独立的映射：`userQueryMemory`（按对话 ID 键存储原始用户消息）、`shortTermMemoryTrack`（按 "userId:conversationId" 键存储完整的消息历史轨迹）和 `shortTermMemory`（每个用户-对话只存储最近一条提取的消息）。`resolveCreateTime` 辅助方法从消息元数据中提取 `LocalDateTime`，用于在 `getRecentUserMessages` 和 `getRecentUserQueries` 中进行按时间排序，当元数据缺失时默认为 `LocalDateTime.MIN`。`saveOrUpdate` 方法将新消息追加到现有轨迹或初始化轨迹，并始终用新批次中的第一个元素更新最近消息缓存。
