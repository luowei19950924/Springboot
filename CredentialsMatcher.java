package com.ruiec.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 登录验证
 * 
 * @author chenJingYuan<br>
 * @date 2018年9月6日 上午8:51:28
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

	/**
	 * 登录信息匹配验证
	 * 
	 * @author chenJingYuan<br>
	 * @date 2018年9月6日 上午8:51:45
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		// 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
		String inPassword = new String(utoken.getPassword());
		// 获得数据库中的密码
		String dbPassword = (String) info.getCredentials();
		// 进行密码的比对
		return this.equals(inPassword, dbPassword);
	}
}
