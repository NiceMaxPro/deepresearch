# PythonReplTool.java

一个 Spring `@Service`，用于在隔离的 Docker 容器中执行任意 Python 代码，实现安全的沙箱化代码执行。其主要方法 `executePythonCode` 接受 Python 代码和可选的 `requirements.txt` 内容，创建包含代码文件的临时目录，在网络被禁用时可选择性地启动依赖安装容器，然后在具有 CPU/内存限制和执行超时的资源受限容器中运行代码。它通过 Docker 的日志流 API 捕获标准输出和标准错误，检查退出码，并在 `finally` 块中清理临时目录、数据卷和容器。网络访问可通过 `PythonCoderProperties` 进行开关控制，该工具使用 `docker-java` 库进行所有 Docker 交互。
