# ProfessionalKbDispatcher.java

一个 `EdgeAction` 实现，根据条件将执行路由到专业知识库 RAG 节点。它检查 `use_professional_kb` 布尔状态标志：如果为 `true`，图继续执行到 `"professional_kb_rag"`；否则，直接跳到 `"reporter"`。这是少数几个包含条件逻辑（不仅仅是简单的状态键查找）的调度器之一。
