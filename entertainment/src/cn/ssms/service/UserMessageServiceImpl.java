/*    */ package cn.ssms.service;
/*    */ 
/*    */ import cn.ssms.dao.UserMessageMapper;
/*    */ import cn.ssms.model.UserMessage;
/*    */ import cn.ssms.util.Contant;
/*    */ import cn.ssms.util.PageHelper;
/*    */ import com.google.gson.Gson;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class UserMessageServiceImpl
/*    */   implements UserMessageService
/*    */ {
/*    */   @Autowired
/*    */   private UserMessageMapper userMessageMapper;
/*    */   
/*    */   public String getlist(Map<String, Object> map) throws Exception {
/* 25 */     Map<String, Object> params = new HashMap<>();
/* 26 */     Integer page = Integer.valueOf(1);
/* 27 */     Integer pageSize = Contant.PAGESIZE;
/*    */ 
/*    */     
/* 30 */     String fromPeople = (String)map.get("fromPeople");
/* 31 */     String toPeople = (String)map.get("toPeople");
/* 32 */     String type = (String)map.get("type");
/* 33 */     String content = (String)map.get("content");
/* 34 */     String isPrivate = (String)map.get("isPrivate");
/* 35 */     String pageStr = (String)map.get("page");
/* 36 */     String pageSizeStr = (String)map.get("pageSize");
/*    */     
/*    */     try {
/* 39 */       if (fromPeople != null && !fromPeople.equals("")) {
/* 40 */         params.put("from", fromPeople);
/*    */       }
/* 42 */       if (toPeople != null && !toPeople.equals("")) {
/* 43 */         params.put("to", toPeople);
/*    */       }
/* 45 */       if (type != null && !type.equals("")) {
/* 46 */         params.put("type", Integer.valueOf(type));
/*    */       }
/* 48 */       if (content != null && !content.equals("")) {
/* 49 */         params.put("content", content);
/*    */       }
/* 51 */       if (isPrivate != null) {
/* 52 */         if (isPrivate.equals("1")) {
/* 53 */           params.put("isPrivate", Integer.valueOf(1));
/* 54 */         } else if (isPrivate.equals("2")) {
/* 55 */           params.put("isPublic", Integer.valueOf(1));
/*    */         } 
/*    */       }
/*    */       
/* 59 */       if (pageStr != null && !pageStr.equals("")) {
/* 60 */         page = Integer.valueOf(pageStr);
/*    */       }
/* 62 */       if (pageSizeStr != null && !pageSizeStr.equals("")) {
/* 63 */         pageSize = Integer.valueOf(pageSizeStr);
/*    */       }
/* 65 */       params.put("beginNum", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 66 */       params.put("limitNum", pageSize);
/*    */     }
/* 68 */     catch (Exception e) {
/* 69 */       throw e;
/*    */     } 
/* 71 */     List<UserMessage> userMessage = this.userMessageMapper.getAllMessageByParams(params);
/* 72 */     Integer count = this.userMessageMapper.countByParams(params);
/*    */     
/* 74 */     Gson gson = new Gson();
/* 75 */     PageHelper result = new PageHelper(count, pageSize, page, gson.toJson(userMessage), "");
/* 76 */     return gson.toJson(result);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\UserMessageServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */