# RetrievalStrategy.java

一个接口，定义了针对特定数据源的检索策略的契约。它声明了两个方法：`retrieve()` 接受一个查询字符串和一个选项 map（如 `session_id`、`user_id` 或 `selected_knowledge_bases`），返回相关 `Document` 对象的列表；`getStrategyName()` 返回用于标识和配置的唯一名称。有三个实现：用于用户上传文件的 `UserFileRetrievalStrategy`、用于基于 Elasticsearch 的专业知识库的 `ProfessionalKbEsStrategy`，以及用于基于 API 的专业知识库的 `ProfessionalKbApiStrategy`。此策略模式使系统能够根据上下文将搜索分发到适当的数据源。
