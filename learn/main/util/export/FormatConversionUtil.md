# FormatConversionUtil.java

最终工具类，使用 openhtmltopdf 库将 HTML 和 Markdown 内容转换为 PDF。`convertHtmlToPdfBytes` 创建一个 `PdfRendererBuilder`，配置为快速渲染模式，使用具有短超时（1 秒连接/读取）的自定义 HTTP 流工厂，并加载类路径中的阿里巴巴普惠体字体。构建器将类路径根目录设为基础 URI，用于解析相对资源。`convertMarkdownToPdfBytes` 首先通过 `HtmlGenerationUtil.markdownToHtml` 将 Markdown 转换为 HTML，然后委托给 HTML 转 PDF 的管线。基于文件的变体（`convertHtmlToPdfFile`、`convertMarkdownToPdfFile`）通过 `FileOutputStream` 将生成的字节数组写入磁盘。
