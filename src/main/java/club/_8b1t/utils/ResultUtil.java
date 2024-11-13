package club._8b1t.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 8bit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultUtil {

    private Integer code;
    private String msg;
    private Object data;

    public static ResultUtil success() { return new ResultUtil(1, "success", null); }

    public static ResultUtil success(Object data) { return new ResultUtil(1, "success", data); }

    public static ResultUtil error(String msg) { return new ResultUtil(0, msg, null); }
}
