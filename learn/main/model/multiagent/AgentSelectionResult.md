# AgentSelectionResult.java

一个不可变的结果类，表示智能代理选择过程的结果。它存储了 `selectedAgent`（一个 `ChatClient` 实例）、解析出的 `AgentType`（代理类型）、一个 `isSmartAgent` 布尔值（表示是否使用了智能代理路由路径）、一个人类可读的 `reason`（原因）字符串（解释选择依据），以及一个 `stateUpdate`（状态更新）映射，用于将配置变更传播到图工作流状态中。提供了两种构造函数重载：一种不带 stateUpdate（默认为空映射），另一种带显式的 stateUpdate。该类通过在构造函数中使用 `Map.of()` 进行守卫，确保 stateUpdate 非空。
