# JsonUtil.java

静态工具类，提供一个集中式的 `ObjectMapper` 用于 JSON 序列化和反序列化。该映射器在静态初始化块中配置为：忽略未知属性、不因空 Bean 而失败、使用标准日期时间格式（`yyyy-MM-dd HH:mm:ss`）、注册 `Jdk8Module` 和 `JavaTimeModule`，并在输出中排除空值。它提供了 `toJson(Object)` 用于序列化，以及两个重载的 `fromJson` 方法——一个接受 `Class<T>`，另一个接受 `TypeReference<T>` 用于泛型类型。转换过程中的错误会被记录日志，并返回 null 或空字符串，而不是抛出异常。
