# ConfidenceLevel.java

一个枚举，定义了用户角色识别系统中使用的四个置信度级别：`LOW`（低）、`MEDIUM`（中）、`MEDIUM_HIGH`（中高）和 `HIGH`（高）。它被 `IdentifiedRole` 引用，用于表示系统对用户角色推断的确定程度。该枚举提供了一个简单有序的量表，允许下游组件根据阈值过滤或决策角色相关的操作。
