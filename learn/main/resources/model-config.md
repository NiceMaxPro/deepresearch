# model-config.json

此 JSON 配置文件将逻辑代理角色映射到特定的阿里云 DashScope 模型名称，允许 DeepResearch 流水线中的每个代理使用针对其任务优化的不同模型。`research` 和 `reporter` 角色使用 `qwen-max-2025-01-25`（功能最强的模型），用于需要深度分析和报告生成的任务。轻量级代理如 `background`、`rewriteAndMultiQuery`、`planner`、`infoCheck` 和 `shortMemory` 使用更快的 `qwen-turbo-2025-04-28` 模型。

专业化的多代理角色（`academicResearch`、`lifestyleTravel`、`encyclopedia`、`dataAnalysis`）均使用 `qwen-max-2025-01-25` 以获取高质量的领域特定响应。`coder` 和 `interaction` 角色使用 `qwen3-coder-plus`，一个针对代码生成和交互任务优化的模型。`coordinator` 角色使用 `qwen-max-0919`，一个早期版本的 max 模型变体，用于编排和路由逻辑。
