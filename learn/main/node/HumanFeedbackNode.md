# HumanFeedbackNode

该节点实现了计划验证的人机协同（Human-in-the-Loop）反馈机制。它首先检查是否超过了最大计划迭代次数；如果超过，则直接路由到 `research_team` 继续执行。否则，它会递增迭代计数器，并从状态中读取 `humanFeedback` 数据以检查用户是否批准了该计划。如果反馈为正面（`feedback=true`），则路由到 `research_team` 开始执行。如果反馈为负面，则路由回 `planner` 进行计划重新生成，可选地传递用户指定的 `feedback_content` 来指导修订。当需要重新规划时，该节点还会调用 `state.withoutResume()` 来清除恢复状态。
