package top.easylove.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private User user;

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 如果 status 是 4，则账号已过期
        return user.getStatus() != 4;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 如果 status 是 3，则账号被锁定
        return user.getStatus() != 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 如果 status 是 4，则凭据已过期
        return user.getStatus() != 4;
    }

    @Override
    public boolean isEnabled() {
        // 只有 status 是 0 时，账号才启用
        return user.getStatus() == 0;
    }
}
