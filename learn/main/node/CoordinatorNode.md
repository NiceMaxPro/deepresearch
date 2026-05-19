# CoordinatorNode

这是入口路由节点，用于确定对话的整体走向。它组装一个消息列表，包括用户的短期角色记忆、协调器模板指令、来自 `MessageWindowChatMemory` 的可选会话历史以及用户当前的查询。然后该节点调用 `coordinatorAgent` ChatClient 并检查响应：如果检测到工具调用，则将 `deep_research` 设置为 true 并路由到 `rewrite_multi_query`，触发完整的深度研究流水线。如果没有工具调用，它将查询视为简单聊天，直接存储助手的文本输出，工作流结束。在启用时，它还会将用户消息和助手响应持久化到短期记忆中。
