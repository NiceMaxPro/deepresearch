# SearchFilterService.java

此抽象类提供了基于网站信任权重对搜索引擎结果进行排序和过滤的框架。子类必须实现 `loadWebsiteWeight()` 以返回一个 `Map<String, Double>`，将域名映射到 [-1.0, 1.0] 范围内的权重值（正数表示信任，负数表示不信任）。`sortSearchResult` 方法从结果 URL 中提取域名，分配权重，按权重降序排序，然后将高权重结果交错排列在输出列表的两端（奇偶索引交替），以优化 AI 模型的可读性。`filterSearchResult` 方法还会额外移除权重为负的条目。便捷方法 `queryAndSort` 和 `queryAndFilter` 使用 `SearchBeanUtil` 查找和 `SearchEnum` 搜索引擎类型，将搜索执行与结果处理相结合。
