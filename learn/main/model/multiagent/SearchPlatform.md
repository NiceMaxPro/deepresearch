# SearchPlatform.java

一个枚举，将 10 个搜索平台映射到它们支持的代理类型，形成了多代理系统的搜索路由配置。平台包括学术工具（`OPENALEX`、`GOOGLE_SCHOLAR`）、旅行工具（`OPENTRIPMAP`、`TRIPADVISOR`）、知识工具（`WIKIPEDIA`）、数据工具（`WORLDBANK_DATA`）以及通用搜索引擎（`TAVILY`、`ALIYUN_AI_SEARCH`、`BAIDU_SEARCH`、`SERPAPI`）。每个常量存储一个 `code`（代码）、一个中文显示 `name`（名称）、一个 `description`（描述）以及一个 `supportedAgents`（支持的代理）列表，将其与一个或多个 `AgentType` 值关联起来。像 TAVILY 和 SERPAPI 这样的平台支持多种代理类型（例如 GENERAL_RESEARCH、ACADEMIC_RESEARCH 和 DATA_ANALYSIS），而像 OPENTRIPMAP 这样的专用平台则只专注于单一代理类型。
