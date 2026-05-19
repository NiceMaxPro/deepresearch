# ReflectionUtil.java

静态辅助类，提供与步骤执行逻辑相关的反思工具方法。`shouldProcessStep` 通过将步骤的 `executionStatus` 与已知的状态前缀（已分配、等待处理、等待反思）进行比较，来判断给定的步骤是否应当由特定节点处理。`buildReflectionHistoryContent` 根据步骤的反思历史生成 Markdown 格式的字符串，包括每次尝试的先前执行结果、反馈以及通过/失败评估。`getCompletionStatus` 根据反思处理器是否可用，返回相应的下一状态字符串，在 `waiting_reflecting_` 和 `completed_` 前缀之间进行选择。`hasReflectionHistory` 和 `shouldContinueAfterReflection` 提供便捷的状态检查。
