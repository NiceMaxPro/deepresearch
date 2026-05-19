# AgentType.java

一个枚举，定义了五种专门的研究代理类型，每种类型都有不同的领域专长、提示模板和引用指南。这些常量包括：`ACADEMIC_RESEARCH`（学术论文、期刊）、`LIFESTYLE_TRAVEL`（旅行指南、餐饮、购物）、`ENCYCLOPEDIA`（百科知识、历史、科学）、`DATA_ANALYSIS`（统计、市场研究、报告）和 `GENERAL_RESEARCH`（未分类或多领域查询的回退选项）。每个常量存储一个 `code`（代码）、一个中文 `name`（名称）、一个 `description`（描述）、一个指向 markdown 提示模板的 `promptFilePath`（对 GENERAL_RESEARCH 为 null），以及包含参考文献格式说明的 `citationGuidance`（引用指南）。静态方法 `parse(String code)` 通过代码字符串查找 AgentType，对未知代码抛出 `IllegalArgumentException`。
