# StateUtil.java

深度研究图 `OverAllState` 的中心化常量和访问器工具类。它定义了执行状态字符串常量（ASSIGNED、PROCESSING、COMPLETED、WAITING_REFLECTING、WAITING_PROCESSING 和 ERROR，每个都带有下划线前缀/后缀用于节点名称拼接），并提供了一套类型化的静态访问器方法，从状态中读取值并带有合理的默认值（例如 `getQuery` 默认为一个示例中文查询，`getPlanMaxIterations` 默认为 1，`getMaxStepNum` 默认为 3）。`handleStepError` 通过将步骤的执行状态设置为带有节点名称的错误前缀并记录异常来标准化错误处理。`getParallelMessages` 通过遍历研究者团队成员及其内容索引来收集并行节点执行中的内容条目。
