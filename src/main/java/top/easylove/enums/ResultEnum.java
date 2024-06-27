package top.easylove.enums;

import lombok.Getter;
import top.easylove.common.ResultConstants;

@Getter
public enum ResultEnum {
    SUCCESS(200, ResultConstants.SUCCESS),
    ERROR(500, ResultConstants.ERROR),
    INVALID_EMAIL_FORMAT(4001, ResultConstants.INVALID_EMAIL_FORMAT),
    USER_NOT_FOUND(4002, ResultConstants.USER_NOT_FOUND),
    ;

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
