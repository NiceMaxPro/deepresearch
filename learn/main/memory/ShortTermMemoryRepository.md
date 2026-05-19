# ShortTermMemoryRepository.java

一个接口，定义了 deepresearch 系统中短期记忆存储操作的契约。它声明了七个方法：`getRecentUserMessages` 和 `getRecentUserQueries`（按对话 ID 检索最近的用户输入，支持数量限制）、`saveUserQuery`（将用户消息持久化到对话作用域的存储中）、`findMessageTrack` 和 `findLatestExtractMessage`（检索用户-对话对的完整记忆轨迹和最近一条提取的消息）、`saveOrUpdate`（将消息追加到轨迹中并更新最近提取的消息）以及 `deleteBy`（删除用户-对话对的所有记忆条目）。该接口抽象了存储后端，允许不同的实现（内存、Redis、数据库）进行替换。
