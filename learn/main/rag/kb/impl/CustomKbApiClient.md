# CustomKbApiClient.java

`ProfessionalKbApiClient` 的一个实现，支持通用的基于 REST API 的知识库。它使用 Spring 的 `RestClient` 向可配置的 URL 发送 HTTP GET 请求，如果提供了 API 密钥则将其作为 Bearer 令牌授权头附加，并将查询和结果限制作为 URL 参数传递。响应解析逻辑（`parseResponse()`）设计用于处理多种常见的 JSON 响应格式：首先尝试从名为 `results`、`data`、`items`、`documents` 或 `docs` 的字段中提取条目，如果失败则将单个对象（包含 `content` 或 `title` 字段）作为单一结果处理。字段映射同样灵活，支持每个逻辑属性的多种替代字段名（例如，文档内容对应 `content`/`text`/`body`/`description`）。这使得客户端无需修改代码即可适配各种外部知识库 API。
