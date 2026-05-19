# PythonCoderProperties.java

Spring Boot `@ConfigurationProperties` 配置属性类，前缀为 `spring.ai.alibaba.deepresearch.python-coder`。管理 Python 代码执行（通过 Docker 容器）的配置：`dockerHost`（Docker 守护进程地址）、`containNamePrefix`（容器名称前缀）、`limitMemory`（内存限制，默认 500MB）、`cpuCore`（CPU 核心数限制）、`enableNetwork`（是否启用容器网络访问，默认 false）、`codeTimeout`（代码执行超时，默认 60s）、`dockerTimeout`（Docker 操作超时）、`imageName`（使用的 Docker 镜像，默认 `python:3-slim`）。安全方面默认禁用网络访问。
