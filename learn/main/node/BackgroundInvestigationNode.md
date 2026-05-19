# BackgroundInvestigationNode

该节点执行深度研究工作流的背景调查阶段。它接收改写/多查询步骤生成的优化查询，执行智能搜索选择（为每个查询选择最佳搜索平台），并通过 `SearchInfoService` 检索网络结果。对于每个查询，它将搜索结果连同最近的会话报告历史一起输入到一个专用的 `backgroundAgent` ChatClient 中，该客户端会生成一份简洁的背景调查报告。该节点收集所有逐查询的报告，并决定下一步：当启用深度研究模式时路由到 `planner`，对于较简单的查询则直接路由到 `reporter`。它将原始 `site_information` 和 `background_investigation_results` 都存储在输出状态中。
