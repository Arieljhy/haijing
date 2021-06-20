/*     */ package cn.ssms.controller;
/*     */ 
/*     */ import cn.ssms.model.Configure;
/*     */ import cn.ssms.model.File;
/*     */ import cn.ssms.model.User;
/*     */ import cn.ssms.service.ConfigureService;
/*     */ import cn.ssms.service.ResourceService;
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
/*     */ @Controller
/*     */ @RequestMapping({"/config"})
/*     */ public class ConfigController
/*     */ {
/*     */   @Autowired
/*     */   private ConfigureService configureService;
/*     */   @Autowired
/*     */   private ResourceService resourceService;
/*     */   @Autowired
/*     */   private UserService userService;
/*     */   
/*     */   @RequestMapping({"/toConfigList"})
/*     */   public String toUserList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  36 */     return "config/configList";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/configList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String configList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  42 */     Gson gson = new Gson();
/*  43 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  44 */     Map<String, Object> result = new HashMap<>();
/*     */     
/*     */     try {
/*  47 */       List<Configure> list = this.configureService.getConfigList();
/*     */       
/*  49 */       result.put("list", list);
/*  50 */       result.put("flag", Boolean.valueOf(true));
/*  51 */       return gson.toJson(result);
/*  52 */     } catch (Exception e) {
/*     */       
/*  54 */       e.printStackTrace();
/*  55 */       result.put("flag", Boolean.valueOf(false));
/*  56 */       result.put("msg", "获取失败！");
/*  57 */       return gson.toJson(result);
/*     */     } 
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/secondListUrl"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String secondListUrl(HttpServletRequest request, HttpServletResponse response, Model model, Integer type) {
/*  64 */     Gson gson = new Gson();
/*  65 */     Map<String, Object> result = new HashMap<>();
/*     */     
/*     */     try {
/*  68 */       List<Configure> list = this.configureService.getConfigByType(type);
/*  69 */       result.put("list", list);
/*  70 */       return gson.toJson(result);
/*  71 */     } catch (Exception e) {
/*     */       
/*  73 */       e.printStackTrace();
/*  74 */       result.put("flag", Boolean.valueOf(false));
/*  75 */       result.put("msg", "获取失败！");
/*  76 */       return gson.toJson(result);
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
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/saveConfig"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String saveConfig(HttpServletRequest request, HttpServletResponse response, String type, String name, String typeName, String configId) {
/*  93 */     Gson gson = new Gson();
/*  94 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  95 */     Map<String, Object> result = new HashMap<>();
/*     */     
/*     */     try {
/*  98 */       map.put("name", name);
/*     */       
/* 100 */       if (configId.isEmpty()) {
/* 101 */         map.put("type", type);
/* 102 */         map.put("type_name", typeName);
/* 103 */         int i = this.configureService.addConfig(map);
/* 104 */         if (i > 0) {
/* 105 */           result.put("flag", Boolean.valueOf(true));
/* 106 */           result.put("msg", "新增成功！");
/*     */         } else {
/* 108 */           result.put("flag", Boolean.valueOf(false));
/* 109 */           result.put("msg", "新增失败！");
/*     */         } 
/* 111 */         return gson.toJson(result);
/*     */       } 
/* 113 */       map.put("id", configId);
/* 114 */       int num = this.configureService.updateConfig(map);
/*     */       
/* 116 */       List<Configure> list = this.configureService.getConfigByType(Integer.valueOf(Integer.parseInt(configId)));
/* 117 */       if (list.size() > 0)
/*     */       {
/* 119 */         for (int i = 0; i < list.size(); i++) {
/* 120 */           Map<String, Object> param = new HashMap<>();
/* 121 */           param.put("type_name", name);
/* 122 */           param.put("id", Integer.valueOf(((Configure)list.get(i)).getId()));
/* 123 */           this.configureService.updateConfig(param);
/*     */         } 
/*     */       }
/* 126 */       if (num > 0) {
/* 127 */         result.put("flag", Boolean.valueOf(true));
/* 128 */         result.put("msg", "修改成功！");
/*     */       } else {
/* 130 */         result.put("flag", Boolean.valueOf(false));
/* 131 */         result.put("msg", "修改失败！");
/*     */       } 
/* 133 */       return gson.toJson(result);
/*     */ 
/*     */     
/*     */     }
/* 137 */     catch (Exception e) {
/* 138 */       e.printStackTrace();
/* 139 */       result.put("flag", Boolean.valueOf(false));
/* 140 */       result.put("msg", "新增失败！");
/* 141 */       return gson.toJson(result);
/*     */     } 
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/isReName"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isReName(HttpServletRequest request, HttpServletResponse response, String name, Integer type, String configId) {
/* 148 */     Gson gson = new Gson();
/* 149 */     Map<String, Object> resultMap = new HashMap<>();
/* 150 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 151 */     map.put("name", name);
/* 152 */     map.put("type", type);
/* 153 */     map.put("id", configId);
/* 154 */     List<Configure> list = this.configureService.isReName(map);
/* 155 */     if (list.size() > 0) {
/* 156 */       resultMap.put("flag", Integer.valueOf(1));
/* 157 */       resultMap.put("msg", "名称重复");
/* 158 */       return gson.toJson(resultMap);
/*     */     } 
/* 160 */     resultMap.put("flag", Integer.valueOf(0));
/* 161 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/isData"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isData(HttpServletRequest request, HttpServletResponse response, String id) {
/* 169 */     Gson gson = new Gson();
/* 170 */     Map<String, Object> resultMap = new HashMap<>();
/* 171 */     Map<String, Object> map = new HashMap<>();
/* 172 */     map.put("configId", id);
/* 173 */     List<User> list = this.userService.getUserByConfigId(map);
/* 174 */     List<File> listFile = this.resourceService.getFileByConfigId(map);
/*     */ 
/*     */     
/* 177 */     if (list.size() > 0 || listFile.size() > 0) {
/* 178 */       resultMap.put("flag", Integer.valueOf(1));
/* 179 */       resultMap.put("msg", "该分类有数据，不能删除");
/* 180 */       return gson.toJson(resultMap);
/*     */     } 
/* 182 */     resultMap.put("flag", Integer.valueOf(0));
/* 183 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/removeConfig"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeConfig(HttpServletRequest request, HttpServletResponse response, String id) {
/* 193 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 194 */     Map<String, Object> resultMap = new HashMap<>();
/* 195 */     map.put("id", id);
/* 196 */     map.put("state", Integer.valueOf(0));
/* 197 */     int num = this.configureService.updateConfig(map);
/*     */     
/* 199 */     if (num > 0) {
/* 200 */       resultMap.put("flag", Boolean.valueOf(true));
/* 201 */       resultMap.put("msg", "删除成功!");
/* 202 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/* 204 */     resultMap.put("flag", Boolean.valueOf(false));
/* 205 */     resultMap.put("msg", "删除失败!");
/* 206 */     return (new Gson()).toJson(resultMap);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\ConfigController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */