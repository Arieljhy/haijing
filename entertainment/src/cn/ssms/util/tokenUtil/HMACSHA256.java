/*    */ package cn.ssms.util.tokenUtil;
/*    */ 
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import javax.crypto.Mac;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HMACSHA256
/*    */ {
/*    */   public static String encode(String data, String key) {
/*    */     try {
/* 15 */       SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
/* 16 */       Mac mac = Mac.getInstance("HmacSHA256");
/* 17 */       mac.init(signingKey);
/* 18 */       return byte2hex(mac.doFinal(data.getBytes()));
/* 19 */     } catch (NoSuchAlgorithmException e) {
/* 20 */       e.printStackTrace();
/* 21 */     } catch (InvalidKeyException e) {
/* 22 */       e.printStackTrace();
/*    */     } 
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String byte2hex(byte[] b) {
/* 29 */     StringBuilder hs = new StringBuilder();
/*    */     
/* 31 */     for (int n = 0; b != null && n < b.length; n++) {
/* 32 */       String stmp = Integer.toHexString(b[n] & 0xFF);
/* 33 */       if (stmp.length() == 1)
/* 34 */         hs.append('0'); 
/* 35 */       hs.append(stmp);
/*    */     } 
/* 37 */     return hs.toString().toUpperCase();
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\tokenUtil\HMACSHA256.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */