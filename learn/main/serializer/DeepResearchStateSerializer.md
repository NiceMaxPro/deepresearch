# DeepResearchStateSerializer.java

继承自 `PlainTextStateSerializer`，为深度研究图状态提供基于 JSON 的序列化功能。它配置了一个自定义的 `ObjectMapper`，采用字段级别可见性（禁用 getter 自动检测），通过 Jackson 模块注册了 `MessageDeserializer` 和 `DeepResearchDeserializer`，并添加了 `JavaTimeModule` 以支持日期/时间类型。其关键创新在于 `writeData` 和 `readData` 方法，它们使用基于字节数组长度前缀的协议，而非默认的 UTF 字符串编码，以避免状态数据超过 UTF 长度限制时出现序列化失败。内容类型为 `"application/json"`。
