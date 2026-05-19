# ResearchTeamDispatcher.java

一个 `EdgeAction` 实现，在研究团队智能体完成其工作后路由执行流程。它从 `OverAllState` 中读取 `research_team_next_node`，默认为 `"planner"`（不同于大多数其他调度器默认的 `END`）。这使得研究团队可以循环回到规划器进行额外的迭代，也可以继续执行到下游节点（如报告生成器）。
