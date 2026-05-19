# ChatRequestProcess.java

一个工具类，包含在图执行前标准化和初始化 `ChatRequest` 对象的静态方法。`getDefaultChatRequest` 方法为任何传入的请求应用合理的默认值：填充缺失的字段，如会话 ID（`"__default__"`）、查询内容（`"草莓蛋糕怎么做呀"`）、搜索引擎（来自 `SearchBeanUtil` 的第一个可用引擎）、最大计划迭代次数（1）、最大步骤数（3）、自动接受计划（true）、以及启用深度研究（true）。`updateThreadId` 方法使用指定的线程 ID 创建一个新的 `ChatRequest`，`initializeObjectMap` 则填充一个 `Map<String, Object>`，包含所有相关请求字段（`thread_id`、`query`、`search_engine`、`max_step_num` 等），作为编译图的初始状态输入。
