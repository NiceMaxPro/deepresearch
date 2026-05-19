# RewriteAndMultiQueryNode

该节点将用户的原始查询转换为一组优化的搜索查询，以获得更好的检索覆盖率。它首先可选地使用 `CompressionQueryTransformer` 将对话历史压缩到查询上下文中（当启用短期记忆时），然后通过 `RewriteQueryTransformer` 改写查询以提高清晰度和搜索友好性。改写后的查询通过 `MultiQueryExpander` 扩展为多个子查询，查询数量根据配置（`optimizeQueryNum`）限制在 0 到 5 之间。原始查询默认包含在结果中。查询处理完成后，如果用户上传了文件，该节点路由到 `user_file_rag`，否则路由到 `background_investigator`。
