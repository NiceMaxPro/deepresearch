# IdentifiedRole.java

一个 DTO，表示系统基于对话分析对用户职业或个人角色的推断。它包含 `possibleIdentities`（候选角色列表）、`primaryCharacteristics`（定义该角色的关键特征）、`evidenceSummary`（支持该角色识别的对话证据摘要）以及 `confidenceLevel`（一个 `ConfidenceLevel` 枚举值：LOW、MEDIUM、MEDIUM_HIGH 或 HIGH）。所有字段都使用 `@JsonProperty` 进行 JSON 序列化。该类嵌入在 `ShortUserRoleExtractResult` 中，帮助系统根据用户的领域和专业水平来定制响应。
