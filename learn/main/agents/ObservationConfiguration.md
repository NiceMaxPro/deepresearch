# ObservationConfiguration.java

Spring `@Configuration` 类，用于配置工具调用（Tool Calling）的可观测性。通过 `@ConditionalOnProperty` 控制启用（默认启用）。核心功能包括：注册 `toolCallingObservationContextObservationHandler` Bean，这是一个 `ObservationHandler`，在工具调用开始和结束时记录日志（工具名称、参数和结果）；注册 `observationRegistry` Bean，收集所有 `ObservationHandler` 并创建 Micrometer `ObservationRegistry` 实例，为系统提供工具调用级别的可观测性支持。
