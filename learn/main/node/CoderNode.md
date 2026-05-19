# CoderNode

该节点执行由 `ParallelExecutorNode` 分配给它的编码/处理步骤。它会找到当前计划中第一个未完成的 `PROCESSING` 类型步骤，可选地处理反思逻辑（使用 `ReflectionProcessor` 提供的反馈重新尝试之前失败的步骤），并构建一个包含步骤标题、描述、语言区域和任何先前反思历史的任务提示。该节点调用一个 AI 代理（可选用 `McpProviderFactory` 提供的 MCP 工具回调来访问外部工具），并将响应作为 `Flux<GraphResponse<StreamingOutput>>` 进行流式传输。每个实例通过 `executorNodeId` 后缀来标识，允许多个编码节点并行运行。完成后，它会将该步骤标记为完成状态，并将生成的代码内容存储在键 `coder_content_<executorNodeId>` 下。
