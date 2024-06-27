package top.easylove.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.easylove.enums.ResultEnum;

@Data
public class ResultResponse<T> {

    @Schema(description = "Status code", example = "200")
    private Integer code;

    @Schema(description = "Response message", example = "Success")
    private String message;

    @Schema(description = "Returned data")
    private T data;

    public ResultResponse(ResultEnum resultEnum, T data) {
        this(resultEnum);
        this.data = data;
    }

    public ResultResponse(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public static <T> ResultResponse<T> success(ResultEnum resultEnum, T data) {
        return new ResultResponse<>(resultEnum, data);
    }

    public static <T> ResultResponse<T> error(ResultEnum resultEnum) {
        return new ResultResponse<>(resultEnum);
    }
}
