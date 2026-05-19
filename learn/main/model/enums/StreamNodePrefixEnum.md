# StreamNodePrefixEnum.java

一个枚举，将流式输出节点前缀映射到前端渲染的可见性标志。它定义了六种流式节点类型：`PLANNER_LLM_STREAM`（规划者 LLM 流，不可见）、`RESEARCHER_LLM_STREAM`（研究者 LLM 流）、`RESEARCHER_REFLECT_LLM_STREAM`（研究者反思 LLM 流）、`CODER_REFLECT_LLM_STREAM`（编码者反思 LLM 流）、`CODER_LLM_STREAM`（编码者 LLM 流）和 `REPORTER_LLM_STREAM`（报告者 LLM 流，除规划者外均可见）。每个常量持有一个作为节点命名约定的 `prefix` 字符串和一个控制其输出是否在 UI 中显示的 `visible` 布尔值。静态方法 `match(String nodeName)` 遍历所有常量以查找给定节点名称以哪个前缀开头，使系统能够在运行时对流式节点进行分类。
