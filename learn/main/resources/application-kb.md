# application-kb.yml

此 Spring 配置文件定义了 DeepResearch 应用程序中用于检索增强生成（RAG）的专业知识库。它在 `spring.ai.alibaba.deepresearch.rag.professional-knowledge-bases` 键下声明了四个示例知识库，并设置 `decision-enabled: true` 标志，允许系统根据用户查询相关性来决定何时查询这些知识库。每个知识库具有 id、名称、描述、类型、启用状态和优先级值。

医疗知识库（优先级 10）使用 DashScope API 提供程序，配合 text-embedding-v2 模型和 30 秒超时。法律知识库（优先级 20）使用自定义 API 提供程序，指向一个示例法律搜索端点。技术文档知识库（优先级 30）使用 Elasticsearch 作为其后端类型。金融知识库（优先级 40）已配置但默认禁用。知识库的描述信息引导系统根据用户问题的领域来确定查询哪个知识库。
