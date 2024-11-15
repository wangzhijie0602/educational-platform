package club._8b1t.utils;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
public class ThreadLocalUtils {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setToken(String username) {
        THREAD_LOCAL.set(username);
    }

    public static String getToken() {
        return THREAD_LOCAL.get();
    }

    public static void clearToken() {
        THREAD_LOCAL.remove();
    }
}
