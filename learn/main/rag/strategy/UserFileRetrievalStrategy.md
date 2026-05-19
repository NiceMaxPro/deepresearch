# UserFileRetrievalStrategy.java

一种搜索存储在向量数据库中的用户上传文件的检索策略。它实现 `RetrievalStrategy` 接口，策略名称为 `"userFile"`。`retrieve()` 方法首先检查选项 map 中是否存在 `session_id`——如果不存在，则返回空列表，因为用户文件始终限定在某个会话范围内。然后它委托给 `HybridRagProcessor.process()`，将 `source_type` 设置为 `USER_UPLOAD` 并传入会话 ID，以便处理器的过滤逻辑将搜索限制为属于该特定用户会话的文档。这确保了不同用户上传内容之间的隔离。
