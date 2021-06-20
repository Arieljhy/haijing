/*    */ package cn.ssms.ueditor.define;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ActionMap
/*    */ {
/* 26 */   public static final Map<String, Integer> mapping = new HashMap<String, Integer>() {
/*    */     
/*    */     };
/*    */   public static final int CONFIG = 0;
/*    */   public static final int UPLOAD_IMAGE = 1;
/*    */   public static final int UPLOAD_SCRAWL = 2;
/*    */   public static final int UPLOAD_VIDEO = 3;
/*    */   public static final int UPLOAD_FILE = 4;
/*    */   public static final int CATCH_IMAGE = 5;
/*    */   public static final int LIST_FILE = 6;
/*    */   public static final int LIST_IMAGE = 7;
/*    */   
/*    */   public static int getType(String key) {
/* 39 */     return ((Integer)mapping.get(key)).intValue();
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\define\ActionMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */