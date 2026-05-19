# McpAssignNodeConfiguration.java

Spring `@Configuration` 类，负责将 MCP（Model Context Protocol）服务器配置分配给指定的节点。通过 `@ConditionalOnProperty` 根据配置控制启用。核心功能包括：`agent2mcpConfig()` Bean 从 JSON 配置文件加载 Agent 到 MCP 服务器配置的静态映射；`agent2mcpConfigWithRuntime()` Bean 提供一个函数式接口，支持在运行时合并静态配置和来自 `OverAllState` 的动态 MCP 设置。使用 `McpConfigMergeUtil` 进行配置合并，使 MCP 客户端可以在节点运行时动态创建，而非启动时固定分配。
