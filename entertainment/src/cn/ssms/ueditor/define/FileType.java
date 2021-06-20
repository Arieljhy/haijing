/*    */ package cn.ssms.ueditor.define;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class FileType
/*    */ {
/*    */   public static final String JPG = "JPG";
/* 10 */   private static final Map<String, String> types = new HashMap<String, String>()
/*    */     {
/*    */       private static final long serialVersionUID = 1L;
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getSuffix(String key) {
/* 21 */     return types.get(key);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getSuffixByFilename(String filename) {
/* 31 */     return filename.substring(filename.lastIndexOf(".")).toLowerCase();
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\define\FileType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */