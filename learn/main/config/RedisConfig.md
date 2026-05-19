# RedisConfig.java

Spring `@Configuration` 类，配置 Redis 数据访问。通过 `@ConditionalOnProperty` 仅在 `spring.data.redis.enabled=true` 时启用。创建 `RedisTemplate<String, Object>` Bean，使用 `StringRedisSerializer` 序列化键（含 HashKey），使用 `GenericJackson2JsonRedisSerializer` 序列化值（含 HashValue 和默认序列化器），实现 JSON 格式的对象存储和检索。为 `ReportRedisService` 等服务提供 Redis 操作基础。
