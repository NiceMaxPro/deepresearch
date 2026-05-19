# TemplateUtil.java

模板加载工具类，从 `resources/prompts/` 目录读取 Markdown 提示文件并用运行时值填充占位符。`getMessage` 重载方法通过名称加载提示，将 `{{ CURRENT_TIME }}` 替换为当前日期时间，并可选择性地替换来自状态的 `{{ max_step_num }}`，返回一个 `SystemMessage`。`getShortMemoryExtractMessage` 加载短期记忆提取模板并填充用户消息占位符。`getShortMemoryUpdateMessage` 通过将提取结果序列化为 JSON 并替换阈值来构建记忆更新提示。`getOptQueryMessage` 构造一个包含来自状态的原始和优化后查询对的消息。`addShortUserRoleMemory` 读取状态中存储的 `ShortUserRoleExtractResult`，并向消息列表中注入一条包含用户概览的系统消息。
