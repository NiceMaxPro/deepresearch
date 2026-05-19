# Plan.java

研究计划模型，表示 AI 的结构化研究策略。它包含一个 `title`（标题）、一个用于记录规划者推理过程的 `thought`（思考）字段、一个 `hasEnoughContext`（是否有足够上下文）标志，以及一个 `Step`（步骤）对象列表。每个内部 `Step` 定义一个研究工作的单元，包含以下字段：`needWebSearch`（是否需要网络搜索）、`title`（标题）、`description`（描述）、`stepType`（一个内部枚举，包含 `RESEARCH` 和 `PROCESSING` 值，同时使用 `@JsonProperty` 和 `@JsonAlias` 以实现灵活的反序列化）、`executionRes`（执行结果）、`executionStatus`（执行状态）以及 `reflectionHistory`（反思历史）列表。`reflectionHistory` 字段追踪一个步骤的所有反思评估记录，提供了延迟初始化的 getter 方法以及 `addReflectionRecord` 便捷方法，用于在研究循环中追加结果。
