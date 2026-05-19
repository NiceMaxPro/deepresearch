# github-markdown.css

此 CSS 样式表为渲染的研究报告提供 GitHub 风格的 Markdown 样式，作用域限定在 `.markdown-body` 类下。它定义了一个浅色主题，白色背景、`#1f2328` 文本颜色，以及 AlibabaPuHuiTi 无衬线字体族，字号 16px，行高 1.5。内容区域限制最大宽度为 800px，40px 内边距，并通过 auto 边距居中。样式表包含对所有标准 Markdown 元素的全面样式设置：标题（h1-h6 大小各异，h1 为 2em 且无底部边框）、表格（斑马纹行，交替白色和 `#f6f8fa` 背景，悬停高亮，单元格文本居中）、代码块（浅灰色 `#f6f8fa` 背景，6px 圆角，85% 字号）、引用块（左侧灰色边框，`#59636e` 文本颜色）、链接（`#0969da` 蓝色，悬停时显示下划线）和图片（通过 translateX 变换居中）。

样式表还包含 GitHub 特有的功能：带复选框的任务列表项、带颜色编码左侧边框的 Markdown 提醒（note=蓝色、important=紫色、warning=黄色、tip=绿色、caution=红色）、语法高亮颜色类（关键字红色、字符串深蓝色、注释灰色）、脚注、CSV 数据表，以及标题悬停时显示的 octicon 链接图标。它还包含针对打印的媒体查询规则，确保表格在 PDF 生成过程中避免分页。值得注意的是，水平分割线被隐藏（`display: none !important`），标题底部边框被移除，这使其与标准的 GitHub Markdown 主题有所区别。
