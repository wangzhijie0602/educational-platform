package club._8b1t.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 8bit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result success() { return new Result(1, "success", null); }

    public static Result success(Object data) { return new Result(1, "success", data); }

    public static Result error(String msg) { return new Result(0, msg, null); }
}
