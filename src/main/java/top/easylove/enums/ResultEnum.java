package top.easylove.enums;

import lombok.Getter;
import top.easylove.constant.ExceptionConstants;
import top.easylove.constant.ResultConstants;

@Getter
public enum ResultEnum {
    SUCCESS(200, ResultConstants.SUCCESS),
    USER_REGISTER_SUCCESS(2001, ResultConstants.USER_REGISTER_SUCCESS),
    USER_LOGIN_SUCCESS(2002, ResultConstants.USER_LOGIN_SUCCESS),
    ERROR(500, ResultConstants.ERROR),
    INVALID_EMAIL_FORMAT(4001, ResultConstants.INVALID_EMAIL_FORMAT),
    USER_NOT_FOUND(4002, ResultConstants.USER_NOT_FOUND),
    USER_EXIST(4003, ResultConstants.USER_EXIST),
    USER_PENDING_APPROVAL(4004, ResultConstants.USER_PENDING_APPROVAL),
    UNAUTHORIZED_ACCESS(4005, "User is not logged in or token is invalid"),
    ACCESS_DENIED(4006, ResultConstants.ACCESS_DENIED),
    USER_DISABLED(4004, ExceptionConstants.USER_DISABLED),
    USER_ACCOUNT_EXPIRED(4005, ExceptionConstants.USER_ACCOUNT_EXPIRED),
    INVALID_CREDENTIALS(4006, ExceptionConstants.INVALID_CREDENTIALS),
    AUTHENTICATION_FAILED(4007, ExceptionConstants.AUTHENTICATION_FAILED),
    INTERNAL_SERVER_ERROR(5000, "Internal server error");

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
