package com.ruiec.demo.config;

import com.ruiec.demo.service.SysUserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Jwt-token
 * @author luowei
 * @date 2019/5/28 14:55
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }
    /**
     * 主要数据
     * @author luowei
     * @date 2019/5/28 14:57
     */
    @Override
    public Object getPrincipal() {
        return token;
    }
    /**
     * 凭证
     * @author luowei
     * @date 2019/5/28 14:57
     */
    @Override
    public Object getCredentials() {
        return token;
    }

}
