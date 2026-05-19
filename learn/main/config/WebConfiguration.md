# WebConfiguration.java

Spring `@Configuration` 类，配置 Web 层的 CORS（跨域资源共享）过滤器。通过 `FilterRegistrationBean` 注册 `CorsFilter`，允许所有来源（`*`）、所有请求头、所有 HTTP 方法的跨域请求，但不允许携带凭据（`setAllowCredentials(false)`）。将所有 CORS 配置应用于 `/**` 路径，确保前端 SPA 应用能跨域访问后端 API。
