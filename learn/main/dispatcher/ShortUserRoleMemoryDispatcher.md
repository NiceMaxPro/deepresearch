# ShortUserRoleMemoryDispatcher.java

一个 `EdgeAction` 实现，在短期用户角色记忆提取节点之后路由执行流程。它从 `OverAllState` 中读取 `short_user_role_next_node`，默认为 `END`。该调度器允许短期记忆节点在提取并存储用户角色信息后控制图的后续走向。
