/*    */ package cn.ssms.util;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.util.List;
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
/*    */ public class HttpClientUtil
/*    */ {
/*    */   public static String sendGet(String url, String param) {
/* 21 */     String result = "";
/* 22 */     BufferedReader in = null;
/*    */     try {
/* 24 */       String urlNameString = url + "?" + param;
/* 25 */       URL realUrl = new URL(urlNameString);
/*    */       
/* 27 */       URLConnection connection = realUrl.openConnection();
/*    */       
/* 29 */       connection.setRequestProperty("accept", "*/*");
/* 30 */       connection.setRequestProperty("connection", "Keep-Alive");
/* 31 */       connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
/*    */ 
/*    */       
/* 34 */       connection.connect();
/*    */       
/* 36 */       Map<String, List<String>> map = connection.getHeaderFields();
/*    */       
/* 38 */       for (String key : map.keySet()) {
/* 39 */         System.out.println(key + "--->" + map.get(key));
/*    */       }
/*    */       
/* 42 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*    */       String line;
/* 44 */       while ((line = in.readLine()) != null) {
/* 45 */         result = result + line;
/*    */       }
/* 47 */     } catch (Exception e) {
/* 48 */       System.out.println("发送GET请求出现异常！" + e);
/* 49 */       e.printStackTrace();
/*    */     } finally {
/*    */ 
/*    */       
/*    */       try {
/* 54 */         if (in != null) {
/* 55 */           in.close();
/*    */         }
/* 57 */       } catch (Exception e2) {
/* 58 */         e2.printStackTrace();
/*    */       } 
/*    */     } 
/* 61 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\HttpClientUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */