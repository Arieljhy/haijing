/*     */ package cn.ssms.service;
/*     */ import cn.ssms.dao.AdminMapper;
/*     */ import cn.ssms.dao.MenuMapper;
/*     */ import cn.ssms.dao.RegionAuthorMapper;
/*     */ import cn.ssms.dao.RoleMapper;
/*     */ import cn.ssms.model.Admin;
/*     */ import cn.ssms.model.AdminRole;
/*     */ import cn.ssms.model.Menu;
/*     */ import cn.ssms.model.RegionAuthor;
/*     */ import cn.ssms.model.Role;
/*     */ import cn.ssms.util.CipherUtil;
/*     */ import cn.ssms.util.DataAccessException;
/*     */ import cn.ssms.util.PageHelper;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.lang.StringEscapeUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.shiro.crypto.hash.Md5Hash;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Propagation;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("adminService")
/*     */ public class AdminServiceImpl implements AdminService {
/*     */   @Autowired
/*     */   private AdminMapper adminMapper;
/*     */   @Autowired
/*     */   private MenuMapper menuMapper;
/*     */   
/*     */   public Admin findAdminByCode(String userCode) {
/*     */     try {
/*  39 */       if (userCode == null || userCode.equals("")) {
/*  40 */         return null;
/*     */       }
/*  42 */       return this.adminMapper.findAdminByCode(userCode);
/*  43 */     } catch (Exception e) {
/*  44 */       e.printStackTrace();
/*  45 */       return null;
/*     */     } 
/*     */   } @Autowired
/*     */   private RoleMapper roleMapper; @Autowired
/*     */   private RegionAuthorMapper regionAuthorMapper;
/*     */   public List<Menu> getMenuListUserCode(String userCode) {
/*  51 */     List<Menu> menuList = new ArrayList<>();
/*     */     try {
/*  53 */       menuList = this.menuMapper.getMenuListUserCode(userCode);
/*  54 */     } catch (Exception e) {
/*  55 */       e.printStackTrace();
/*     */     } 
/*  57 */     return menuList;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAdminList(Map<String, Object> map) {
/*     */     try {
/*  63 */       List<Admin> adminList = this.adminMapper.getAdminList(map);
/*  64 */       Integer totalCount = this.adminMapper.getAdminListTotal(map);
/*  65 */       Gson gson = new Gson();
/*  66 */       String path = "getAdminList.html?page=";
/*  67 */       PageHelper result = new PageHelper(totalCount, Integer.valueOf(map.get("pageSize").toString()), Integer.valueOf(map.get("index").toString()), gson.toJson(adminList), path);
/*     */       
/*  69 */       return gson.toJson(result);
/*  70 */     } catch (Exception e) {
/*  71 */       e.printStackTrace();
/*  72 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRoleList(Map<String, Object> map) {
/*     */     try {
/*  79 */       List<Role> roleList = this.roleMapper.getRoleList(map);
/*  80 */       if (roleList != null && !roleList.isEmpty()) {
/*  81 */         for (int i = 0; i < roleList.size(); i++) {
/*  82 */           List<Menu> menus = this.menuMapper.getMenuByRoleId(((Role)roleList.get(i)).getId());
/*  83 */           ((Role)roleList.get(i)).setMenus(menus);
/*     */         } 
/*     */       }
/*  86 */       Integer totalCount = this.roleMapper.getRoleListTotal(map);
/*  87 */       Gson gson = new Gson();
/*  88 */       String path = "getRoleList.html?page=";
/*  89 */       PageHelper result = new PageHelper(totalCount, Integer.valueOf(map.get("pageSize").toString()), Integer.valueOf(map.get("index").toString()), gson.toJson(roleList), path);
/*     */       
/*  91 */       return gson.toJson(result);
/*  92 */     } catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
/*     */   public String addAdmin(Map<String, Object> map) throws DataAccessException {
/* 104 */     String userName = (String)map.get("userName");
/* 105 */     String loginCode = (String)map.get("loginCode");
/* 106 */     String oriPwd = (String)map.get("pwd");
/* 107 */     String post = (String)map.get("post");
/* 108 */     String state = (String)map.get("state");
/* 109 */     String tel = (String)map.get("tel");
/* 110 */     String role = (String)map.get("role");
/* 111 */     String bankNo = (String)map.get("bankNo");
/*     */ 
/*     */     
/* 114 */     String password = CipherUtil.generatePassword(oriPwd);
/* 115 */     String password_cipherText = (new Md5Hash(password, loginCode, 2)).toBase64();
/* 116 */     Admin admin = new Admin();
/* 117 */     admin.setUserName(userName);
/* 118 */     admin.setUserCode(loginCode);
/* 119 */     admin.setPassword(password_cipherText);
/* 120 */     admin.setPost(post);
/* 121 */     admin.setStatus(Integer.valueOf(state));
/* 122 */     admin.setBankNo(bankNo);
/* 123 */     admin.setTel(tel);
/*     */     
/* 125 */     Map<String, Object> result = new HashMap<>();
/*     */     
/* 127 */     Admin adminCheck = this.adminMapper.findAdminByCode(loginCode);
/* 128 */     if (adminCheck != null) {
/* 129 */       result.put("flag", Boolean.valueOf(false));
/* 130 */       result.put("message", "已存在的登录账号");
/* 131 */       return (new Gson()).toJson(result);
/*     */     } 
/* 133 */     this.adminMapper.addAdmin(admin);
/* 134 */     Integer adminId = admin.getId();
/*     */     
/* 136 */     String[] roles = role.split(",");
/* 137 */     for (String roleid : roles) {
/* 138 */       AdminRole adminRole = new AdminRole();
/* 139 */       adminRole.setAdminId(Long.valueOf(adminId.intValue()));
/* 140 */       adminRole.setRoleId(Long.valueOf(roleid));
/* 141 */       this.adminMapper.insertAdminRole(adminRole);
/*     */     } 
/*     */     
/* 144 */     result.put("flag", Boolean.valueOf(true));
/* 145 */     result.put("message", "新增成功");
/* 146 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
/*     */   public String updateByCode(Map<String, Object> map) throws DataAccessException {
/* 152 */     Admin admin = new Admin();
/*     */     
/* 154 */     String userName = (String)map.get("userName");
/* 155 */     String post = (String)map.get("post");
/* 156 */     String state = (String)map.get("state");
/* 157 */     String role = (String)map.get("role");
/* 158 */     String code = (String)map.get("code");
/* 159 */     String tel = (String)map.get("tel");
/*     */     
/* 161 */     Admin admimn1 = this.adminMapper.findAdminByCode(code);
/* 162 */     admimn1.setUserName(userName);
/* 163 */     admimn1.setPost(post);
/* 164 */     admimn1.setStatus(Integer.valueOf(state));
/* 165 */     admimn1.setUpdateDate("1");
/* 166 */     admimn1.setTel(tel);
/* 167 */     this.adminMapper.updateByCode(admimn1);
/*     */     
/* 169 */     this.adminMapper.removeRoleListByAdminId(Long.valueOf(admimn1.getId().intValue()));
/* 170 */     String[] roles = role.split(",");
/* 171 */     for (String roleid : roles) {
/* 172 */       AdminRole adminRole = new AdminRole();
/* 173 */       adminRole.setAdminId(Long.valueOf(admimn1.getId().intValue()));
/* 174 */       adminRole.setRoleId(Long.valueOf(roleid));
/* 175 */       this.adminMapper.insertAdminRole(adminRole);
/*     */     } 
/* 177 */     Map<String, Object> result = new HashMap<>();
/* 178 */     result.put("flag", Boolean.valueOf(true));
/* 179 */     result.put("message", "修改成功");
/* 180 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeByCode(Map<String, Object> map) throws DataAccessException {
/* 186 */     Admin admin = new Admin();
/* 187 */     String code = (String)map.get("code");
/* 188 */     admin.setStatus(Integer.valueOf(-1));
/* 189 */     admin.setUserCode(code);
/* 190 */     admin.setDelDate("1");
/* 191 */     this.adminMapper.updateByCode(admin);
/* 192 */     Map<String, Object> result = new HashMap<>();
/* 193 */     result.put("flag", Boolean.valueOf(true));
/* 194 */     result.put("message", "删除成功");
/* 195 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String updPwdbyCode(Map<String, Object> map) throws DataAccessException {
/* 202 */     Admin admin = new Admin();
/* 203 */     String code = (String)map.get("code");
/* 204 */     String oriPwd = (String)map.get("pwd");
/* 205 */     String password = CipherUtil.generatePassword(oriPwd);
/* 206 */     String password_cipherText = (new Md5Hash(password, code, 2)).toBase64();
/* 207 */     admin.setUserCode(code);
/* 208 */     admin.setPassword(password_cipherText);
/* 209 */     admin.setUpdateDate("1");
/* 210 */     this.adminMapper.updateByCode(admin);
/* 211 */     Map<String, Object> result = new HashMap<>();
/* 212 */     result.put("flag", Boolean.valueOf(true));
/* 213 */     result.put("message", "修改成功");
/* 214 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAdminMenuList(Map<String, Object> map) {
/* 220 */     String roleId = (String)map.get("role");
/* 221 */     List<Menu> menu1 = this.menuMapper.getMenuByRoleId(Integer.valueOf(roleId));
/* 222 */     List<Menu> menu2 = this.menuMapper.selectAllMenu();
/*     */     
/* 224 */     List<RegionAuthor> regionAuthors = this.regionAuthorMapper.getRegionAuthorByRoleId(Integer.valueOf(roleId));
/* 225 */     Map<String, Object> resultMap = new HashMap<>();
/* 226 */     resultMap.put("has", menu1);
/* 227 */     resultMap.put("all", menu2);
/* 228 */     resultMap.put("region", regionAuthors);
/* 229 */     return (new Gson()).toJson(resultMap);
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
/*     */   public String updRoleMenu(Map<String, Object> map) throws DataAccessException {
/* 235 */     Map<String, Object> params = new HashMap<>();
/* 236 */     Map<String, Object> result = new HashMap<>();
/* 237 */     Gson gson = new Gson();
/* 238 */     String roleidStr = (String)map.get("roleid");
/* 239 */     if (roleidStr == null || roleidStr.equals("")) {
/* 240 */       result.put("flag", Boolean.valueOf(false));
/* 241 */       result.put("message", "角色不能为空！");
/* 242 */       return gson.toJson(result);
/*     */     } 
/*     */     
/* 245 */     Long roleid = Long.valueOf(roleidStr);
/* 246 */     this.adminMapper.removeAuthorByRole(roleid);
/* 247 */     params.put("roleId", roleid);
/* 248 */     Object menus = map.get("menuids");
/* 249 */     String menusString = "";
/* 250 */     if (menus != null) {
/* 251 */       menusString = StringEscapeUtils.unescapeHtml(menus.toString());
/*     */     }
/* 253 */     JSONArray jan = JSONArray.fromObject(menusString);
/* 254 */     if (jan != null && !jan.equals("") && (
/* 255 */       jan != null || jan.size() != 0)) {
/* 256 */       for (int i = 0; i < jan.size(); i++) {
/* 257 */         params.remove("menuId");
/* 258 */         params.remove("authorList");
/* 259 */         JSONObject jo = JSONObject.fromObject(jan.get(i));
/* 260 */         String idStr = jo.getString("id");
/* 261 */         String author = jo.getString("authorlist");
/* 262 */         params.put("menuId", Long.valueOf(idStr));
/* 263 */         params.put("authorList", author);
/* 264 */         this.adminMapper.insertAuthor(params);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     result.put("flag", Boolean.valueOf(true));
/* 281 */     result.put("message", "修改成功！");
/* 282 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMenuList() {
/* 287 */     List<Menu> menus = this.menuMapper.selectAllMenu();
/* 288 */     return (new Gson()).toJson(menus);
/*     */   }
/*     */ 
/*     */   
/*     */   public String addRole(Map<String, Object> map) throws DataAccessException {
/* 293 */     Map<String, Object> params = new HashMap<>();
/* 294 */     Map<String, Object> result = new HashMap<>();
/* 295 */     Gson gson = new Gson();
/*     */     
/* 297 */     String roleName = (String)map.get("roleName");
/*     */     
/* 299 */     int role = this.adminMapper.checkRoleName(roleName);
/* 300 */     if (role <= 0) {
/* 301 */       params.put("roleName", roleName);
/* 302 */       params.put("roleId", Long.valueOf(-1L));
/* 303 */       this.adminMapper.addRole(params);
/* 304 */       Object menus = map.get("menus");
/* 305 */       String menusString = "";
/* 306 */       if (menus != null) {
/* 307 */         menusString = StringEscapeUtils.unescapeHtml(menus.toString());
/*     */       }
/* 309 */       JSONArray jan = JSONArray.fromObject(menusString);
/* 310 */       if (jan != null && !jan.equals("") && (
/* 311 */         jan != null || jan.size() != 0)) {
/* 312 */         for (int i = 0; i < jan.size(); i++) {
/* 313 */           params.remove("menuId");
/* 314 */           params.remove("authorList");
/* 315 */           JSONObject jo = JSONObject.fromObject(jan.get(i));
/* 316 */           String idStr = jo.getString("id");
/* 317 */           String author = jo.getString("authorlist");
/* 318 */           params.put("menuId", Long.valueOf(idStr));
/* 319 */           params.put("authorList", author);
/* 320 */           this.adminMapper.insertAuthor(params);
/*     */         } 
/*     */       }
/*     */       
/* 324 */       result.put("flag", Boolean.valueOf(true));
/* 325 */       result.put("message", "新增成功！");
/*     */     } else {
/* 327 */       result.put("flag", Boolean.valueOf(false));
/* 328 */       result.put("message", "角色名称重复，请重新填写！");
/*     */     } 
/*     */     
/* 331 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String removeRole(Map<String, Object> map) throws DataAccessException {
/* 336 */     Map<String, Object> result = new HashMap<>();
/* 337 */     Gson gson = new Gson();
/* 338 */     String roleIdStr = (String)map.get("roleId");
/* 339 */     Integer roleId = Integer.valueOf(roleIdStr);
/*     */     
/* 341 */     this.roleMapper.deleteRole(roleId);
/* 342 */     this.adminMapper.removeAuthorByRole(Long.valueOf(roleIdStr));
/*     */     
/* 344 */     this.regionAuthorMapper.removeResionAuthorByRoleId(roleId);
/* 345 */     result.put("flag", Boolean.valueOf(true));
/* 346 */     result.put("message", "删除成功！");
/* 347 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String stopOrUseRole(Map<String, Object> map) {
/* 352 */     Map<String, Object> result = new HashMap<>();
/* 353 */     Gson gson = new Gson();
/* 354 */     String roleIdStr = (String)map.get("roleId");
/* 355 */     String status = (String)map.get("status");
/* 356 */     Role role = new Role();
/* 357 */     role.setId(Integer.valueOf(roleIdStr));
/* 358 */     role.setState(Integer.valueOf(status));
/*     */     
/* 360 */     this.roleMapper.updateRole(role);
/* 361 */     String use = status.equals("1") ? "启用" : "禁用";
/* 362 */     result.put("flag", Boolean.valueOf(true));
/* 363 */     result.put("message", use + "成功！");
/* 364 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMenuListByType(Map<String, Object> map) {
/* 369 */     String type = (String)map.get("type");
/* 370 */     if ("son".equals(type + "")) {
/* 371 */       Integer parentId = Integer.valueOf((String)map.get("parentId"));
/* 372 */       return (new Gson()).toJson(this.menuMapper.getSonMenuList(parentId));
/*     */     } 
/*     */     
/* 375 */     return (new Gson()).toJson(this.menuMapper.getMenuList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String addMenu(Map<String, Object> map) {
/* 382 */     Menu menu = new Menu();
/* 383 */     Gson gson = new Gson();
/* 384 */     String type = (String)map.get("type");
/* 385 */     String menuName = (String)map.get("menuName");
/*     */     
/* 387 */     String menuUrl = (String)map.get("url");
/* 388 */     menu.setMenuName(menuName);
/* 389 */     menu.setUrl(menuUrl);
/* 390 */     menu.setMenuState(Integer.valueOf(1));
/* 391 */     menu.setAddDate(new Date());
/* 392 */     if ("son".equals(type + "")) {
/* 393 */       String parentid = (String)map.get("parentId");
/* 394 */       menu.setParentMenuId(Integer.valueOf(parentid));
/*     */     } else {
/*     */       
/* 397 */       menu.setParentMenuId(Integer.valueOf(0));
/*     */     } 
/*     */     
/* 400 */     this.menuMapper.addMenu(menu);
/* 401 */     Map<String, Object> result = new HashMap<>();
/* 402 */     result.put("flag", Boolean.valueOf(true));
/* 403 */     result.put("message", "新增成功！");
/* 404 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> updatePassword(Map<String, Object> params) throws DataAccessException {
/* 409 */     Admin admin = new Admin();
/* 410 */     String code = (String)params.get("code");
/* 411 */     String oriPwd = (String)params.get("pwd1");
/* 412 */     String password = CipherUtil.generatePassword(oriPwd);
/* 413 */     String password_cipherText = (new Md5Hash(password, code, 2)).toBase64();
/* 414 */     Map<String, Object> map = new HashMap<>();
/* 415 */     map.put("userCode", code);
/* 416 */     map.put("password", password_cipherText);
/* 417 */     Integer count = this.adminMapper.getAdminByPwd(map);
/* 418 */     if (count.intValue() < 1) {
/* 419 */       throw new DataAccessException("旧密码不正确!");
/*     */     }
/* 421 */     String newPwd = (String)params.get("pwd2");
/* 422 */     password = CipherUtil.generatePassword(newPwd);
/* 423 */     password_cipherText = (new Md5Hash(password, code, 2)).toBase64();
/* 424 */     admin.setUserCode(code);
/* 425 */     admin.setPassword(password_cipherText);
/* 426 */     admin.setUpdateDate("1");
/* 427 */     this.adminMapper.updateByCode(admin);
/* 428 */     Map<String, Object> result = new HashMap<>();
/* 429 */     result.put("flag", Boolean.valueOf(true));
/* 430 */     result.put("message", "修改成功");
/* 431 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String updateMenu(Map<String, Object> map) throws DataAccessException {
/* 436 */     String id = (String)map.get("id");
/* 437 */     String name = (String)map.get("name");
/* 438 */     if (StringUtils.isNotEmpty(id)) {
/* 439 */       Menu menu = this.menuMapper.getMenuById(Integer.valueOf(id));
/* 440 */       if (menu != null) {
/* 441 */         menu.setMenuName(name);
/* 442 */         int result = this.menuMapper.updateByPrimaryKeySelective(menu);
/* 443 */         return "true";
/*     */       } 
/* 445 */       throw new DataAccessException("菜单信息错误!");
/*     */     } 
/*     */     
/* 448 */     return "false";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Admin> findAdminByRoleId(String id) {
/* 454 */     return this.adminMapper.findAdminByRoleId(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> checkAdminRole(Map<String, Object> params, String userCode) {
/* 459 */     String id = (String)params.get("id");
/* 460 */     Map<String, Object> result = new HashMap<>();
/* 461 */     if (StringUtils.isNotEmpty(id)) {
/* 462 */       Map<String, Object> map = new HashMap<>();
/* 463 */       map.put("roleId", id);
/* 464 */       map.put("code", userCode);
/* 465 */       Admin admin1 = this.adminMapper.checkAdminRole(map);
/* 466 */       if (admin1 != null) {
/* 467 */         result.put("flag", Boolean.valueOf(false));
/*     */       } else {
/*     */         
/* 470 */         result.put("flag", Boolean.valueOf(true));
/*     */       } 
/*     */     } else {
/* 473 */       result.put("flag", Boolean.valueOf(false));
/*     */     } 
/* 475 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\AdminServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */