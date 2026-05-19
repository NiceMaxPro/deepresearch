# ModelParamRepository.java

一个简单的仓库接口，定义了加载智能体模型配置数据的契约。它声明一个方法 `loadModels()`，返回 `List<ModelParamRepositoryImpl.AgentModel>`，其中 `AgentModel` 是一个包含 `name` 和 `modelName` 字段的记录。该接口由 `ModelParamRepositoryImpl` 实现。
