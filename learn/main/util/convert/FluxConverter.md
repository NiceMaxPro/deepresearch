# FluxConverter.java

一个采用构建器模式的接口，将响应式 `Flux<ChatResponse>` 流转换为 `Flux<GraphResponse<StreamingOutput>>` 以供图流式系统使用。构建器需要一个 `mapResult` 函数（用于生成完成元数据）以及可选的 `startingNode` 和 `startingState` 用于流式输出上下文。其内部的 `buildInternal` 方法使用 `AtomicReference` 通过拼接文本内容来合并连续的 `AssistantMessage` 文本块，将工具调用边界检测为合并停止的分割点。管道过滤空输出，通过 `doOnNext` 应用合并消费者，将每个响应映射为包装在 `GraphResponse` 中的 `StreamingOutput`，并追加一个通过 `Mono.fromCallable` 使用合并后的最终结果创建的终端 `GraphResponse.done()`。
