# RagDataAutoConfiguration.java

Spring `@Configuration` 类，实现 `ApplicationRunner` 接口，负责 RAG 数据的自动化摄入。应用启动时从配置的 `locations` 加载初始数据文档并通过 `VectorStoreDataIngestionService` 摄入向量存储。通过 `@Scheduled` 定时任务（cron 表达式可配置）扫描指定目录，自动发现新文档、摄入向量存储后移动到归档目录。通过 `@ConditionalOnProperty` 仅在 `rag.enabled=true` 时启用，`@EnableScheduling` 启用调度功能。
