# RagNodeService.java

此 `@Service` 为检索增强生成（RAG）流水线创建异步图节点操作，支持两种检索策略：用户文件检索和专业知识库（基于 ES）检索。它使用 `required = false` 自动装配可选的依赖项（`ChatClient`、`UserFileRetrievalStrategy`、`ProfessionalKbEsStrategy`、`FusionStrategy`、`HybridRagProcessor`）。每个创建方法（`createUserFileRagNode` 和 `createProfessionalKbRagNode`）在可用的 `HybridRagProcessor` 存在时优先使用统一的处理器（将其包装在 `RagNode` 中），否则回退到传统的策略模式方法，将检索策略列表与融合策略结合使用。生成的 `AsyncNodeAction` 实例作为可执行节点在 Spring AI Alibaba Graph 工作流中使用。
