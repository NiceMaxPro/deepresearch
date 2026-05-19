# application.yml

这是 Spring AI Alibaba DeepResearch 应用程序的主要 Spring Boot 配置文件。它将服务器端口设置为 8080，配置了文件大小限制为 10MB、请求限制为 100MB 的多部分文件上传，并激活了 `observability` 和 `kb` Spring 配置文件。Redis 数据存储已配置但默认禁用，使用 Lettuce 连接池连接到 localhost:6379。DashScope AI API 密钥和基础 URL 配置用于嵌入（text-embedding-v1）和通用 AI 使用。

在 `spring.ai.alibaba.toolcalling` 下定义了多个搜索工具集成，包括 Tavily（启用）、百度搜索（启用）、SerpAPI（启用）和阿里云 AI 搜索（启用），而 Jina Crawler、OpenAlex、OpenTripMap、Wikipedia、WorldBank 和 Google 学术默认禁用。深度研究（deep research）部分配置了最多 50 次图执行迭代、包含四个搜索引擎（tavily、aliyun、baidu、serpapi）的搜索列表，以及并行执行时 4 个研究者（researcher）和 4 个编码者（coder）节点。RAG 默认禁用，使用 simple 向量存储类型；反思（reflection）已启用，最多 1 次尝试；短期记忆（short-term memory）已启用，使用内存存储，对话记忆上限为 100 条消息，记忆更新的用户角色相似度阈值为 0.8。
