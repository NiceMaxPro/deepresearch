# RagVectorStoreConfiguration.java

Spring `@Configuration` 类，根据配置创建相应的 `VectorStore` Bean。包含两个内部配置类：`SimpleVectorStoreConfiguration`（当 `vector-store-type=simple` 时启用，创建 `SimpleVectorStore` 并从文件加载已有数据）和 `ElasticsearchVectorStoreConfiguration`（当 `vector-store-type=elasticsearch` 时启用，创建 `ElasticsearchRestClient` 和 `ElasticsearchVectorStore`，支持用户名/密码认证和索引配置）。两个 Bean 均以 `ragVectorStore` 名称注册，通过 `@ConditionalOnProperty` 按条件激活。
