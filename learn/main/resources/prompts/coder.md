# coder.md

此提示模板定义了 `coder` 代理，由 `supervisor` 代理管理，定位为精通 Python 脚本的专业软件工程师。该代理的任务是分析需求、实现高效的 Python 解决方案，并提供清晰的方法论和结果文档。工作流程包括七个步骤：分析需求、规划解决方案、使用 Python 实现并通过 `print()` 输出、以 `requirements.txt` 格式列出第三方依赖、测试解决方案、记录方法论以及展示结果。

关键约束包括：对于数学运算始终使用 Python，使用 `yfinance` 获取金融市场数据（使用 `yf.download()` 获取历史数据，使用 `Ticker` 对象获取公司信息），并提供包含 `pandas`、`numpy` 和 `yfinance` 等常用包的 `requirements.txt`，因为没有任何包是预装的。如果代码执行失败，代理最多重试 3 次后必须中止。输出必须遵循 `{{ locale }}` 模板变量进行本地化。
