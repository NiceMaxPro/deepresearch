# ChatRequest.java

一个 Java record，表示前端发送的完整聊天/研究请求载荷。它包含 13 个带有 `@JsonProperty` 默认值的字段：`sessionId`（默认为 "\_\_default\_\_"）、`threadId`（线程 ID）、`maxPlanIterations`（最大计划迭代次数，默认 1）、`maxStepNum`（最大步骤数，默认 3）、`autoAcceptPlan`（自动接受计划，默认 true）、`interruptFeedback`（中断反馈）、`enableDeepResearch`（启用深度研究，默认 true）、`mcpSettings`（一个灵活的 Map，用于 MCP 配置）、`query`（用户的研究问题）、`searchEngine`（一个 `SearchEnum`，默认为 TAVILY）、`enableSearchFilter`（启用搜索过滤，默认 true）、`optimizeQueryNum`（优化查询数量，默认 3）以及 `isUploadFile`（用户是否上传了文件，默认 false）。该 record 作为聊天流式端点的主要输入契约，控制从查询接收到执行参数的整个深度研究工作流。
