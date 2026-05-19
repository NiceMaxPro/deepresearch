# McpConfigMergeUtil.java

用于按智能体名称合并静态（application.yml）和动态（运行时）MCP 服务器配置的工具类。`mergeAgent2McpConfigs` 复制所有静态的智能体-服务器映射，然后遍历运行时设置——对于每个包含 `"mcp-servers"` 键的智能体条目，通过 Jackson 将值转换为 `McpServerConfig`，并使用 `mergeAgent2McpServers` 合并服务器列表。后者使用以服务器 URL 为键的 `LinkedHashMap` 进行去重（具有相同 URL 的动态服务器覆盖静态的，新 URL 则追加）。`createAgent2McpTransports` 为配置中每个已启用的服务器构建 `WebFluxSseClientTransport` 实例，克隆 WebClient 构建器模板，设置 SSE 端点（默认 `/sse`），并使用智能体名称和 URL 哈希为每个传输命名以实现唯一标识。
