/*     */ package cn.ssms.service;
/*     */ 
/*     */ import cn.ssms.dao.CommentsMapper;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.PageHelper;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class CommentsServiceImple
/*     */   implements CommentsService
/*     */ {
/*     */   @Autowired
/*     */   private CommentsMapper commentsMapper;
/*     */   
/*     */   public int insertComments(Map<String, Object> params) {
/*  29 */     return this.commentsMapper.insertComments(params);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteCommentsById(Map<String, Object> params) {
/*  35 */     return this.commentsMapper.deleteCommentsById(params);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommentsClassify(Map<String, Object> map) {
/*     */     try {
/*  41 */       Map<String, Object> params = new HashMap<>();
/*  42 */       String pageStr = (String)map.get("page");
/*  43 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/*  44 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/*  45 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  46 */       params.put("pageSize", pageSize);
/*     */       
/*  48 */       if (map.get("classifyId") != null && !map.get("classifyId").equals("")) {
/*  49 */         params.put("classifyId", map.get("classifyId"));
/*     */       }
/*     */       
/*  52 */       Gson gson = new Gson();
/*  53 */       List<Map<String, Object>> bbsList = this.commentsMapper.getCommentsClassify(params);
/*  54 */       Integer totalCount = Integer.valueOf(this.commentsMapper.getCommentsClassifyCount(params));
/*  55 */       String path = "getCommentsClassify.html?page=";
/*  56 */       PageHelper result = new PageHelper(totalCount, pageSize, page, gson.toJson(bbsList), path);
/*  57 */       return gson.toJson(result);
/*  58 */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getComments(Map<String, Object> map) {
/*     */     try {
/*  68 */       Map<String, Object> params = new HashMap<>();
/*  69 */       String pageStr = (String)map.get("page");
/*  70 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/*  71 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/*  72 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  73 */       params.put("pageSize", pageSize);
/*     */       
/*  75 */       params.put("id", map.get("id"));
/*  76 */       params.put("type", map.get("type"));
/*  77 */       Gson gson = new Gson();
/*  78 */       List<Map<String, Object>> bbsList = this.commentsMapper.selectByPrimaryKey(params);
/*  79 */       Integer totalCount = this.commentsMapper.selectByPrimaryKeytotal(params);
/*  80 */       String path = "getComments.html?page=";
/*  81 */       PageHelper result = new PageHelper(totalCount, pageSize, page, gson.toJson(bbsList), path);
/*  82 */       return gson.toJson(result);
/*  83 */     } catch (Exception e) {
/*  84 */       e.printStackTrace();
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String deleteComments(Map<String, Object> map) {
/*  92 */     Map<String, Object> params = new HashMap<>();
/*     */     try {
/*  94 */       this.commentsMapper.deleteCommentsById(map);
/*  95 */       params.put("flag", Boolean.valueOf(true));
/*  96 */     } catch (Exception e) {
/*  97 */       e.printStackTrace();
/*  98 */       params.put("flag", Boolean.valueOf(false));
/*     */     } 
/* 100 */     Gson gson = new Gson();
/* 101 */     return gson.toJson(params);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\CommentsServiceImple.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */