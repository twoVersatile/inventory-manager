package com.inventory.manager.service.utils.perf;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class EnvContextFilter extends OncePerRequestFilter {

    public static final String PERF_HEADER = "X_ENV_CONTEXT";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String environmentContext = request.getHeader(PERF_HEADER);

        try {
            if (Objects.nonNull(environmentContext)) {
                EnvContext envContext = EnvContext.valueOf(environmentContext);

                EnvironmentContextHolder.setContext(envContext);
            } else {
                EnvironmentContextHolder.setContext(EnvContext.PROD);
            }
        } finally {
            EnvironmentContextHolder.clear();
        }
    }
}
