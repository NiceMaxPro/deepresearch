# HttpClientConfiguration.java

Spring `@Configuration` 类，配置 HTTP 客户端基础设施。创建 `RestClient` Bean 用于 HTTP 调用，使用 Apache `HttpComponentsClientHttpRequestFactory` 作为底层请求工厂以获得更好的性能和配置选项。内部 `HttpClientProperties` 类作为 `@ConfigurationProperties`（前缀 `spring.ai.alibaba.deepresearch.http-client`），支持配置连接超时（默认 30s）、读取超时（默认 60s）、连接请求超时、最大连接数、连接池、重试机制等参数。还提供了 `RestClient.Builder` Bean 供其它组件自定义 RestClient。
