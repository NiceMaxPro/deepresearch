# SmartAgentProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.smart-agents`。管理智能 Agent 系统的配置：`enabled`（是否启用，默认 true）、`searchPlatformMapping`（Map 结构，key 为 Agent 类型名，value 为 `SearchPlatformConfig` 包含 `primary` 字段指定首选搜索平台）。用于控制智能 Agent（学术研究、数据分析、百科、生活旅行等）的启用状态及其搜索平台偏好。
