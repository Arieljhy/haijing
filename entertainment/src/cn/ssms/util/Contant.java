/*    */ package cn.ssms.util;
/*    */ 
/*    */ import cn.ssms.util.tokenUtil.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Contant
/*    */ {
/* 12 */   public static Integer PAGESIZE = Integer.valueOf(5);
/*    */   
/* 14 */   public static String PASSWORD = "123456";
/*    */   
/* 16 */   public static String FILEURL = "E:\\tools\\nginx-flv\\objs\\resource\\";
/*    */   
/* 18 */   public static String[] TYPE = new String[] { "视频", "音频", "书籍", "游戏" };
/*    */   
/* 20 */   public static String bbsPhotoUrlString = "http://191.168.19.53:9281/entertainment";
/*    */   
/*    */   static {
/* 23 */     bbsPhotoUrlString = StringUtil.readProperties("bbs.photo.url");
/* 24 */     FILEURL = StringUtil.readProperties("resource.fileUrl");
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\Contant.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */