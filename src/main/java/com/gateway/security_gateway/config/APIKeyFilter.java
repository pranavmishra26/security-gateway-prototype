package com.gateway.security_gateway.config;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class APIKeyFilter implements Filter {
    private static final String VALID_KEY = "demo-key-123";
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;
        if (httpReq.getRequestURI().startsWith("/gateway")) {
            String key = httpReq.getHeader("X-API-KEY");
            if (key == null || !key.equals(VALID_KEY)) {
                httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpRes.getWriter().write("{\"error\":\"Unauthorized - missing or invalid API key\"}");
                httpRes.setContentType("application/json");
                return;
            }
        }
        chain.doFilter(httpReq, httpRes);
    }
}
