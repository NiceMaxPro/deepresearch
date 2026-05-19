# McpServerInfo.java

一个基于 record 的 DTO，携带 MCP（模型上下文协议）服务器信息，用于服务发现和展示。其字段包括 `agentName`、`agentDisplayName`、`url`、`description`、`enabled` 和 `serviceName`。该类提供了两个静态工具方法：`extractServiceName(String url)` 检查 URL 以识别已知服务（目前将 amap.com 识别为高德地图服务），以及 `getAgentDisplayName(String agentName)` 将内部代理名称（如 "coderAgent" 和 "researchAgent"）映射为人类可读的中文显示名称。
