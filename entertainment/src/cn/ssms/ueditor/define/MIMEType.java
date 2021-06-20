/*    */ package cn.ssms.ueditor.define;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MIMEType
/*    */ {
/*  8 */   public static final Map<String, String> types = new HashMap<String, String>()
/*    */     {
/*    */       private static final long serialVersionUID = 1L;
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getSuffix(String mime) {
/* 21 */     return types.get(mime);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\define\MIMEType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */