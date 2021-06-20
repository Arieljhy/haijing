/*    */ package cn.ssms.xss;
/*    */ 
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SQLFilter
/*    */ {
/*    */   public static String sqlInject(String str) {
/* 18 */     if (StringUtils.isBlank(str)) {
/* 19 */       return null;
/*    */     }
/*    */     
/* 22 */     str = StringUtils.replace(str, "'", "");
/* 23 */     str = StringUtils.replace(str, "\"", "");
/* 24 */     str = StringUtils.replace(str, ";", "");
/* 25 */     str = StringUtils.replace(str, "\\", "");
/*    */ 
/*    */     
/* 28 */     str = str.toLowerCase();
/*    */ 
/*    */     
/* 31 */     String[] keywords = { "master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "create", "drop" };
/*    */ 
/*    */     
/* 34 */     for (String keyword : keywords) {
/* 35 */       if (str.indexOf(keyword) != -1) {
/* 36 */         throw new RuntimeException("包含非法字符");
/*    */       }
/*    */     } 
/*    */     
/* 40 */     return str;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\xss\SQLFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */