# PlannerTool.java

一个 Spring `@Service`，为 AI 智能体系统暴露一个名为 `handoff_to_planner` 的工具。其唯一的 `handoffToPlanner` 方法接受一个 `taskTitle` 字符串并记录日志，其本身是一个空操作工具，真正的作用是作为 LLM 将工作移交给规划器智能体的信号。该工具不返回任何值，因为它被设计为基于图的执行系统中的纯控制流机制，允许 LLM 表明某个任务需要进行规划分解。
