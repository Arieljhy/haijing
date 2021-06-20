/*    */ package cn.ssms.service;
/*    */ 
/*    */ import cn.ssms.dao.UserLoginLogMapper;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class UserLoginLogServiceImple
/*    */   implements UserLoginLogService
/*    */ {
/*    */   @Autowired
/*    */   private UserLoginLogMapper userLoginLogMapper;
/*    */   
/*    */   public int insert(Map<String, Object> params) {
/* 19 */     return this.userLoginLogMapper.insert(params);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\UserLoginLogServiceImple.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */