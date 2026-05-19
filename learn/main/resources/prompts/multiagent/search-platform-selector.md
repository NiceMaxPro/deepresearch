# search-platform-selector.md

此中文提示定义了一个搜索平台选择专家代理，根据用户的问题类型和内容智能选择最合适的搜索平台。它定义了十个可用平台，分为两个类别：传统搜索引擎（Tavily 用于通用网页搜索、阿里云 AI 搜索用于中文内容和商业信息、百度搜索用于中文本地化内容、SerpAPI 通过 Google 获取高质量英文结果）和专业工具调用平台（OpenAlex 用于学术论文、Google 学术用于论文和引用、Wikipedia 用于百科知识和概念、OpenTripMap 用于旅游景点和地理位置、TripAdvisor 用于酒店/餐厅评价、WorldBank Data 用于经济和发展数据分析）。

选择逻辑遵循基于领域的优先级：学术研究问题发送到 OpenAlex 或 Google 学术，百科知识发送到 Wikipedia，旅行/生活方式发送到 OpenTripMap 或 TripAdvisor，数据分析发送到 WorldBank Data，一般性问题根据语言和地区发送到适当的传统搜索引擎。代理仅输出平台名称（如 `OPENALEX`、`WIKIPEDIA`、`TAVILY`），不附加任何其他内容。对于模糊或跨领域问题，代理选择覆盖范围最广的平台。
