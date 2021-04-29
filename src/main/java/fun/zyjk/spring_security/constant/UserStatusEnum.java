package fun.zyjk.spring_security.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>用户状态</h1>
 *
 * @author yzk
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    /**
     * 不可用
     *
     */
    DISABLE(0, "用户不可用"),
    ENABLED(1, "用户可用");

    private int value;
    private String info;
}
