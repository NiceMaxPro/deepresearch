# ExportProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.export`。管理报告导出功能的配置，目前仅有一个属性：`path`（导出文件路径，默认 `${user.home}/reports`）。控制生成的 Markdown 和 PDF 报告的存储位置。
