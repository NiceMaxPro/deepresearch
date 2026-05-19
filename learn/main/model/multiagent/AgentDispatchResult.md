# AgentDispatchResult.java

一个不可变的结果类，封装了将研究任务分派给特定代理的结果。它持有对选定的 `ChatClient agent`、`AgentType`（代理类型）、`SearchEnum` 搜索平台列表、`searchStrategy`（搜索策略）字符串、`success`（成功）标志以及用于失败情况的 `errorMessage`（错误信息）的引用。`getStateUpdate()` 方法委托给 `SmartAgentUtil.createSmartAgentStateUpdate()`，在分派成功时为图工作流生成状态更新映射，失败时返回空映射。该类桥接了代理选择和图状态变更。
