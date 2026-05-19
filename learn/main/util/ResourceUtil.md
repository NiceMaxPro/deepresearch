# ResourceUtil.java

用于将静态类路径资源加载为字符串的工具类，主要用于读取提示模板文件。`loadResourceAsString` 接受一个 Spring `Resource` 对象，打开 `InputStream` 并使用 `StreamUtils` 将其复制为字符串，同时断言结果非空。`loadFileContent` 提供了一个便捷封装，从相对文件路径构造 `ClassPathResource` 并以 UTF-8 文本读取。两个方法在 IO 错误时均抛出 `RuntimeException`，要求调用者显式处理失败情况。
