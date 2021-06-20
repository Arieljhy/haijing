/*    */ package cn.ssms.util;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetRequestParam
/*    */ {
/*    */   public static Map<String, Object> setMap(HttpServletRequest request) {
/* 18 */     Map<String, Object> parm = new HashMap<>();
/* 19 */     Enumeration<?> enu = request.getParameterNames();
/* 20 */     while (enu.hasMoreElements()) {
/* 21 */       Object val = "";
/* 22 */       String paraName = (String)enu.nextElement();
/* 23 */       val = (request.getParameter(paraName) == null) ? "" : request.getParameter(paraName);
/* 24 */       parm.put(paraName, val);
/*    */     } 
/* 26 */     return parm;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\GetRequestParam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */