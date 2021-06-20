/*     */ package cn.ssms.util.tokenUtil;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringUtil
/*     */ {
/*     */   public static boolean isEmpty(Object obj) {
/*  26 */     if (obj == null)
/*  27 */       return true; 
/*  28 */     if (obj.toString().trim().length() == 0) {
/*  29 */       return true;
/*     */     }
/*  31 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String source) {
/*  41 */     if (source == null || source.length() == 0)
/*  42 */       return true; 
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String readProperties(String attrName) {
/*  53 */     String attrValue = loadProperties(attrName);
/*  54 */     if (!isEmpty(attrValue))
/*  55 */       return attrValue; 
/*  56 */     Properties p = new Properties();
/*     */     try {
/*  58 */       InputStream inputStream = StringUtil.class.getClassLoader().getResourceAsStream("config.properties");
/*  59 */       p.load(inputStream);
/*  60 */       inputStream.close();
/*  61 */     } catch (Exception ex) {
/*  62 */       ex.printStackTrace();
/*     */     } 
/*  64 */     return p.getProperty(attrName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String loadProperties(String attrName) {
/*  74 */     Properties p = new Properties();
/*     */     try {
/*  76 */       String url = StringUtil.class.getClassLoader().getResource("/").getPath();
/*     */ 
/*     */       
/*  79 */       InputStream fileInputSteam = new FileInputStream(url + "/config.properties");
/*  80 */       InputStream inputStream = new BufferedInputStream(fileInputSteam);
/*  81 */       p.load(inputStream);
/*  82 */       inputStream.close();
/*  83 */     } catch (Exception ex) {
/*  84 */       ex.printStackTrace();
/*     */     } 
/*  86 */     return p.getProperty(attrName);
/*     */   }
/*     */   
/*  89 */   String realpath = "";
/*  90 */   List<Map<String, Object>> list = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map<String, Object>> getList() {
/* 125 */     return this.list;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\tokenUtil\StringUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */