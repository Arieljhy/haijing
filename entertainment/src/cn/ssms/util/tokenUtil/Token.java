/*    */ package cn.ssms.util.tokenUtil;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLDecoder;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.sf.json.JSONObject;
/*    */ 
/*    */ 
/*    */ public class Token
/*    */ {
/*    */   public static String getToken(String usercode) {
/* 13 */     Map<String, Object> header = new HashMap<>();
/* 14 */     header.put("typ", "JWT");
/* 15 */     header.put("alg", "HS256");
/* 16 */     Map<String, Object> payload = new HashMap<>();
/* 17 */     payload.put("iss", StringUtil.readProperties("copyright.url"));
/* 18 */     payload.put("usercode", usercode);
/* 19 */     long currentTime = System.currentTimeMillis();
/* 20 */     currentTime += 14400000L;
/* 21 */     payload.put("exp", Long.valueOf(currentTime));
/* 22 */     String token = Base64.getBase64(JSONObject.fromObject(header).toString()) + "." + Base64.getBase64(JSONObject.fromObject(payload).toString());
/* 23 */     token = token + "." + HMACSHA256.encode(token, StringUtil.readProperties("copyright.url"));
/* 24 */     return token;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Map<String, Object> verfiy(String token) {
/* 29 */     Map<String, Object> result = new HashMap<>();
/* 30 */     if (token != null) {
/* 31 */       String[] datas = token.split("\\.");
/* 32 */       if (datas.length == 3) {
/* 33 */         String header = "";
/* 34 */         String payload = "";
/* 35 */         String hs = "";
/*    */         try {
/* 37 */           header = URLDecoder.decode(datas[0], "UTF-8");
/* 38 */           payload = URLDecoder.decode(datas[1], "UTF-8");
/* 39 */           hs = URLDecoder.decode(datas[2], "UTF-8");
/* 40 */         } catch (UnsupportedEncodingException e) {
/*    */           
/* 42 */           e.printStackTrace();
/*    */         } 
/* 44 */         String str = HMACSHA256.encode(header + "." + payload, StringUtil.readProperties("copyright.url"));
/* 45 */         if (str.equals(hs)) {
/* 46 */           JSONObject json = JSONObject.fromObject(Base64.getFromBase64(payload));
/* 47 */           long currentTime = System.currentTimeMillis();
/* 48 */           if (currentTime < Long.valueOf(json.get("exp").toString()).longValue()) {
/* 49 */             result.put("usercode", json.get("usercode"));
/*    */             
/* 51 */             result.put("flag", Boolean.valueOf(true));
/*    */           } else {
/* 53 */             result.put("flag", Boolean.valueOf(false));
/* 54 */             result.put("message", "????????????????????????????????????????????????");
/*    */           } 
/*    */         } else {
/* 57 */           result.put("flag", Boolean.valueOf(false));
/* 58 */           result.put("message", "?????????????????????????????????????????????");
/*    */         } 
/*    */       } else {
/* 61 */         result.put("flag", Boolean.valueOf(false));
/* 62 */         result.put("message", "?????????????????????????????????????????????");
/*    */       } 
/*    */     } else {
/* 65 */       result.put("flag", Boolean.valueOf(false));
/* 66 */       result.put("message", "?????????????????????????????????????????????");
/*    */     } 
/* 68 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\????????????????????????20200916\??????????????????????????????????????????\????????????\entertainment\WEB-INF\classes\!\cn\ssm\\util\tokenUtil\Token.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */