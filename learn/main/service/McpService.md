# McpService.java

此 `@Service` 处理 MCP 服务器发现和元数据聚合。它从类路径 JSON 文件（`mcp-config.json`）中加载 MCP 服务配置，解析包含 URL、描述和启用状态字段的智能体级服务器条目。`getAllMcpServices()` 方法通过遍历 JSON 中每个智能体的 `mcp-servers` 数组来返回 `McpServerInfo` DTO 列表。`createServiceSummary` 方法生成一个摘要映射，包含服务总数、启用数和禁用数的统计信息，以及去重后的可用服务名称列表。基于 URL 的服务名称提取委托给 `McpServerInfo.extractServiceName()`。
