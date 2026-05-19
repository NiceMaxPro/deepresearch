# McpProviderFactory.java

此 `@Service` 充当图执行框架中创建 MCP（模型上下文协议）工具回调提供者的工厂类，通过 `mcp.assign-node.enabled` 属性条件启用。其核心方法 `createProvider(OverAllState state, String agentName)` 委托给 `McpClientUtil.createMcpProvider()` 来实例化一个 `AsyncMcpToolCallbackProvider`，使用注入的 MCP 配置提供者（`agent2mcpConfigWithRuntime`）、异步客户端配置器、通用属性、WebClient 构建器和 ObjectMapper。该工厂接收一个 `Function<OverAllState, Map<String, McpServerConfig>>`（限定为 `agent2mcpConfigWithRuntime`），基于当前图执行状态动态解析每个智能体的 MCP 服务器配置。
