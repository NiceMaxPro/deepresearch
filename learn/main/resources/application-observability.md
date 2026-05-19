# application-observability.yml

此 Spring 配置文件为 DeepResearch 应用程序设置了基于 OpenTelemetry 的可观测性，主要针对 Langfuse 集成进行 LLM 追踪和监控。它通过 HTTP 暴露所有管理端点，将追踪采样概率设置为 1.0（捕获每个请求），并启用基于注解的观测。OpenTelemetry 服务命名为 `spring-ai-alibaba-deepresearch-langfuse`，部署环境属性为 `development`。

追踪和指标通过 OTLP 协议导出到 Langfuse 的美国数据区域端点（`us.cloud.langfuse.com`），并附有欧盟区域和本地部署的备选方案（已注释掉）。日志导出显式设置为 `none`，因为 Langfuse 目前不支持日志摄入。认证使用 Base64 编码的 `public_key:secret_key` 对，作为 Basic Authorization 请求头传递。使用的协议为 `http/protobuf`。
