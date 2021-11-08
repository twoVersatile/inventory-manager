package com.inventory.manager.service.utils.perf;

public class EnvironmentContextHolder {
    private static final ThreadLocal<EnvContext> ENV_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static void setContext(EnvContext envContext) {
        if (envContext == null) {
            ENV_CONTEXT_THREAD_LOCAL.set(EnvContext.PROD);
        } else {
            ENV_CONTEXT_THREAD_LOCAL.set(envContext);
        }
    }

    public static EnvContext getContext() {
        EnvContext envContext = ENV_CONTEXT_THREAD_LOCAL.get();

        if (envContext == null) {
            return EnvContext.PROD;
        } else {
            return envContext;
        }
    }

    public static void clear() {
        ENV_CONTEXT_THREAD_LOCAL.remove();
    }
}
