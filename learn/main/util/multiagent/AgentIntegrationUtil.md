# AgentIntegrationUtil.java

多智能体子系统的集成辅助类。`createSelectionHelper` 是一个工厂方法，通过组装 `SmartAgentProperties`、`SmartAgentDispatcherService`、`QuestionClassifierService` 和 `SearchPlatformSelectionService` 来构造 `SmartAgentSelectionHelperService`——集中化智能体选择管道的依赖装配。`isSmartAgentAvailable` 执行门控检查：它验证 `SmartAgentProperties` 非空且已启用，然后检查所有传入的服务对象均为非空，如果有任何必要项缺失则返回 false（并发出警告日志），以便调用者可以回退到原始的单智能体逻辑路径。
