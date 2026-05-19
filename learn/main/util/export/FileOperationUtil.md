# FileOperationUtil.java

最终工具类，为导出子系统提供文件 I/O 操作。`readFromFile` 和 `saveContentToFile` 使用 `Files.readString`/`Files.writeString` 读取/写入字符串内容，并自动创建父目录。`createDirectoryIfNotExists` 确保目录路径存在。两个 `generateFilename` 重载方法根据线程 ID 或自定义标题生成清理后的文件名，去除非法字符和空白符，将过长的标题截断至 50 个字符。两个 `getFileDownload` 重载方法构造 Spring `ResponseEntity<Resource>` 对象用于 HTTP 文件下载，第二个重载支持通过 RFC 5987 的 `filename*` 编码在 `Content-Disposition` 头中使用 UTF-8 编码的文件名。`fileExists` 提供简单的文件存在性检查。
