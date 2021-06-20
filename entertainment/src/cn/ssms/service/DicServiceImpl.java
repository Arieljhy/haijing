/*    */ package cn.ssms.service;
/*    */ 
/*    */ import cn.ssms.dao.DicMapper;
/*    */ import cn.ssms.model.Dic;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service("dicService")
/*    */ public class DicServiceImpl
/*    */   implements DicService
/*    */ {
/*    */   @Autowired
/*    */   private DicMapper dicMapper;
/*    */   
/*    */   public List<Dic> getAllRoleList() {
/*    */     List<Dic> roleList;
/*    */     try {
/* 24 */       roleList = this.dicMapper.getAllRoleList();
/* 25 */     } catch (Exception e) {
/* 26 */       e.printStackTrace();
/* 27 */       return null;
/*    */     } 
/*    */     
/* 30 */     return roleList;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\DicServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */