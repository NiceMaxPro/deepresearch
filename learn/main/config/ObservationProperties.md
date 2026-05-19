# ObservationProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.observation`。管理可观测性功能的启用/禁用状态，仅有一个 `enabled` boolean 属性。被 `ObservationConfiguration` 使用，控制工具调用的日志记录和 Micrometer Observation 注册行为。
