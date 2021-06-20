/*    */ package cn.ssms.ueditor;
/*    */ 
/*    */ 
/*    */ public class Encoder
/*    */ {
/*    */   public static String toUnicode(String input) {
/*  7 */     StringBuilder builder = new StringBuilder();
/*  8 */     char[] chars = input.toCharArray();
/*    */     
/* 10 */     for (char ch : chars) {
/*    */       
/* 12 */       if (ch < 'Ā') {
/* 13 */         builder.append(ch);
/*    */       } else {
/* 15 */         builder.append("\\u" + Integer.toHexString(ch & Character.MAX_VALUE));
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 20 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\Encoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */