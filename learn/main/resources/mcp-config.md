# mcp-config.json

此 JSON 配置文件定义了研究代理可用的模型上下文协议（MCP）服务器连接。它包含一个 `researchAgent` 对象，其中包含一个 `mcp-servers` 数组，列出了可用的 MCP 服务器。当前定义了一个服务器：一个高德地图（Amap）MCP 服务器，通过 SSE（服务器发送事件）连接，使用来自 `AMAP_API_KEY` 环境变量的 API 密钥。此服务器默认禁用（`enabled: false`）。主 application.yml 中的 MCP 客户端集成通过 `classpath:mcp-config.json` 引用此文件，并且也默认禁用。
