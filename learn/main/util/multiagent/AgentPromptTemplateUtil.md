# AgentPromptTemplateUtil.java

用于从类路径 Markdown 文件加载多智能体提示模板的工具类。`getSystemPrompt` 接受 `AgentType` 枚举，并使用 `ResourceUtil.loadFileContent` 加载由 `agentType.getPromptFilePath()` 返回路径上的提示文件。硬编码的便捷方法 `getClassificationPrompt` 和 `getSearchPlatformSelectionPrompt` 分别从 `prompts/multiagent/classifier.md` 和 `prompts/multiagent/search-platform-selector.md` 加载特定模板。`buildCompletePrompt` 通过将智能体的系统提示与 `agentType.getCitationGuidance()` 拼接来构建完整提示，后者提供特定于每种智能体类型的引文格式说明。
