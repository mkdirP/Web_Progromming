package application.configuration;

import application.domain.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

// 自定义的用户详情服务，实现了Spring Security提供的接口
// 该接口用于从app的持久存储中获取用户详细信息，并将其提供给SS

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userService获取用户实体对象user，查找数据库中的用户信息
//        并将其转换为CustomUserDetails对象，作为SS验证用户时使用的用户信息
        User userEntity = userService.findByLogin(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User wasn‘t found");
        } else {
            return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
        }
    }
}