# NodeNameEnum.java

一个集中管理所有 StateGraph 工作流节点名称及其对应前端显示标题的枚举。它定义了 12 个节点：`START`（开始）、`END`（结束）、`COORDINATOR`（意图识别）、`REWRITE_MULTI_QUERY`（查询相关信息搜索）、`BACKGROUND_INVESTIGATOR`（背景调查）、`PLANNER`（研究计划）、`INFORMATION`（信息）、`HUMAN_FEEDBACK`（人类反馈）、`RESEARCH_TEAM`（等待并行节点）、`PARALLEL_EXECUTOR`（并行执行器）、`REPORTER`（报告生成）和 `RAG_NODE`（RAG 节点）。每个枚举常量将一个内部 `nodeName` 与一个中文 `displayTitle` 配对，用于 UI 渲染。该类提供了两个静态查找方法：`getDisplayTitleByNodeName(String)` 根据节点名称返回显示标题，`fromNodeName(String)` 返回完整的枚举常量。
