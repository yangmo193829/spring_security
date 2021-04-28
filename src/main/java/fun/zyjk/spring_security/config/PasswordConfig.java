package fun.zyjk.spring_security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <h1>密码的加密方式</h1>
 *
 * @author yzk
 */
@Configuration
@AllArgsConstructor
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 推荐使用 BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

}
