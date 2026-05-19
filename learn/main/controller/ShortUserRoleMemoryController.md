# ShortUserRoleMemoryController.java

用于管理存储在 `ShortTermMemoryRepository` 中的短期用户角色记忆的 REST 控制器。它在 `/api/user/memory/` 下暴露四个端点：`GET /conversation` 从 `MessageWindowChatMemory` 中获取某个会话的完整对话历史；`GET /track` 获取指定用户-会话对的角色提取短期记忆消息轨迹；`GET /latest` 仅返回最近一条角色提取记忆消息；`POST /delete` 删除指定用户和会话的所有短期记忆条目。所有响应均封装在 `ApiResponse` 中以保持一致性。
