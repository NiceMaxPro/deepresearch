# CoordinatorDispatcher.java

一个 `EdgeAction` 实现，在协调器节点完成后路由图流程。它从 `OverAllState` 中读取 `coordinator_next_node` 键的值，默认为 `END`，从而允许协调器智能体动态决定下一步执行路径。这是基于图的编排模式的一部分，每个智能体节点在共享状态中设置其后继节点。
