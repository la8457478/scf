package com.fkhwl.scf.web.shiro;

import com.fkhwl.scf.CompanyContractProvider;
import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.ScfUserProvider;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.dto.TemplateUserDTO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.UserType;
import com.fkhwl.scf.wrapper.ScfUserViewConverterWrapper;
import com.fkhwl.starter.core.util.DigestUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 自定义的Realm
 */
public class MyRealm extends AuthorizingRealm {

//  @Autowired
//  private UserService userService;
  @Resource
  private ScfUserProvider scfUserProvider;
  @Resource
  private CompanyProvider companyProvider;
  @Resource
  private CompanyContractProvider companyContractProvider;

  // 设置realm的名称
  @Override
  public void setName(String name) {
    super.setName("customRealm");
  }
  /**
   * 认证的方法，登录时执行
   *
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    //System.out.println("————身份认证方法————");
    // token是用户输入的用户名和密码
    // 第一步从token中取出用户名
    final String username = (String) token.getPrincipal();
    String password = null;
    final Object credentials = token.getCredentials();
    if (credentials instanceof char[]) {
      password = new String((char[]) credentials);
    }

    // 第二步：根据用户输入从数据库查询用户信息
    ScfUserDTO userDto = scfUserProvider.getByUserNameAndPassword(username,password);
     ScfUserVO user = ScfUserViewConverterWrapper.INSTANCE.vo(userDto);
    if (user == null) {
      throw new UnknownAccountException("账号或密码错误");
    }

      //查询对应的企业信息，平台超级管理员暂无企业信息
      CompanyDTO companyDTO = companyProvider.getByOwnerId(user.getParentId());
      if (companyDTO != null) {
          user.setCompanyId(companyDTO.getId());
          user.setCompanyName(companyDTO.getCompanyName());
          user.setCompanyType(companyDTO.getCompanyType());
      }

      //校验客户信息是否审核通过
      if (UserType.BORROWER.getValue().equals(user.getUserType()) || UserType.BORROWER_SUB.getValue().equals(user.getUserType())) {
          if (companyDTO == null) {
              throw new UnknownAccountException("用户信息未审核通过");
          }
          CompanyContractDTO contractDTO = companyContractProvider.getByBorrowCompanyId(companyDTO.getId());
          if (contractDTO == null || contractDTO.getStatus() != 1) {
              throw new UnknownAccountException("用户信息未审核通过");
          }
      }

      // 从数据库查询到密码
    //配合shiro配置的mc5加密(应该可以配置为不加密)
    if (password != null) {
      password = DigestUtils.md5Hex(password);
    }
    //加密的盐
    //String salt = user.getSalt();

    final HashMap<String, Object> principal = new HashMap<>();
    principal.put("user", user);
    return new SimpleAuthenticationInfo(principal, password, this.getName());
  }

  /**
   * 授权的方法，每次访问需要权限的接口都会执行
   *
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    //System.out.println("————权限认证————");
    //从principals获取主身份信息
    //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中的身份类型）

    //以下方法等效SecurityUtils.getSubject().getPrincipal() principals.getPrimaryPrincipal()
    //Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
    Map principal = (Map) principals.getPrimaryPrincipal();
      TemplateUserDTO user = (TemplateUserDTO) principal.get("user");
    List<String> permissions = (List<String>) principal.get("permissions");

    //查到权限数据，返回授权信息(要包括上边的permissions)
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    simpleAuthorizationInfo.addStringPermissions(permissions);//这里添加用户有的权限列表
    simpleAuthorizationInfo.addRole(user.getGender().getDesc());//这里添加用户所拥有的角色

    return simpleAuthorizationInfo;
  }

}
