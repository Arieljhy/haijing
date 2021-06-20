/*    */ package cn.ssms.ueditor.upload;
/*    */ 
/*    */ import com.baidu.ueditor.define.State;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class Uploader {
/*  8 */   private HttpServletRequest request = null;
/*  9 */   private Map<String, Object> conf = null;
/*    */   
/*    */   public Uploader(HttpServletRequest request, Map<String, Object> conf) {
/* 12 */     this.request = request;
/* 13 */     this.conf = conf;
/*    */   }
/*    */   
/*    */   public final State doExec() {
/* 17 */     String filedName = (String)this.conf.get("fieldName");
/* 18 */     State state = null;
/*    */     
/* 20 */     if ("true".equals(this.conf.get("isBase64"))) {
/* 21 */       state = Base64Uploader.save(this.request.getParameter(filedName), this.conf);
/*    */     } else {
/*    */       
/* 24 */       state = BinaryUploader.save(this.request, this.conf);
/*    */     } 
/*    */     
/* 27 */     return state;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\uedito\\upload\Uploader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */