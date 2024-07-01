package top.easylove.service.impl;

import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.easylove.pojo.User;
import top.easylove.repository.UserRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    /**
     * Loads user details by username.
     *
     * @param username the username (in this case, email) to load user details for
     * @return UserDetails containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> {
                    Set<GrantedAuthority> roleAuthorities = role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                            .collect(Collectors.toSet());
                    roleAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                    return roleAuthorities.stream();
                })
                .collect(Collectors.toSet());

        return new UserDetails() {
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

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return username;
            }
        };
    }
}
