/*    */ package cn.ssms.util;
/*    */ 
/*    */ import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.MessageDigest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CipherUtil
/*    */ {
/* 10 */   private static final String[] hexDigits = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String generatePassword(String inputString) {
/* 20 */     return encodeByMD5(inputString);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean validatePassword(String password, String inputString) {
/* 31 */     if (password.equals(encodeByMD5(inputString))) {
/* 32 */       return true;
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static String encodeByMD5(String originString) {
/* 45 */     if (originString != null) {
/*    */       try {
/* 47 */         MessageDigest md = MessageDigest.getInstance("MD5");
/* 48 */         byte[] results = md.digest(originString.getBytes());
/* 49 */         String resultString = byteArrayToHexString(results);
/* 50 */         return resultString.toUpperCase();
/* 51 */       } catch (Exception ex) {
/* 52 */         ex.printStackTrace();
/*    */       } 
/*    */     }
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static String byteArrayToHexString(byte[] b) {
/* 65 */     StringBuffer resultSb = new StringBuffer();
/* 66 */     for (int i = 0; i < b.length; i++) {
/* 67 */       resultSb.append(byteToHexString(b[i]));
/*    */     }
/* 69 */     return resultSb.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static String byteToHexString(byte b) {
/* 79 */     int n = b;
/* 80 */     if (n < 0)
/* 81 */       n = 256 + n; 
/* 82 */     int d1 = n / 16;
/* 83 */     int d2 = n % 16;
/* 84 */     return hexDigits[d1] + hexDigits[d2];
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {


    String s = generatePassword("e10adc3949ba59abbe56e057f20f883e");
    System.out.println(s);
    String ss = (new Md5Hash(s, "admin", 2)).toBase64();
    System.out.println(ss);
    UsernamePasswordToken token = new UsernamePasswordToken("admin", ss);
    System.out.println(token);

}
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\CipherUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */