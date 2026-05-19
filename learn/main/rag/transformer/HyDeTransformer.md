# HyDeTransformer.java

一个查询变换器，实现了假设文档嵌入（HyDE）技术，如 arxiv.org/abs/2212.10496 论文中所述。它实现 Spring AI 的 `QueryTransformer` 接口，使用 `ChatClient` 生成一个假设可回答用户问题的文档段落，然后将原始查询文本替换为生成的段落，以便基于嵌入的检索能够匹配文档式的文本，而非简短的查询文本。默认的提示词模板指示 LLM 基于用户问题编写一段"全面且信息丰富的段落"，但也可以注入自定义的 `PromptTemplate`。如果 LLM 返回 null 或空内容，变换器会记录警告并返回原始查询不变。该类通过其静态的 `builder()` 方法遵循建造者模式进行构造。
