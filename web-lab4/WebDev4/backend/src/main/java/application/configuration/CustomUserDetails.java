package application.configuration;

import application.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//表示用户的安全相关信息，比如用户名，密码，权限等
//从User实体类中获取用户名和密码

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User userEntity) {
        CustomUserDetails c = new CustomUserDetails();
        c.username = userEntity.getUsername();
        c.password = userEntity.getPassword();
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 如果需要SS进行权限控制，需要在该方法中返回用户的权限列表
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
