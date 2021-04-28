package fun.zyjk.spring_security.sys.service.impl;

import fun.zyjk.spring_security.constant.Oauth2LoginConstant;
import fun.zyjk.spring_security.constant.RspCodeEnum;
import fun.zyjk.spring_security.exception.RrException;
import fun.zyjk.spring_security.sys.entity.AuthRequestConfigEntity;
import fun.zyjk.spring_security.sys.service.AuthRequestService;
import lombok.SneakyThrows;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;

@Service
public class AuthRequestServiceImpl implements AuthRequestService {

    // 读取第三方登陆配置的实体类
    @Autowired
    private AuthRequestConfigEntity authRequestConfigEntity;

    @SneakyThrows
    @Override
    public AuthRequest getAuthRequest(String sys) {
        AuthRequest authRequest = null;
        List<AuthRequestConfigEntity.AuthRequestInfo> othersys = authRequestConfigEntity.getOthersys();
        // 如果配置多个相同名称的 第三方系统，仅获取第一个配置信息
        Optional<AuthRequestConfigEntity.AuthRequestInfo> info = othersys.stream().
                filter(authRequestInfo -> authRequestInfo.getName().equalsIgnoreCase(sys))
                .findFirst();
        AuthRequestConfigEntity.AuthRequestInfo authRequestInfo = info.orElseThrow(() -> new RrException("不存在" + sys + "该系统的第三方登陆配置，请在yml文件中加入该系统的配置", RspCodeEnum.ERROR.getCode()));
        // 构造 AuthConfig
        AuthConfig config = AuthConfig.builder()
                .clientId(authRequestInfo.getClientId())
                .clientSecret(authRequestInfo.getClientSecret())
                .redirectUri(authRequestInfo.getRedirectUri())
                .build();
        // 反射，使用有参的构造方法，创建对象
        Class aClass = Oauth2LoginConstant.sysMappingClazz.get(sys);
        Constructor constructor = aClass.getConstructor(AuthConfig.class);
        Object obj = constructor.newInstance(config);
        if (obj instanceof AuthRequest) {
            authRequest = (AuthRequest) obj;
        }
        return authRequest;
    }
}
