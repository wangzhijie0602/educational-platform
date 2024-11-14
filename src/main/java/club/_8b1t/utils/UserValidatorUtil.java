package club._8b1t.utils;

import club._8b1t.pojo.User;

/**
 * 验证用户是否合法的工具类
 *
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
public class UserValidatorUtil {

    /**
     * 验证用户对象是否合法
     *
     * @param user 要验证的用户对象
     * @return 如果用户对象合法则返回 true，否则返回 false
     */
    public static boolean validateUser(User user) {

        if (!validateUserBasicInfo(user)) {
            return false;
        }

        // 检查用户 ID 是否为 null 或小于等于 0
        if (user.getId() == null || user.getId() <= 0) {
            return false;
        }

        // 检查昵称是否为 null 且长度超过 50
        if (user.getName() != null && user.getName().length() > 50) {
            return false;
        }

        // 检查角色是否为 null 或不在有效范围内
        if (user.getRole() == null || !isValidRole(user.getRole())) {
            return false;
        }

        // 检查状态是否为 null 或不在有效范围内
        if (user.getStatus() == null || !isValidStatus(user.getStatus())) {
            return false;
        }

        // 检查创建时间是否为 null
        if (user.getCreatedAt() == null) {
            return false;
        }

        // 检查更新时间是否为 null
        if (user.getUpdatedAt() == null) {
            return false;
        }

        // 检查逻辑删除标志是否为 null
        if (user.getIsDeleted() == null) {
            return false;
        }

        // 如果所有检查都通过，则返回 true
        return true;
    }

    /**
     * 验证用户对象的基本信息（用户名、密码和邮箱）是否合法
     *
     * @param user 要验证的用户对象
     * @return 如果用户的基本信息合法则返回 true，否则返回 false
     */
    public static boolean validateUserBasicInfo(User user) {
        // 检查用户对象是否为 null
        if (user == null) {
            return false;
        }

        // 检查用户名是否为 null、为空或长度超过 50
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            return false;
        }

        // 检查密码是否为 null、为空或长度超过 255
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() < 8 || user.getPassword().length() > 255) {
            return false;
        }

        // 检查电子邮件是否为 null、格式是否正确或长度超过 100
        if (user.getEmail() == null || !isValidEmail(user.getEmail()) || user.getEmail().length() > 100) {
            return false;
        }

        // 如果所有检查都通过，则返回 true
        return true;
    }

    /**
     * 验证电子邮件地址是否合法
     *
     * @param email 要验证的电子邮件地址
     * @return 如果电子邮件地址合法则返回 true，否则返回 false
     */
    private static boolean isValidEmail(String email) {
        // 使用正则表达式验证电子邮件格式
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    /**
     * 验证用户角色是否合法
     *
     * @param role 要验证的用户角色
     * @return 如果用户角色合法则返回 true，否则返回 false
     */
    private static boolean isValidRole(User.Role role) {
        // 检查角色是否在有效范围内
        return role == User.Role.ADMIN || role == User.Role.TEACHER || role == User.Role.STUDENT;
    }

    /**
     * 验证用户状态是否合法
     *
     * @param status 要验证的用户状态
     * @return 如果用户状态合法则返回 true，否则返回 false
     */
    private static boolean isValidStatus(User.Status status) {
        // 检查状态是否在有效范围内
        return status == User.Status.ACTIVE || status == User.Status.INACTIVE || status == User.Status.SUSPENDED;
    }
}
