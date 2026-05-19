# ReflectionProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.reflection`。管理反思（Reflection）机制的配置：`enabled`（是否启用反思，默认 true）、`maxAttempts`（最大反思尝试次数，默认 2）。反思机制让 Researcher 和 Coder 节点在执行任务后自我审查和优化结果，此配置控制反思的行为和重试上限。
