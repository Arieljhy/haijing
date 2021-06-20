/*    */ package cn.ssms.util;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PrimaryKeyGenerate
/*    */ {
/* 14 */   private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
/* 15 */   private static Random r = new Random();
/*    */   
/*    */   private static String getTimeStamp() {
/* 18 */     return sdf.format(new Date());
/*    */   }
/*    */   
/*    */   private static String getRandomNum() {
/* 22 */     StringBuilder sb = new StringBuilder();
/* 23 */     for (int i = 0; i < 6; i++) {
/* 24 */       sb.append(r.nextInt(10));
/*    */     }
/* 26 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public static String getInstance(String str) {
/* 30 */     return str + getTimeStamp() + getRandomNum();
/*    */   }
/*    */   
/*    */   public static String getResult() {
/* 34 */     return getTimeStamp() + getRandomNum();
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\PrimaryKeyGenerate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */