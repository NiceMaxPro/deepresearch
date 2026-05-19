# AgentModelsConfiguration.java

Spring `@Configuration` 类，负责根据 `model-config.json` 配置文件为多智能体系统创建不同的 `ChatClient.Builder` Bean。实现 `InitializingBean` 接口，在属性设置完成后自动调用 `afterPropertiesSet()` 方法。核心逻辑：从 `ModelParamRepository` 加载模型配置列表，通过 `agentModels()` 方法将每个配置转为 `DashScopeChatModel` 实例，然后通过 `registerConsumer` 将它们注册为 Spring 单例 Bean（命名规则为 `{模型名}ChatClientBuilder`）。这使得不同的 Agent 可使用不同的 DashScope 模型实例。
