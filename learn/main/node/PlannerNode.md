# PlannerNode

该节点使用 LLM 代理生成结构化的研究计划。它通过组合用户的原始查询、短期角色记忆、背景调查结果、任何人工反馈内容以及用户上传的 RAG 数据来组装丰富的上下文。提示使用 `BeanOutputConverter` 进行格式化，以确保 LLM 生成的输出符合 `Plan` 数据结构。计划生成以 `Flux<ChatResponse>` 的形式流式传输，并通过 `FluxConverter` 转换为 `Flux<GraphResponse<StreamingOutput>>`，以便实时显示进度。输出存储在图形状态的键 `planner_content` 下，并注册步骤标题 `[正在制定研究计划]` 用于 UI 反馈。
