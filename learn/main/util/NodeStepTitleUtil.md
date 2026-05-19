# NodeStepTitleUtil.java

用于在图 `OverAllState` 中注册人类可读的步骤标题标签的工具类，在并行节点执行期间使用。其唯一的 `registerStepTitle` 方法构建一个复合节点标识符（`prefix_executorNodeId`），构造一个格式化的标题字符串，该字符串可以可选地前置反思标记（`[反思]`）和包含节点类型和执行器 ID 的并行节点标记，并使用 `ReplaceStrategy` 将标题键注册到状态中（以便在并发场景下最新的标题胜出）。该方法返回 `nodeNum` 标识符，调用者将其用作流式聊天生成的 `startingNode`。
