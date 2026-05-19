# ExportService.java

此 Spring `@Service` 类负责将研究报告导出为多种文件格式，并作为可下载响应提供。它支持 Markdown（`.md`）和 PDF 格式，通过静态的 `SUPPORTED_FORMATS` 集合进行格式校验。该服务委托 `ReportService` 获取报告内容，使用 `FileOperationUtil` 进行文件 I/O（保存、检查是否存在、生成文件名），并使用 `FormatConversionUtil` 进行 Markdown 到 PDF 的转换。关键方法包括 `saveAsMarkdown(String threadId)`、`saveAsPdf(String threadId)` 和 `downloadReport(String threadId, String format)`，后者返回一个 `ResponseEntity<Resource>`，用于带有适当 MIME 类型的 HTTP 文件下载。它还通过正则表达式从报告内容中提取第一个 H1 标题，以生成可读的下载文件名。
