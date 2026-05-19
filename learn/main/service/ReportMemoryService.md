# ReportMemoryService.java

实现 `ReportService`，使用基于内存的 `ConcurrentHashMap<String, String>` 作为存储，当 `spring.data.redis.enabled` 为 `false`（或未设置）时激活。提供 `saveReport`、`getReport`（不存在时返回 null）、`existsReport` 和 `deleteReport` 方法，所有方法都带有异常转 `RuntimeException` 的包装和日志输出。作为 Redis 不可用时的轻量级回退方案。
