# McpClientUtil.java

用于按智能体、按线程创建和初始化 MCP（模型上下文协议）异步客户端的工具类。`createMcpProvider` 接受图状态、智能体名称（例如 "coderAgent"、"researchAgent"）、配置提供者函数以及基础设施组件（Configurer、通用属性、WebClient 构建器、ObjectMapper）。它从状态中检索智能体的 MCP 服务器配置，过滤出已启用的服务器，通过 `McpConfigMergeUtil.createAgent2McpTransports` 动态创建 `WebFluxSseClientTransport` 实例，构建并初始化每个 `McpAsyncClient`（具有 2 分钟初始化超时），并将所有客户端包装在一个 `AsyncMcpToolCallbackProvider` 中。`isMcpConfigurationAvailable` 对所有必需的基础设施组件执行空值检查。
