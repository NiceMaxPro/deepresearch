# ExportData.java

一个基于 record 的 DTO，表示报告导出操作的结果。它包含以下字段：操作是否成功（`boolean success`）、导出 `format`（格式，如 "markdown" 或 "pdf"）、磁盘上的 `filePath`（文件路径）、供客户端检索的 `downloadUrl`（下载地址），以及仅在导出失败时填充的 `error`（错误信息）字符串。两个静态工厂方法（`success` 和 `error`）根据结果提供了清晰的构造路径，其中错误变体仅接受错误信息，而 format/path/url 则为 null。
