# ConversationAnalysis.java

一个轻量级 DTO，记录关于用户对话行为的分析指标。它存储了一个 `confidenceScore`（一个 Double 值，表示系统对其理解用户程度的置信度）和一个 `interactionCount`（一个 Integer 值，追踪与该用户的交互次数）。两个字段都使用 `@JsonProperty` 进行 JSON 绑定。该类通常嵌入在 `ShortUserRoleExtractResult` 中，作为聚合用户记忆画像的一部分。
