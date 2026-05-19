# FeedbackRequest.java

一个 Java record，携带用户在人机协作工作流中对规划者所提议研究计划的反馈。它包含四个字段：`sessionId`（默认为 "\_\_default\_\_"）、`threadId`、`feedback`（一个布尔值，true 表示接受计划，false 表示重新生成）以及 `feedbackContent`（在请求重新生成计划时提供的可选附加上下文）。当系统在 `HUMAN_FEEDBACK` 节点暂停以等待用户批准或修改生成的研究计划，然后再继续执行时，会发送此对象。
