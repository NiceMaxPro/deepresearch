# InformationDispatcher.java

一个 `EdgeAction` 实现，控制信息收集阶段之后的路由。它从 `OverAllState` 中读取 `information_next_node`，默认为 `END`。该调度器允许信息节点指定下一步应由哪个下游节点处理收集到的数据，从而支持研究管道中灵活的工作流路径。
