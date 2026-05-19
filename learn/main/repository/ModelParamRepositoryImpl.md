# ModelParamRepositoryImpl.java

此 `@Repository` 类通过从类路径 JSON 文件（`model-config.json`）加载智能体模型参数来实现 `ModelParamRepository`。构造函数使用 Jackson 的 `ObjectMapper` 读取 JSON 并将其反序列化为 `Map<String, List<AgentModel>>`，提取 `"models"` 键下的列表。`loadModels()` 方法返回此列表（如果键不存在则返回空列表）。`AgentModel` 记录（包含 `name` 和 `modelName` 字段）作为内部记录定义，该类包含一个 fixme 注释，指出如果将来需要支持外部数据源，则需要进行重新设计。
