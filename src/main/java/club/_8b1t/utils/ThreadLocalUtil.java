package club._8b1t.utils;

/**
 * @author 8bit
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setToken(String token) {
        THREAD_LOCAL.set(token);
    }

    public static String getToken() {
        return THREAD_LOCAL.get();
    }

    public static void clearToken() {
        THREAD_LOCAL.remove();
    }
}
