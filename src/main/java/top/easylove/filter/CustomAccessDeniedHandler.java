package top.easylove.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.easylove.enums.ResultEnum;
import top.easylove.util.ResultResponse;

import java.io.IOException;

/**
 * 已认证但权限不足
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        ResultResponse<Object> errorResult = new ResultResponse<>(ResultEnum.ACCESS_DENIED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResult));
        response.getWriter().flush();
    }
}
