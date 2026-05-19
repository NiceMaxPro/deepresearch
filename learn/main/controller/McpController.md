# McpController.java

一个 REST 控制器，通过 `GET /api/mcp/services` 端点提供 MCP（模型上下文协议）服务发现功能。它委托 `McpService` 获取所有已配置的 MCP 服务器信息（`List<McpServerInfo>`），并返回一个包含成功标志、消息、数据列表和服务摘要的响应 Map。该控制器逻辑简洁：记录传入请求、调用服务层、将结果封装为 `ResponseEntity<Map<String, Object>>`。
