/*    */ package cn.ssms.ueditor;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/*    */ 
/*    */ public class CommonsMultipartResolverPhhc
/*    */   extends CommonsMultipartResolver
/*    */ {
/*    */   public boolean isMultipart(HttpServletRequest request) {
/* 10 */     String url = request.getRequestURI();
/* 11 */     System.out.println("url=" + url);
/* 12 */     if (url != null && url.contains("config")) {
/* 13 */       return false;
/*    */     }
/* 15 */     return super.isMultipart(request);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\CommonsMultipartResolverPhhc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */