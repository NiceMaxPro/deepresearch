# HtmlGenerationUtil.java

最终工具类，将 Markdown 转换为完整的、自包含的 XHTML 文档，用于 PDF 渲染。它使用 commonmark-java 并启用 GFM 表格扩展，通过静态 `Parser` 将 Markdown 解析为 AST，再通过静态 `HtmlRenderer` 渲染为 HTML。`wrapHtmlContent` 方法构建一个完整的 XHTML 1.0 Strict 文档，包含外部 github-markdown CSS 样式表链接、针对阿里巴巴普惠体 TrueType 字体（从类路径加载）的内联 `@font-face` 声明，以及 body 元素上的 `markdown-body` CSS 类。资源（CSS 和字体）通过 `getResourceUrl` 解析，该方法查找类路径资源并返回其 URL 字符串，如果文件未找到则优雅降级并发出警告。
