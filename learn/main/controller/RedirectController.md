# RedirectController.java

一个极简的 Spring MVC `@Controller`，将所有前端 URL 模式（`/`、`/ui`、`/ui/`、`/ui/chat`、`/ui/chat/**`）转发到单页应用入口点 `/ui/index.html`。它包含一个方法 `frontend()`，返回静态视图路径，从而支持 Vue 3 前端的客户端路由。这是 Spring Boot 后端服务 SPA 的标准模式。
