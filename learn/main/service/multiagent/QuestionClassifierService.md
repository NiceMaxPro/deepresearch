# QuestionClassifierService.java

此 `@Service` 使用 AI 模型（DashScope `ChatClient`）将用户问题分类为智能体类型，当 `smart.agent.enabled` 为 `true` 时条件启用。构造函数构建一个 `ChatClient`，使用从 `AgentPromptTemplateUtil.getClassificationPrompt()` 加载的默认系统提示词。`classifyQuestion(String question)` 方法将问题发送给 AI，通过 `SmartAgentUtil.parseAiClassification()` 将响应解析为 `AgentType` 枚举值，对于空输入则默认返回 `GENERAL_RESEARCH`。支持全部五种智能体类型：GENERAL_RESEARCH、ACADEMIC_RESEARCH、LIFESTYLE_TRAVEL、ENCYCLOPEDIA 和 DATA_ANALYSIS。
