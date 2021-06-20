/*     */ package cn.ssms.realm;
/*     */ 
/*     */ import cn.ssms.model.Admin;
/*     */ import cn.ssms.service.AdminService;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.authc.AuthenticationException;
/*     */ import org.apache.shiro.authc.AuthenticationInfo;
/*     */ import org.apache.shiro.authc.AuthenticationToken;
/*     */ import org.apache.shiro.authc.LockedAccountException;
/*     */ import org.apache.shiro.authc.SimpleAuthenticationInfo;
/*     */ import org.apache.shiro.authc.UnknownAccountException;
/*     */ import org.apache.shiro.authc.UsernamePasswordToken;
/*     */ import org.apache.shiro.authz.AuthorizationInfo;
/*     */ import org.apache.shiro.authz.SimpleAuthorizationInfo;
/*     */ import org.apache.shiro.cache.Cache;
/*     */ import org.apache.shiro.realm.AuthorizingRealm;
/*     */ import org.apache.shiro.session.Session;
/*     */ import org.apache.shiro.subject.PrincipalCollection;
/*     */ import org.apache.shiro.subject.SimplePrincipalCollection;
/*     */ import org.apache.shiro.subject.Subject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ public class ShiroDbRealm
/*     */   extends AuthorizingRealm
/*     */ {
/*  30 */   private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private AdminService adminService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
/*  47 */     UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
/*  48 */     Admin admin = this.adminService.findAdminByCode(token.getUsername());
/*  49 */     if (admin != null) {
/*  50 */       if (0 == admin.getStatus().intValue()) {
/*  51 */         throw new LockedAccountException();
/*     */       }
/*  53 */       Subject currentUser = SecurityUtils.getSubject();
/*  54 */       Session session = currentUser.getSession();
/*  55 */       session.setAttribute("id", admin.getId());
/*  56 */       session.setAttribute("userCode", admin.getUserCode());
/*  57 */       session.setAttribute("userName", admin.getUserName());
/*  58 */       session.setAttribute("userType", admin.getType());
/*     */ 
/*     */       
/*  61 */       return (AuthenticationInfo)new SimpleAuthenticationInfo(admin.getUserCode(), admin.getPassword(), getName());
/*     */     } 
/*  63 */     throw new UnknownAccountException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
/*  72 */     logger.info("----------获取权限----------");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     Set<String> roleNames = new HashSet<>();
/*  79 */     Set<String> permissions = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     roleNames.add("menu");
/*  86 */     permissions.add("user.do?myjsp");
/*  87 */     permissions.add("login.do?main");
/*  88 */     permissions.add("/views/showUser.jsp");
/*  89 */     SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
/*  90 */     info.setStringPermissions(permissions);
/*  91 */     return (AuthorizationInfo)info;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCachedAuthorizationInfo(String principal) {
/*  98 */     SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
/*  99 */     clearCachedAuthorizationInfo((PrincipalCollection)principals);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAllCachedAuthorizationInfo() {
/* 106 */     Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
/* 107 */     if (cache != null)
/* 108 */       for (Object key : cache.keys())
/* 109 */         cache.remove(key);  
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\realm\ShiroDbRealm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */