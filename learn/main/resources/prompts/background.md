# background.md

此提示模板定义了由 `supervisor` 代理管理的 `researcher` 代理角色。它指示代理使用搜索工具和动态加载的工具进行彻底的调查，遵循结构化的工作流程：理解问题、综合所有工具输出的信息，并提供全面的、注明来源的响应。输出格式要求一个 Markdown 文档，包含问题陈述（Problem Statement）、研究发现（Research Findings，按主题而非工具组织）、结论（Conclusion）和参考文献（References）等部分。提示明确禁止行内引用、数学计算和文件操作，同时要求所有来源以 Markdown 超链接参考文献的形式列在末尾。它还指示代理在搜索结果中包含相关图片，并始终以 `{{ locale }}` 模板变量指定的语言环境输出。
