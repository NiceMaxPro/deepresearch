# InformationNode

该节点使用 `BeanOutputConverter` 将 LLM 生成的原始计划文本（`planner_content`）反序列化为结构化的 `Plan` 对象。如果反序列化成功，则将 `current_plan` 存储在状态中，并路由到 `human_feedback` 等待用户批准（除非计划被自动接受，在这种情况下直接转到 `research_team`）。如果反序列化失败，则检查是否已达到最大计划迭代次数：如果未达到，则递增迭代计数器并路由回 `planner` 进行重新生成；如果已达上限，工作流结束。该节点充当规划器非结构化输出与结构化执行流水线之间的验证关口。
