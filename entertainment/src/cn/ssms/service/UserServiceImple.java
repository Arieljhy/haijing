/*     */ package cn.ssms.service;
/*     */ 
/*     */ import cn.ssms.dao.ConfigureMapper;
/*     */ import cn.ssms.dao.UserMapper;
/*     */ import cn.ssms.model.Configure;
/*     */ import cn.ssms.model.User;
/*     */ import cn.ssms.util.CipherUtil;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.PageHelper;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.shiro.crypto.hash.Md5Hash;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class UserServiceImple
/*     */   implements UserService
/*     */ {
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */   @Autowired
/*     */   private ConfigureMapper configureMapper;
/*     */   
/*     */   public User selectByUserCode(String Code) {
/*  31 */     return this.userMapper.selectByUserCode(Code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public User loginValidation(User user) {
/*  37 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public User getUserById(String idStr) {
/*  42 */     if (idStr == null || idStr.equals("")) {
/*  43 */       return null;
/*     */     }
/*  45 */     Integer id = Integer.valueOf(-1);
/*     */     try {
/*  47 */       id = Integer.valueOf(idStr);
/*  48 */     } catch (Exception e) {
/*  49 */       return null;
/*     */     } 
/*     */ 
/*     */     
/*  53 */     return this.userMapper.getUserById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String userList(Map<String, Object> map) throws Exception {
/*  58 */     Map<String, Object> params = new HashMap<>();
/*  59 */     Integer page = Integer.valueOf(1);
/*  60 */     Integer pageSize = Contant.PAGESIZE;
/*     */ 
/*     */     
/*  63 */     String userCode = (String)map.get("userCode");
/*  64 */     String name = (String)map.get("name");
/*  65 */     String stateStr = (String)map.get("state");
/*  66 */     String departmentStr = (String)map.get("department");
/*  67 */     String pageStr = (String)map.get("page");
/*  68 */     String pageSizeStr = (String)map.get("pageSize");
/*     */     
/*     */     try {
/*  71 */       if (userCode != null && !userCode.equals("")) {
/*  72 */         params.put("userCode", userCode);
/*     */       }
/*  74 */       if (name != null && !name.equals("")) {
/*  75 */         params.put("name", name);
/*     */       }
/*  77 */       if (stateStr != null && !stateStr.equals("")) {
/*  78 */         Integer state = Integer.valueOf(stateStr);
/*  79 */         params.put("state", state);
/*     */       } 
/*  81 */       if (departmentStr != null && !departmentStr.equals("")) {
/*  82 */         Integer department = Integer.valueOf(departmentStr);
/*  83 */         params.put("department", department);
/*     */       } 
/*  85 */       if (pageStr != null && !pageStr.equals("")) {
/*  86 */         page = Integer.valueOf(pageStr);
/*     */       }
/*  88 */       if (pageSizeStr != null && !pageSizeStr.equals("")) {
/*  89 */         pageSize = Integer.valueOf(pageSizeStr);
/*     */       }
/*  91 */       params.put("beginNum", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  92 */       params.put("limitNum", pageSize);
/*     */     }
/*  94 */     catch (Exception e) {
/*  95 */       throw e;
/*     */     } 
/*  97 */     List<User> users = this.userMapper.selectUserByParams(params);
/*  98 */     Integer count = this.userMapper.countUserByParams(params);
/*  99 */     String path = "userList.html?page=";
/* 100 */     Gson gson = new Gson();
/* 101 */     PageHelper result = new PageHelper(count, pageSize, page, gson.toJson(users), path);
/* 102 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<User> removeuser(String idsStr) {
/* 107 */     List<User> users = new LinkedList<>();
/* 108 */     String[] ids = idsStr.split(",");
/* 109 */     for (int i = 0; i < ids.length; i++) {
/* 110 */       User user = new User();
/* 111 */       Integer id = Integer.valueOf(ids[i]);
/* 112 */       user.setId(id.intValue());
/* 113 */       user.setState(3);
/* 114 */       int count = this.userMapper.updateUserById(user).intValue();
/* 115 */       if (count < 1) {
/* 116 */         user.setState(0);
/*     */       }
/* 118 */       users.add(user);
/*     */     } 
/* 120 */     return users;
/*     */   }
/*     */ 
/*     */   
/*     */   public User addUser(Map<String, Object> map) {
/* 125 */     User user = new User();
/* 126 */     String name = (String)map.get("name");
/* 127 */     String department = (String)map.get("department");
/* 128 */     String userCode = (String)map.get("userCode");
/* 129 */     String oriPwd = (String)map.get("password");
/* 130 */     String militaryDate = (String)map.get("militaryDate");
/* 131 */     String certificateType = (String)map.get("certificateType");
/* 132 */     String certificateCode = (String)map.get("certificateCode");
/* 133 */     String state = (String)map.get("state");
/* 134 */     String userType = (String)map.get("userType");
/* 135 */     String icon = (String)map.get("icon");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     System.out.println("上传密码:" + oriPwd);
/* 141 */     String password = CipherUtil.generatePassword(oriPwd);
/* 142 */     System.out.println("解密密码:" + password);
/* 143 */     String password_cipherText = (new Md5Hash(password, userCode, 1)).toBase64();
/* 144 */     System.out.println(password_cipherText);
/* 145 */     System.out.println("加密密码:" + password);
/* 146 */     user.setName(name);
/* 147 */     user.setDepartment(Integer.valueOf(department).intValue());
/* 148 */     user.setUserCode(userCode);
/* 149 */     user.setPassword(password_cipherText);
/* 150 */     user.setMilitaryDate(militaryDate);
/* 151 */     user.setCertificateType(Integer.valueOf(certificateType).intValue());
/* 152 */     user.setCertificateCode(certificateCode);
/* 153 */     user.setState(Integer.valueOf(state).intValue());
/* 154 */     user.setUserType(Integer.valueOf(userType).intValue());
/* 155 */     user.setHeadImage(icon);
/*     */     
/* 157 */     User user0 = this.userMapper.selectByUserCode(userCode);
/* 158 */     if (user0 != null) {
/* 159 */       return null;
/*     */     }
/*     */     
/* 162 */     this.userMapper.addUser(user);
/*     */ 
/*     */     
/* 165 */     return user;
/*     */   }
/*     */ 
/*     */   
/*     */   public User updateUser(Map<String, Object> map) {
/* 170 */     User user = new User();
/* 171 */     String idStr = (String)map.get("id");
/* 172 */     String name = (String)map.get("name");
/* 173 */     String department = (String)map.get("department");
/* 174 */     String userCode = (String)map.get("userCode");
/* 175 */     String oriPwd = (String)map.get("password");
/* 176 */     String militaryDate = (String)map.get("militaryDate");
/* 177 */     String certificateType = (String)map.get("certificateType");
/* 178 */     String certificateCode = (String)map.get("certificateCode");
/* 179 */     String state = (String)map.get("state");
/* 180 */     String userType = (String)map.get("userType");
/* 181 */     String icon = (String)map.get("icon");
/* 182 */     System.out.println("上传密码:" + oriPwd);
/*     */ 
/*     */ 
/*     */     
/* 186 */     if (oriPwd != null && !oriPwd.equals("")) {
/* 187 */       String password = CipherUtil.generatePassword(oriPwd);
/* 188 */       System.out.println("解密密码:" + password);
/* 189 */       String password_cipherText = (new Md5Hash(password, userCode, 1)).toBase64();
/* 190 */       System.out.println(password_cipherText);
/* 191 */       user.setPassword(password_cipherText);
/*     */     } 
/*     */ 
/*     */     
/* 195 */     user.setId(Integer.valueOf(idStr).intValue());
/* 196 */     user.setName(name);
/* 197 */     user.setDepartment(Integer.valueOf(department).intValue());
/* 198 */     user.setUserCode(userCode);
/* 199 */     user.setMilitaryDate(militaryDate);
/* 200 */     user.setCertificateType(Integer.valueOf(certificateType).intValue());
/* 201 */     user.setCertificateCode(certificateCode);
/* 202 */     user.setState(Integer.valueOf(state).intValue());
/* 203 */     user.setUserType(Integer.valueOf(userType).intValue());
/* 204 */     user.setHeadImage(icon);
/*     */     
/* 206 */     Integer num = this.userMapper.updateUserById(user);
/*     */     
/* 208 */     if (num.intValue() < 1) {
/* 209 */       return null;
/*     */     }
/* 211 */     return user;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Configure> getConfig(Integer type) {
/* 217 */     List<Configure> configures = this.configureMapper.getConfigByType(type);
/* 218 */     return configures;
/*     */   }
/*     */ 
/*     */   
/*     */   public User checkCertificateCode(String code) {
/* 223 */     return this.userMapper.checkCertificateCode(code);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<User> getUserByConfigId(Map<String, Object> params) {
/* 228 */     return this.userMapper.getUserByConfigId(params);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\UserServiceImple.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */