/*     */ package cn.ssms.controller;
/*     */ 
/*     */ import cn.ssms.model.Admin;
/*     */ import cn.ssms.realm.ShiroDbRealm;
/*     */ import cn.ssms.service.AdminService;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.GetRequestParam;
/*     */ import cn.ssms.util.TextUtil;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.subject.Subject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/admin"})
/*     */ public class AdminControler
/*     */ {
/*  30 */   private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
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
/*     */   @RequestMapping({"/adminList"})
/*     */   public String adminList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  44 */     String userCode = request.getParameter("username");
/*  45 */     model.addAttribute("userCode", userCode);
/*  46 */     logger.debug("来自IP[" + request.getRemoteHost() + "]的访问 ");
/*  47 */     return "admin/adminList";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getAdminList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getAdminList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  60 */     logger.debug("来自IP[" + request.getRemoteHost() + "]的访问 ");
/*  61 */     Integer page = Integer.valueOf((request.getParameter("page") == null) ? 1 : Integer.valueOf(request.getParameter("page")).intValue());
/*  62 */     Integer pageSize = (request.getParameter("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(request.getParameter("pageSize"));
/*  63 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  64 */     Subject currentUser = SecurityUtils.getSubject();
/*  65 */     map.put("filter", currentUser.getPrincipal());
/*  66 */     map.put("index", page);
/*  67 */     map.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  68 */     map.put("pageSize", pageSize);
/*  69 */     String result = this.adminService.getAdminList(map);
/*  70 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getAdminMenuList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getAdminMenuList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  83 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*  84 */     return this.adminService.getAdminMenuList(params);
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
/*     */   @RequestMapping({"/roleList"})
/*     */   public String roleList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  97 */     logger.debug("来自IP[" + request.getRemoteHost() + "]的访问 ");
/*  98 */     return "admin/roleList";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/menuList"})
/*     */   public String menuList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 103 */     logger.debug("来自IP[" + request.getRemoteHost() + "]的访问 ");
/* 104 */     return "admin/menuList";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getRoleList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getRoleList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 117 */     logger.debug("来自IP[" + request.getRemoteHost() + "]的访问 ");
/* 118 */     Integer page = Integer.valueOf((request.getParameter("page") == null) ? 1 : Integer.valueOf(request.getParameter("page")).intValue());
/* 119 */     Integer pageSize = (request.getParameter("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(request.getParameter("pageSize"));
/* 120 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 121 */     map.put("index", page);
/* 122 */     map.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 123 */     map.put("pageSize", pageSize);
/* 124 */     String result = this.adminService.getRoleList(map);
/* 125 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/addAdmin"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addAdmin(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 138 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 140 */       return this.adminService.addAdmin(params);
/* 141 */     } catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       Map<String, Object> result = new HashMap<>();
/* 144 */       result.put("flag", Boolean.valueOf(false));
/* 145 */       result.put("message", "新增失败");
/* 146 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getUserByCode"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getUserByCode(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 160 */     String userCode = request.getParameter("code");
/* 161 */     Admin admin = this.adminService.findAdminByCode(userCode);
/* 162 */     if (admin != null) {
/* 163 */       admin.setPassword("");
/* 164 */       return (new Gson()).toJson(admin);
/*     */     } 
/* 166 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updUserByCode"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updUserByCode(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 179 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 181 */       return this.adminService.updateByCode(params);
/* 182 */     } catch (Exception e) {
/* 183 */       e.printStackTrace();
/* 184 */       Map<String, Object> result = new HashMap<>();
/* 185 */       result.put("flag", Boolean.valueOf(false));
/* 186 */       result.put("message", "修改失败");
/* 187 */       return (new Gson()).toJson(result);
/*     */     } 
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
/*     */   @RequestMapping(value = {"/removeByCode"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeByCode(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 202 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 204 */       return this.adminService.removeByCode(params);
/* 205 */     } catch (Exception e) {
/* 206 */       e.printStackTrace();
/* 207 */       Map<String, Object> result = new HashMap<>();
/* 208 */       result.put("flag", Boolean.valueOf(false));
/* 209 */       result.put("message", "删除失败");
/* 210 */       return (new Gson()).toJson(result);
/*     */     } 
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
/*     */   @RequestMapping(value = {"/updPwdbyCode"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updPwdbyCode(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 225 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 227 */       return this.adminService.updPwdbyCode(params);
/* 228 */     } catch (Exception e) {
/* 229 */       e.printStackTrace();
/* 230 */       Map<String, Object> result = new HashMap<>();
/* 231 */       result.put("flag", Boolean.valueOf(false));
/* 232 */       result.put("message", "修改失败");
/* 233 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updRoleMenuUrl"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updRoleMenuUrl(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 247 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 249 */       return this.adminService.updRoleMenu(params);
/* 250 */     } catch (Exception e) {
/* 251 */       e.printStackTrace();
/* 252 */       Map<String, Object> result = new HashMap<>();
/* 253 */       result.put("flag", Boolean.valueOf(false));
/* 254 */       result.put("message", "修改失败");
/* 255 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getMenuList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getMenuList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 262 */     return this.adminService.getMenuList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/checkRole"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String checkRole(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 274 */     String id = request.getParameter("id");
/* 275 */     List<Admin> admin = this.adminService.findAdminByRoleId(id);
/* 276 */     if (admin.size() > 0) {
/* 277 */       id = "true";
/* 278 */       return id;
/*     */     } 
/* 280 */     return "false";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/addRole"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addRole(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 293 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 295 */       return this.adminService.addRole(params);
/* 296 */     } catch (Exception e) {
/* 297 */       e.printStackTrace();
/* 298 */       Map<String, Object> result = new HashMap<>();
/* 299 */       result.put("flag", Boolean.valueOf(false));
/* 300 */       result.put("message", "新增失败");
/* 301 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/removeRole"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeRole(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 315 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 317 */       return this.adminService.removeRole(params);
/* 318 */     } catch (Exception e) {
/* 319 */       e.printStackTrace();
/* 320 */       Map<String, Object> result = new HashMap<>();
/* 321 */       result.put("flag", Boolean.valueOf(false));
/* 322 */       result.put("message", "删除失败");
/* 323 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/checkAdminRole"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String checkAdminRole(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 336 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 337 */     String userCode = TextUtil.getString(request.getSession().getAttribute("userCode"));
/*     */     try {
/* 339 */       Map<String, Object> map = this.adminService.checkAdminRole(params, userCode);
/* 340 */       return (new Gson()).toJson(map);
/* 341 */     } catch (Exception e) {
/* 342 */       e.printStackTrace();
/* 343 */       Map<String, Object> result = new HashMap<>();
/* 344 */       result.put("flag", Boolean.valueOf(false));
/* 345 */       result.put("message", "操作失败");
/* 346 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/stopOrUseRole"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String stopOrUseRole(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 359 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 361 */       return this.adminService.stopOrUseRole(params);
/* 362 */     } catch (Exception e) {
/* 363 */       e.printStackTrace();
/* 364 */       Map<String, Object> result = new HashMap<>();
/* 365 */       result.put("flag", Boolean.valueOf(false));
/* 366 */       result.put("message", "操作失败");
/* 367 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getMenuListByType"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getMenuListByType(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 381 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 383 */       return this.adminService.getMenuListByType(params);
/* 384 */     } catch (Exception e) {
/* 385 */       e.printStackTrace();
/* 386 */       Map<String, Object> result = new HashMap<>();
/* 387 */       result.put("flag", Boolean.valueOf(false));
/* 388 */       result.put("message", "操作失败");
/* 389 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateMenu"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updateMenu(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 402 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 404 */       String result = this.adminService.updateMenu(params);
/* 405 */       return result;
/* 406 */     } catch (Exception e) {
/* 407 */       e.printStackTrace();
/* 408 */       return "false";
/*     */     } 
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/addMenu"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addMenu(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 415 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 417 */       return this.adminService.addMenu(params);
/* 418 */     } catch (Exception e) {
/* 419 */       e.printStackTrace();
/* 420 */       Map<String, Object> result = new HashMap<>();
/* 421 */       result.put("flag", Boolean.valueOf(false));
/* 422 */       result.put("message", "操作失败");
/* 423 */       return (new Gson()).toJson(result);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\AdminControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */