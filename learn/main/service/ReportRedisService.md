# ReportRedisService.java

实现 `ReportService`，使用 Redis 作为后端存储，当 `spring.data.redis.enabled` 为 `true` 时激活。使用 `RedisTemplate<String, Object>`，键前缀为 `deepresearch:report:`，TTL 为 24 小时。所有 CRUD 方法构建带前缀的键，并将操作包装在 try-catch 中，将错误转换为 `RuntimeException` 并记录日志。
