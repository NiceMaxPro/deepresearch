# shortmemory-extract.md

此提示模板定义了一个短期记忆提取代理，专注于当前对话中的实时用户角色识别。它分析当前用户消息（`{{ last_user_message }}`）和历史用户消息（`{{ history_user_messages }}`），提取用户角色特征，用于 AI 响应的即时个性化。分析涵盖两个维度：技术能力评估（术语使用、查询具体性、问题表述方式）和沟通风格分析（语言正式程度、信息密度偏好、交互模式）。

输出是一个原始 JSON `ShortUserRoleExtractResult` 对象，包含三个子对象。`ConversationAnalysis` 包含置信度分数（0 到 1）和交互次数。`IdentifiedRole` 包含可能的身份（如"software_engineer"）、主要特征标签、证据摘要和置信度级别（LOW/MEDIUM/MEDIUM_HIGH/HIGH）。`CommunicationPreferences` 指定详细程度（CONCISE/BALANCE/COMPREHENSIVE）、内容深度（OVERVIEW/PRACTICAL/CONCEPTUAL）和响应格式（CONCISE/DETAILED/STRUCTURED_WITH_EXAMPLES）。`userOverview` 字符串用一句话总结用户。如果用户提供了自我描述，则优先于推断分析。输出遵循 `{{ locale }}` 模板变量。
