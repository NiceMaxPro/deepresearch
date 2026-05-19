# DashScopeKbApiClient.java

`ProfessionalKbApiClient` 的一个实现，集成了阿里 DashScope 知识库文本搜索 API。它发送带有 JSON 请求体的 POST 请求，包含模型名称（默认为 `"text-embedding-v1"`）、查询输入和参数（包括最大结果数）。响应从 DashScope 特有的格式解析，该格式将搜索结果节点嵌套在 `output.nodes` 下，提取 `id`、`title`、`content`、`url`、`score` 等字段以及任何额外的元数据。客户端的可用性仅取决于 API 密钥是否已配置且非空。默认端点为 `https://dashscope.aliyuncs.com/api/v1/services/knowledge-base/text-search`，但可以在配置中指定自定义 URL。
