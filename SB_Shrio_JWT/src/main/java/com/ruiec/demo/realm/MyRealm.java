package com.ruiec.demo.realm;

import com.ruiec.demo.config.JwtToken;
import com.ruiec.demo.service.SysUserService;
import com.ruiec.demo.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * @author luowei
 * @date 2019/5/28 15:57
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    /**
     * 必须重写此方法，不然Shiro会报错
     * @author luowei
     * @date 2019/5/28 16:14
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @author luowei
     * @date 2019/5/28 16:03
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token=(String) authenticationToken.getCredentials();
        //解密获取username，用于数据库比对
        String username = JwtUtil.getUsername(token);
        if (username==null){
            throw new AuthenticationException("token无效");
        }
        Map<String,Object> userBean=sysUserService.findByUserName(username);
        if (userBean == null) {
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtil.verify(token, username, userBean.get("password").toString())) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(token,token,"my_realm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @author luowei
     * @date 2019/5/28 16:03
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=JwtUtil.getUsername(principalCollection.toString());
        Map<String,Object> user=sysUserService.findByUserName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }


}
