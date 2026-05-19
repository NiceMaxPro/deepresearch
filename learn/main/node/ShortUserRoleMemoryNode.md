# ShortUserRoleMemoryNode

该节点在对话轮次之间提取并维护关于用户角色、身份和偏好的短期记忆。它从 `ShortTermMemoryRepository` 中检索最近的用户查询，调用 LLM 代理将结构化的角色信息（如职位、专业水平和沟通偏好）提取到 `ShortUserRoleExtractResult` 对象中，并使用基于置信度的合并策略持久化结果。当当前提取的置信度高于历史记录时，该节点调用合并更新步骤，请求 LLM 智能地融合新旧角色信息（考虑历史轨迹和相似度阈值）。该记忆通过 `short_user_role_memory` 状态键暴露给下游节点，其引导行为由 `GuideScope` 配置控制（NONE、ONCE 或 ALWAYS）。
