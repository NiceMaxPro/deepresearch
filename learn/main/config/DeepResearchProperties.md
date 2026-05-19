# DeepResearchProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch`。管理深度研究的三个核心配置：`parallelNodeCount`（Map 结构，key 为节点名称如 researcher/coder，value 为并行实例数量）；`mcpClientMapping`（Agent 名称到 MCP 客户端名称集合的映射）；`maxIterations`（图执行最大迭代次数，默认 50）；以及 `searchList`（`SearchEnum` 列表，定义启用的搜索引擎）。通过 getter/setter 提供属性访问，在 `application.yml` 中配置。
