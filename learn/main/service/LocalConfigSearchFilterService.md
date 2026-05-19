# LocalConfigSearchFilterService.java

此 `@Service` 继承抽象类 `SearchFilterService`，提供了 `loadWebsiteWeight()` 的具体实现，从类路径中的本地 JSON 配置文件（`website-weight-config.json`）读取网站信任/不信任权重。它将 JSON 解析为 `WebsiteConfig` 记录列表（每个记录包含 `host` 字符串和 `weight` 双精度浮点数），然后构建一个 `Map<String, Double>` 将域名映射到其权重。权重值被限定在 [-1.0, 1.0] 范围内，当遇到超出范围的值时会发出日志警告。如果配置文件缺失或无法解析，则返回空映射，使所有网站获得中性（0.0）权重。
