# ResearchTeamNode

这是一个轻量级路由节点，用于检查当前计划中的所有步骤是否已完全执行完毕。它调用 `areAllExecutionResultsPresent` 来验证每个步骤是否都有完成或出错状态的执行状态。如果仍有步骤未执行，则路由到 `parallel_executor` 继续将工作分派给研究者和编码节点。如果所有步骤都已完成，则路由到 `professional_kb_decision`，在进入报告生成阶段之前可选地查询专业知识库。该节点将其路由决策存储在键 `research_team_next_node` 下。
