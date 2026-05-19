# ExportConfiguration.java

Spring `@Configuration` 导出服务配置类，启用 `ExportProperties` 配置属性。创建 `ExportService` Bean，传入导出路径（来自 `ExportProperties.getPath()`，默认为用户目录下的 `reports` 文件夹）。提供报告导出功能的基础服务配置。
