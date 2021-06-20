/*     */ package cn.ssms.service;
/*     */ 
/*     */ import cn.ssms.dao.LiveMapper;
/*     */ import cn.ssms.model.Live;
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
/*     */ @Service
/*     */ public class LiveServiceImpl
/*     */   implements LiveService
/*     */ {
/*     */   @Autowired
/*     */   private LiveMapper liveMapper;
/*     */   
/*     */   public String getLiveList(Map<String, Object> map) {
/*  27 */     List<Live> liveList = this.liveMapper.getLiveList(map);
/*  28 */     Integer totalCount = this.liveMapper.getLiveListTotal(map);
/*  29 */     Gson gson = new Gson();
/*  30 */     String path = "getLiveList.html?page=";
/*  31 */     PageHelper result = new PageHelper(totalCount, Integer.valueOf(map.get("pageSize").toString()), Integer.valueOf(map.get("index").toString()), gson.toJson(liveList), path);
/*     */     
/*  33 */     return gson.toJson(result);
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
/*     */   public Map<String, Object> removeById(Map<String, Object> params) {
/*  45 */     Map<String, Object> returnMap = new HashMap<>(2);
/*     */     try {
/*  47 */       String[] ids = params.get("ids").toString().split(",");
/*  48 */       params.put("state", "1");
/*  49 */       params.put("ids", ids);
/*  50 */       int id = this.liveMapper.updateStatusByIds(params);
/*  51 */       if (id > 0) {
/*  52 */         returnMap.put("flag", Boolean.valueOf(true));
/*  53 */         returnMap.put("message", "删除成功");
/*     */       } else {
/*  55 */         returnMap.put("flag", Boolean.valueOf(false));
/*  56 */         returnMap.put("message", "删除失败");
/*     */       } 
/*  58 */     } catch (NumberFormatException e) {
/*  59 */       returnMap.put("flag", Boolean.valueOf(false));
/*  60 */       returnMap.put("message", "删除失败");
/*     */     } 
/*  62 */     return returnMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Live getLive(String id) {
/*  73 */     return this.liveMapper.selectByPrimaryKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> saveOrUpdateLive(Live live) {
/*  84 */     Map<String, Object> map = new HashMap<>(2);
/*  85 */     boolean flag = false;
/*  86 */     String message = "保存失败";
/*     */     try {
/*  88 */       if (live.getId() != null) {
/*  89 */         Live live1 = this.liveMapper.selectByPrimaryKey(live.getId());
/*  90 */         boolean updateFlag = live1.getName().equals(live.getName());
/*  91 */         if (updateFlag) {
/*  92 */           int j = this.liveMapper.updateByPrimaryKeySelective(live);
/*  93 */           if (j > 0) {
/*  94 */             flag = true;
/*  95 */             message = "保存成功";
/*     */           } 
/*  97 */           map.put("flag", Boolean.valueOf(flag));
/*  98 */           map.put("message", message);
/*  99 */           return map;
/*     */         } 
/*     */       } 
/* 102 */       int i = 0;
/* 103 */       i = this.liveMapper.checkName(live.getName());
/* 104 */       if (i > 0) {
/* 105 */         message = "直播主题名称重复，请修改后再保存";
/*     */       } else {
/* 107 */         i = this.liveMapper.checkCode(live.getCode());
/* 108 */         if (i > 0) {
/* 109 */           message = "视频名称重复，请修改直播主题名称后再保存";
/*     */         } else {
/* 111 */           if (live.getId() != null) {
/* 112 */             Live live1 = this.liveMapper.selectByPrimaryKey(live.getId());
/*     */             
/* 114 */             i = this.liveMapper.updateByPrimaryKeySelective(live);
/*     */           } else {
/* 116 */             live.setState("0");
/* 117 */             i = this.liveMapper.insertSelective(live);
/*     */           } 
/* 119 */           if (i > 0) {
/* 120 */             flag = true;
/* 121 */             message = "保存成功";
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 126 */       map.put("flag", Boolean.valueOf(flag));
/* 127 */       map.put("message", message);
/*     */     }
/* 129 */     catch (Exception e) {
/* 130 */       map.put("flag", Boolean.valueOf(flag));
/* 131 */       map.put("message", message);
/*     */     } 
/* 133 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Live getLiveByCode(String code) {
/* 144 */     return this.liveMapper.selectByCode(code);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\LiveServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */