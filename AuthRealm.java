package com.ruiec.config;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.collections4.MapUtils;

import com.ruiec.service.AdminRoleService;
import com.ruiec.service.AdminService;
import com.ruiec.util.SpringUtil;

/**
 * shiro权限域
 * 
 * @author chenJingYuan<br>
 * @date 2018年8月23日 上午9:27:08
 */
public class AuthRealm extends AuthorizingRealm {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthRealm.class);

	/**
	 * 认证.登录
	 * 
	 * @author chenJingYuan<br>
	 * @date 2018年9月6日 上午8:51:04
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOGGER.info("---------------------------->登陆验证:");
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token
		String username = utoken.getUsername();
		AdminService adminService = SpringUtil.getBean(AdminService.class);
		Map<String, Object> admin = adminService.selectByUsename(username);
		if (MapUtils.isEmpty(admin)) {
			// 用户不存在就抛出异常
			return null;
		}
		if (MapUtils.getBooleanValue(admin, "isLocked", true)) {
			// 用户被锁定就抛异常
			throw new LockedAccountException();
		}
		return new SimpleAuthenticationInfo(admin, admin.get("password").toString(), this.getClass().getName());// 放入shiro.调用CredentialsMatcher检验密码
	}

	/**
	 * 授权
	 * 
	 * @author chenJingYuan<br>
	 * @date 2018年9月6日 上午8:51:13
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		Map<String, Object> admin = (Map<String, Object>) principal.fromRealm(this.getClass().getName()).iterator().next();// 获取session中的用户
		Set<String> permissions = new HashSet<String>();
		String adminId = admin.get("id").toString();
		// 该管理员所拥有的权限
//		AdminService adminService = SpringUtil.getBean(AdminService.class);
//		Map<String, Object> adminRoles = adminService.selectAdminRolesAliasById(adminId);
//		if (adminRoles.get("roles") != null) {
//			List<Map<String, Object>> roleList = (List<Map<String, Object>>) adminRoles.get("roles");
//			if (roleList != null && roleList.size() > 0) {
//				for (Map<String, Object> roMap : roleList) {
//					if (roMap.get("authoritys") != null) {
//						List<Map<String, Object>> authorityList = (List<Map<String, Object>>) roMap.get("authoritys");
//						for (Map<String, Object> auMap : authorityList) {
//							if (auMap.get("url") != null) {
//								permissions.add(auMap.get("url").toString());
//							}
//						}
//					}
//				}
//			}
//		}
		AdminRoleService adminRoleService = SpringUtil.getBean(AdminRoleService.class);
		List<Map<String, Object>> navList = adminRoleService.selectNavByAdminId(adminId);
		for (Map<String, Object> nav : navList) {
			if (nav == null)
				continue;
			if (nav.get("link") != null && !"".equals(nav.get("link"))) {
				permissions.add(nav.get("link").toString());
			}
			/*
			 * if (nav.get("sign") != null && !"".equals(nav.get("sign"))) {
			 * permissions.add(nav.get("sign").toString()); }
			 */
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (permissions.size() > 0) {
			info.addStringPermissions(permissions);// 将权限放入shiro中.
		}
		return info;
	}

}