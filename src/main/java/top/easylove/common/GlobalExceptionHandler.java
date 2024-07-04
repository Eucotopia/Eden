package top.easylove.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.easylove.util.ResultResponse;
import top.easylove.enums.ResultEnum;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultResponse<String> nullPointerException(NullPointerException e) {
        log.error("NullPointerException:", e);
        return ResultResponse.error(ResultEnum.USER_NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseBody
    public ResultResponse<String> userNotFoundException(UsernameNotFoundException e) {
        log.error("UsernameNotFoundException:", e);
        return ResultResponse.error(ResultEnum.USER_NOT_FOUND);
    }

    @ExceptionHandler(value = DisabledException.class)
    @ResponseBody
    public ResultResponse<String> handleDisabledException(DisabledException e) {
        return ResultResponse.error(ResultEnum.USER_DISABLED);
    }

    @ExceptionHandler(value = AccountExpiredException.class)
    @ResponseBody
    public ResultResponse<String> handleAccountExpiredException(AccountExpiredException e) {
        return ResultResponse.error(ResultEnum.USER_ACCOUNT_EXPIRED);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseBody
    public ResultResponse<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResultResponse.error(ResultEnum.INVALID_CREDENTIALS);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ResultResponse<String> handleAuthenticationException(AuthenticationException e) {
        return ResultResponse.error(ResultEnum.AUTHENTICATION_FAILED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse<String> handleException(Exception e) {
        return ResultResponse.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public ResultResponse<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException:", e);
        return ResultResponse.error(ResultEnum.AUTHENTICATION_FAILED);
    }

}
