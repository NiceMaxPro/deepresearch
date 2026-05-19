# InfoCheckService.java

此 `@Component` 使用 AI 驱动的 `ChatClient`（`infoCheckAgent`）来过滤后台搜索结果，检查其敏感性和相关性。`backgroundInfoCheck` 方法从类路径资源（`backgroundInfoCheck.md`）中加载提示词模板，将用户查询和后台结果替换到模板中，然后针对每个结果条目调用 AI 模型，要求其返回包含 `is_sensitive` 和 `is_irrelevant` 布尔字段的 JSON 响应。只有既非敏感也非无关的结果才会被保留在输出列表中，并将其标题、URL 和内容拼接在一起。每次 AI 调用之间会插入 100 毫秒的延迟以限制请求速率。
