# CommunicationPreferences.java

一个捕获用户沟通风格偏好的 DTO，由短期记忆系统用于个性化 AI 响应。它包含三个枚举类型字段：`detailLevel`（来自 `DetailLevel` 枚举：CONCISE、BALANCE 或 COMPREHENSIVE）、`contentDepth`（来自 `ContentDepth` 枚举：OVERVIEW、PRACTICAL 或 CONCEPTUAL）以及 `responseFormat`（来自 `ResponseFormat` 枚举：CONCISE、DETAILED 或 STRUCTURED_WITH_EXAMPLES）。每个字段都使用 `@JsonProperty` 注解进行 JSON 序列化。该类嵌入在 `ShortUserRoleExtractResult` 中，共同构成完整的用户画像。
