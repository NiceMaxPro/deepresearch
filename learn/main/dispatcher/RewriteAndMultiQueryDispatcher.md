# RewriteAndMultiQueryDispatcher.java

一个 `EdgeAction` 实现，在查询重写和多查询生成节点之后路由执行流程。它从 `OverAllState` 中读取 `rewrite_multi_query_next_node`，默认为 `END`。该调度器使查询重写智能体能够根据重写后的查询上下文，将工作流引导至下一个合适的节点。
