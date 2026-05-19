# ReportService.java

一个定义报告存储契约的接口，包含四个方法：`saveReport`、`getReport`、`existsReport` 和 `deleteReport`，均以 `threadId` 为键。由 `ReportMemoryService`（基于内存的回退方案）和 `ReportRedisService`（基于 Redis）共同实现，运行时通过 `@ConditionalOnProperty` 根据 Redis 启用标志进行选择。
