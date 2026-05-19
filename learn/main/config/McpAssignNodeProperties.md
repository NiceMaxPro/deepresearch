# McpAssignNodeProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.mcp`。管理 MCP 代理节点分配配置：`enabled`（是否启用，默认 true）、`configLocation`（配置文件路径，默认 `classpath:mcp-config.json`）。包含两个内部 record 类——`McpServerConfig`（包含 `mcpServers` 列表）和 `McpServerInfo`（包含 url、sse-endpoint、description、enabled 字段），用于反序列化 JSON 配置中的 MCP 服务器信息。
