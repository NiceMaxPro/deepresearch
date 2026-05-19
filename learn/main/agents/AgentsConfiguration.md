# AgentsConfiguration.java

Spring `@Configuration` 类，定义所有 AI Agent 的 `ChatClient` Bean，是 Agent 创建的核心配置中心。为每种 Agent 类型创建对应的 Bean：`backgroundAgent`、`researchAgent`、`coderAgent`、`coordinatorAgent`、`plannerAgent`、`reporterAgent`、`interactionAgent`、`rewriteAndMultiQueryAgent`、`infoCheckAgent`、`reflectionAgent`、`ragAgent`、`shortMemoryAgent` 等。还通过 `@ConditionalOnProperty` 条件注解控制智能 Agent（`academicResearchAgent`、`lifestyleTravelAgent`、`encyclopediaAgent`、`dataAnalysisAgent`）的启用。每个 Agent 的创建过程加载对应的 prompt 模板，配置工具回调（包括 MCP 工具），并组装成完整的 `ChatClient` 实例。
