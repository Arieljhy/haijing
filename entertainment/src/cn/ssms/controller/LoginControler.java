/*     */ package cn.ssms.controller;
/*     */ import cn.ssms.model.Admin;
/*     */ import cn.ssms.model.Menu;
/*     */ import cn.ssms.realm.ShiroDbRealm;
/*     */ import cn.ssms.service.AdminService;
/*     */ import cn.ssms.service.UserLoginLogService;
import cn.ssms.util.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.authc.*;
/*     */
/*     */
/*     */ import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
/*     */ import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
/*     */ import org.apache.shiro.subject.support.DefaultSubjectContext;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ public class LoginControler {
/*  35 */   private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private AdminService adminService;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private SessionDAO sessionDAO;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private UserLoginLogService userLoginLogService;
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"interface/uploadApk"})
/*     */   public String uploadApk(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  56 */     return "apk/index";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/tologin"})
/*     */   public String tologin(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  68 */     return "login";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/login"})
/*     */   public String login(HttpServletRequest request, Model model) {
/*     */     Admin _user;
/*  80 */     String errorMessage = "";
/*  81 */     String result = "login";
/*     */ 
/*     */     
/*  84 */     Collection<Session> sessions = this.sessionDAO.getActiveSessions();
/*     */     
/*  86 */     String userCode = request.getParameter("username");
/*  87 */     String pwd = request.getParameter("password");
/*  88 */     String password = "";
/*  89 */     String password_cipherText = "";


/*  90 */     if (pwd != null && !"".equals(pwd) && userCode != null && !"".equals(userCode)) {
/*  91 */       _user = this.adminService.findAdminByCode(userCode);


/*  92 */       if (_user == null) {
/*  93 */         errorMessage = "用户认证失败：不存在的用户.";
/*  94 */         model.addAttribute("error", "登录账号不存在!");
/*  95 */         logger.info(errorMessage);
/*  96 */         return "login";
/*     */       } 
/*  98 */       if (_user.getStatus().intValue() == 0) {
/*  99 */         errorMessage = "用户认证失败：用户停用.";
/* 100 */         model.addAttribute("error", "登录账号已禁用,不可登录!");
/* 101 */         logger.info(errorMessage);
/* 102 */         return "login";
/*     */       } 
/*     */       
/* 105 */       password = CipherUtil.generatePassword(request.getParameter("password"));
/* 106 */       password_cipherText = (new Md5Hash(password, userCode, 2)).toBase64();

/*     */     } else {
/* 108 */       return "login";
/*     */     } 
/* 110 */     UsernamePasswordToken token = new UsernamePasswordToken(userCode, password_cipherText);
/* 111 */     Subject currentUser = SecurityUtils.getSubject();
/* 112 */     for (Session session : sessions) {
/* 113 */       if (userCode.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))))
/*     */       {
/* 115 */         if (session.getAttribute("sessionState") != null || "0".equals(session.getAttribute("sessionState"))) {
/*     */           
/* 117 */           session.setAttribute("sessionState", "1");
/* 118 */           session.setTimeout(0L);
/*     */           
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 126 */       if (!currentUser.isAuthenticated()) {
/* 127 */         token.setRememberMe(true);
/* 128 */         currentUser.login((AuthenticationToken)token);
/*     */       } 
/*     */       
/* 131 */       Session sessionNow = currentUser.getSession();
/*     */       
/* 133 */       sessionNow.setAttribute("sessionState", "0");
/*     */       
/* 135 */       Map<String, Object> params = new HashMap<>();
/* 136 */       String ip = SystemUtils.getIpAddr(request);
/* 137 */       params.put("userId", _user.getId());
/* 138 */       params.put("IP", ip);
/* 139 */       params.put("sysOpt", SystemUtils.getHostName(ip));
/* 140 */       params.put("OS", SystemUtils.getRequestSystemInfo(request));
/* 141 */       params.put("browser", SystemUtils.getRequestBrowserInfo(request));
/* 142 */       params.put("type", Integer.valueOf(2));
/* 143 */       params.put("loginDate", new Date());
/* 144 */       this.userLoginLogService.insert(params);
/*     */       
/* 146 */       result = "redirect:index.html";
/* 147 */     } catch (UnknownAccountException uae) {
/* 148 */       errorMessage = "用户认证失败：username wasn't in the system.";
/* 149 */       logger.info(errorMessage);
/* 150 */       model.addAttribute("error", "用户不存在!");
/* 151 */       result = "login";
/* 152 */     } catch (IncorrectCredentialsException ice) {
/* 153 */       errorMessage = "用户认证失败：password didn't match.";
/* 154 */       model.addAttribute("error", "密码不正确!");
/* 155 */       logger.info(errorMessage);
/* 156 */       result = "login";
/* 157 */     } catch (LockedAccountException lae) {
/* 158 */       errorMessage = "用户认证失败：account for that username is locked - can't login.";
/* 159 */       model.addAttribute("error", "用户已锁定,不可登录!");
/* 160 */       logger.info(errorMessage);
/* 161 */       result = "login";
/* 162 */     } catch (Exception e) {
/* 163 */       logger.error(e.getMessage());
/* 164 */       model.addAttribute("error", "登录失败!");
/* 165 */       result = "login";
/*     */     } 
/* 167 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/index"})
/*     */   public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 180 */     String userCode = TextUtil.getString(request.getSession().getAttribute("userCode"));
/* 181 */     List<Menu> menulist = this.adminService.getMenuListUserCode(userCode);
/* 182 */     MenuHelper menu = new MenuHelper();
/* 183 */     String userName = TextUtil.getString(request.getSession().getAttribute("userName"));
/* 184 */     String menuString = menu.menuHTML(menulist, userName);
/* 185 */     model.addAttribute("menu", menuString);
/* 186 */     model.addAttribute("userName", userName);
/* 187 */     String result = "index";
/* 188 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/updatePwd"})
/*     */   public String updatePwd(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 200 */     return "updatePwd";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updatePassword"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updatePassword(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 213 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 214 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/* 216 */       String code = TextUtil.getString(request.getSession().getAttribute("userCode"));
/* 217 */       params.put("code", code);
/* 218 */       result = this.adminService.updatePassword(params);
/* 219 */     } catch (DataAccessException ee) {
/* 220 */       result.put("flag", Boolean.valueOf(false));
/* 221 */       result.put("message", ee.getMessage());
/* 222 */     } catch (Exception e) {
/* 223 */       e.printStackTrace();
/* 224 */       result.put("flag", Boolean.valueOf(false));
/* 225 */       result.put("message", "操作失败");
/*     */     } 
/* 227 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/home"})
/*     */   public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 239 */     return "home";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/logout"})
/*     */   public String logout() {
/* 247 */     Subject currentUser = SecurityUtils.getSubject();
/* 248 */     currentUser.logout();
/* 249 */     return "login";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/chklogin"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String chkLogin() {
/* 259 */     Subject currentUser = SecurityUtils.getSubject();
/* 260 */     if (!currentUser.isAuthenticated()) {
/* 261 */       return "false";
/*     */     }
/* 263 */     return "true";
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\LoginControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */