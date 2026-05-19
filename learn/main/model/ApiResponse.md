# ApiResponse.java

一个通用的 Java record 类，作为 deepresearch 应用的标准 API 响应包装器。它封装了四个字段：`code`（类似 HTTP 的状态码）、`status`（成功/错误字符串）、`message`（描述性文本）和 `data`（类型为 T 的泛型载荷）。该类提供了静态工厂方法 `success(T data)`（返回 200 响应）以及 `error(String message)` / `error(String message, T data)`（返回 500 响应），确保所有控制器端点具有一致的响应格式。
