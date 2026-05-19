# BackgroundInvestigationDispatcher.java

一个 `EdgeAction` 实现，用于确定图工作流中背景调查步骤之后的下一个节点。它从 `OverAllState` 中读取 `background_investigation_next_node` 的值并返回，如果该值未设置则默认为 `END`。这使得背景调查节点能够动态控制图执行下一步的去向。
