# ExportRequest.java

一个 Java record，表示报告导出请求。它包含两个字段：`format`（格式，支持 "markdown"、"md" 或 "pdf" 的字符串，默认为 "markdown"）和 `threadId`（标识要导出哪个会话线程的报告，默认为 "\_\_default\_\_"）。两个字段都使用了带有默认值的 `@JsonProperty` 注解。这个最小化的请求对象被报告导出端点用于触发将已完成的研究报告转换为可下载文件。
