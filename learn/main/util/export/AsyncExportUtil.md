# AsyncExportUtil.java

使用 `ThreadPoolTaskExecutor` 执行异步 PDF 生成的工具类。其唯一的 `saveAsPdfAsync` 方法接受报告内容、标题、基础路径和执行器，返回一个 `CompletableFuture<String>`，该 Future 解析为输出的 PDF 文件路径。内部流程为：通过 `FileOperationUtil.generateFilename` 生成清理后的文件名，使用 `FormatConversionUtil.convertMarkdownToPdfBytes` 将 Markdown 内容转换为 PDF 字节数组，将字节写入磁盘，并记录完成日志。失败时记录错误并在 Future 中包装抛出 `RuntimeException`。
