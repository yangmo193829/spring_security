package fun.zyjk.spring_security.constant;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h2>响应返回码枚举定义</h2>
 *
 * @author yzk
 */
public enum RspCodeEnum {
    // 【参考】枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开
    //【推荐】如果变量值仅在一个固定范围内变化用 enum 类型来定义。
    SUCCESS(200, "success"),
    ERROR(0, "error"),
    ERROR_SYSTEM(500, "system error"),
    FAILURE(1000, "failure"),
    NOT_ACCESS(403,"无权限"),
    ACCOUNT_NOT_EXIST(5001,"ACCOUNT_NOT_EXIST"),
    ERROR_VALIDCODE(5002,"ERROR_VALIDCODE"),
    NOTOKEN(5000, "no token");


    // key 返回码 value 描述, 注意如果 key 重复，会报异常，设置第三个参数，规定值的变化
    public static final Map<Integer, String> CODE_TO_DESC = Arrays.stream(values()).collect(
            Collectors.toMap(RspCodeEnum::getCode, RspCodeEnum::getDesc, (oldValue, newValue) -> newValue));
    // key 描述 value 返回码
    public static final Map<String, Integer> DESC_TO_CODE = Arrays.stream(values()).collect(Collectors.toMap(
            RspCodeEnum::getDesc, RspCodeEnum::getCode, (oldValue, newValue) -> newValue));
    // key 返回码 value 枚举信息
    public static final Map<Integer, RspCodeEnum> CODE_TO_RSPCODEENUM = Arrays.stream(values()).collect(
            Collectors.toMap(RspCodeEnum::getCode, RspCodeEnum -> RspCodeEnum, (oldValue, newValue) -> newValue));

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    RspCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }


    /**
     * 根据code返回RspCodeEnum
     *
     * @param code 返回码
     * @return {@link RspCodeEnum}
     */
    public static RspCodeEnum getRspCodeEnumBycode(int code) {
        for (RspCodeEnum rspCodeEnum : RspCodeEnum.values()) {
            if (rspCodeEnum.getCode() == code) {
                return rspCodeEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举-RspCodeEnum");
    }

    /**
     * 根据响应码 返回 描述信息
     *
     * @param code 返回码
     * @return 描述信息
     */
    public static String fromCodeToDesc(Integer code) {
        return CODE_TO_DESC.getOrDefault(code, FAILURE.desc);
    }

    /**
     * 根据响应码 返回 枚举对象
     *
     * @param code 返回码
     * @return {@link RspCodeEnum}
     */
    public static RspCodeEnum fromCode(Integer code) {
        return CODE_TO_RSPCODEENUM.getOrDefault(code, FAILURE);
    }


}
