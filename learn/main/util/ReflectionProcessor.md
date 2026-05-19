# ReflectionProcessor.java

核心反思逻辑，负责协调研究/编码步骤的质量评估和重新处理决策。它接受一个 `ChatClient`（反思智能体）和最大反思尝试次数。核心方法 `handleReflection` 检查步骤的 `executionStatus`：如果状态指示等待反思，则调用 `performReflection`，该方法会检查尝试次数是否超过最大值，通过 `evaluateStepQuality` 调用反思智能体（构建包含任务标题、描述和执行结果的结构化评估提示），并使用 `BeanOutputConverter` 将响应解析为 `ReflectionResult` Bean。如果质量通过或已达到最大尝试次数，则步骤被标记为已完成；否则标记为需要重新处理。评估过程中的错误默认视为通过。包含内部类 `ReflectionHandleResult`，提供工厂方法 `continueProcessing()` 和 `skipProcessing()`。
