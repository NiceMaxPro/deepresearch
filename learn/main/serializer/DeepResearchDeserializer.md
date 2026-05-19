# DeepResearchDeserializer.java

自定义 Jackson `JsonDeserializer`，用于反序列化 `OverAllState` 对象。它从解析器中读取 JSON 树，提取 `"data"` 映射，并利用提供的 `ObjectMapper` 将 `current_plan` 字段显式转换为 `Plan` 对象，将 `search_engine` 字段转换为 `SearchEnum` 枚举值。数据映射中其余的所有键值对均被保留并传入新的 `OverAllState` 实例。这确保了像计划和搜索引擎这样的类型化字段能够被正确反序列化，同时未知的动态字段也能被透明地传递。
