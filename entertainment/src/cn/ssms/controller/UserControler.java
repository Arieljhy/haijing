/*     */ package cn.ssms.controller;
/*     */ 
/*     */ import cn.ssms.model.Configure;
/*     */ import cn.ssms.model.User;
/*     */ import cn.ssms.service.UserService;
/*     */ import cn.ssms.util.GetRequestParam;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/user"})
/*     */ public class UserControler
/*     */ {
/*     */   @Autowired
/*     */   private UserService userService;
/*     */   
/*     */   @RequestMapping({"/toUserList"})
/*     */   public String toUserList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  32 */     Gson gson = new Gson();
/*  33 */     List<Configure> configures = this.userService.getConfig(Integer.valueOf(8));
/*  34 */     model.addAttribute("departments", configures);
/*  35 */     model.addAttribute("department", gson.toJson(configures));
/*  36 */     return "user/userList";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping({"/toUserInfo"})
/*     */   public String toUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  42 */     List<Configure> configures = this.userService.getConfig(Integer.valueOf(8));
/*  43 */     model.addAttribute("department", configures);
/*  44 */     return "user/userInfo";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/userList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String userList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  51 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     
/*     */     try {
/*  54 */       return this.userService.userList(map);
/*  55 */     } catch (Exception e) {
/*     */       
/*  57 */       e.printStackTrace();
/*  58 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getUserByCode"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String selectByUserCode(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  66 */     Gson gson = new Gson();
/*  67 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     
/*  69 */     User user = this.userService.selectByUserCode((String)map.get("code"));
/*     */     
/*  71 */     return gson.toJson(user);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/checkCertificateCode"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String checkCertificateCode(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  78 */     Gson gson = new Gson();
/*  79 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     
/*  81 */     User user = this.userService.checkCertificateCode((String)map.get("code"));
/*     */     
/*  83 */     return gson.toJson(user);
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
/*     */   @RequestMapping(value = {"/addUser"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  97 */     Gson gson = new Gson();
/*  98 */     Map<String, Object> resultMap = new HashMap<>();
/*  99 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 100 */     User user = null;
/*     */     try {
/* 102 */       user = this.userService.addUser(map);
/* 103 */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */       resultMap.put("flag", Boolean.valueOf(false));
/* 106 */       resultMap.put("msg", "新增失败!");
/* 107 */       return gson.toJson(resultMap);
/*     */     } 
/*     */     
/* 110 */     if (user == null) {
/* 111 */       resultMap.put("flag", Boolean.valueOf(false));
/* 112 */       resultMap.put("msg", "新增失败!");
/* 113 */       return gson.toJson(resultMap);
/*     */     } 
/* 115 */     resultMap.put("flag", Boolean.valueOf(true));
/* 116 */     resultMap.put("msg", "新增成功!");
/* 117 */     resultMap.put("data", user);
/* 118 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updUser"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updUser(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 127 */     Gson gson = new Gson();
/* 128 */     Map<String, Object> resultMap = new HashMap<>();
/* 129 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 130 */     User user = null;
/*     */     try {
/* 132 */       user = this.userService.updateUser(map);
/* 133 */     } catch (Exception e) {
/* 134 */       e.printStackTrace();
/* 135 */       resultMap.put("flag", Boolean.valueOf(false));
/* 136 */       resultMap.put("msg", "更新失败!");
/* 137 */       return gson.toJson(resultMap);
/*     */     } 
/*     */     
/* 140 */     if (user == null) {
/* 141 */       resultMap.put("flag", Boolean.valueOf(false));
/* 142 */       resultMap.put("msg", "更新失败!");
/* 143 */       return gson.toJson(resultMap);
/*     */     } 
/* 145 */     resultMap.put("flag", Boolean.valueOf(true));
/* 146 */     resultMap.put("msg", "更新成功!");
/* 147 */     resultMap.put("data", user);
/* 148 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/removeUser"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeUser(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 156 */     Gson gson = new Gson();
/* 157 */     Map<String, Object> resultMap = new HashMap<>();
/* 158 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 159 */     List<User> users = null;
/*     */     try {
/* 161 */       users = this.userService.removeuser((String)map.get("ids"));
/* 162 */     } catch (Exception e) {
/* 163 */       e.printStackTrace();
/* 164 */       resultMap.put("flag", Boolean.valueOf(false));
/* 165 */       resultMap.put("msg", "删除失败!");
/* 166 */       return gson.toJson(resultMap);
/*     */     } 
/*     */     
/* 169 */     if (users == null) {
/* 170 */       resultMap.put("flag", Boolean.valueOf(false));
/* 171 */       resultMap.put("msg", "删除失败!");
/* 172 */       return gson.toJson(resultMap);
/*     */     } 
/* 174 */     resultMap.put("flag", Boolean.valueOf(true));
/* 175 */     resultMap.put("msg", "删除成功!");
/* 176 */     resultMap.put("data", users);
/* 177 */     return gson.toJson(resultMap);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\UserControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */