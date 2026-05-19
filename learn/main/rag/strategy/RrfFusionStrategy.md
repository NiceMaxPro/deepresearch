# RrfFusionStrategy.java

同时实现 `FusionStrategy` 和 `DocumentPostProcessor` 接口，使用倒数排名融合（RRF）来合并和重排序文档列表。标准的 RRF 公式为 `1 / (k + rank)`，其中 `k` 是可配置的常数（默认 60），`rank` 是文档的排名位置（从 1 开始）。作为 `FusionStrategy`，`fuse()` 方法接受多个排序后的结果列表，为每个文档计算 RRF 分数，将各列表的贡献值求和，然后按降序排序并返回合并后的列表。作为 `DocumentPostProcessor`，`process()` 方法按 `source_type` 元数据将单个文档列表分组以模拟多个来源，然后应用相同的 RRF 融合逻辑生成重排序的输出。两种模式都支持可配置的 `topK`（默认 10）和 `threshold`（默认 0.1）来限制和过滤结果。该类使用文档的显式 ID 或其文本内容的哈希值作为去重键。
