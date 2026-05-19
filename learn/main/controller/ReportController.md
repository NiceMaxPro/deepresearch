# ReportController.java

位于 `/api/reports` 的 REST 控制器，处理研究报告的完整生命周期。主要端点：`GET /{threadId}` 获取报告，`GET /{threadId}/exists` 检查报告是否存在，`DELETE /{threadId}` 删除报告。导出操作包括：`POST /export` 将报告保存为 Markdown 或 PDF 格式，`GET /download/{threadId}` 用于文件下载。`GET /build-html` 端点通过 `ChatClient` 流式传输交互式 HTML。
