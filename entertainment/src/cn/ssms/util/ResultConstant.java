/*    */ package cn.ssms.util;
/*    */ 
/*    */ import cn.ssms.util.tokenUtil.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResultConstant
/*    */ {
/*    */   public static String replaceTrim(String s) {
/* 13 */     if (!StringUtil.isEmpty(s))
/* 14 */       s = s.replaceAll("\\s*|\t|\r|\n|<[^>]+>", "").trim(); 
/* 15 */     return s;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\ResultConstant.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */