# ChatController.java

深度研究对话 API 的主入口——流式聊天控制器。它暴露一个 `POST /chat/stream` SSE（服务器推送事件）端点，接收 `ChatRequest` 并返回响应式 `Flux<ServerSentEvent<String>>`，将流式聊天响应推送给前端。该控制器编译一个 `StateGraph`（限定名为 `"deepResearch"`），配置了基于内存的检查点保存机制，并在 `"human_feedback"` 处设置中断点，以支持计划审核工作流。它将流处理委托给 `GraphProcess`，后者同时处理初始提问和人工反馈恢复两种场景。其他端点包括：用于取消正在运行的图执行的 `POST /chat/stop`，以及用于通过用户反馈恢复暂停图的 `POST /chat/resume`。
