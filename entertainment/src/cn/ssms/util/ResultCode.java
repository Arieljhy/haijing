/*    */ package cn.ssms.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ResultCode
/*    */ {
/*  7 */   SUCCESS(200),
/*  8 */   FAIL(400),
/*  9 */   UNAUTHORIZED(401),
/* 10 */   NOT_FOUND(404),
/* 11 */   INTERNAL_SERVER_ERROR(500);
/*    */   
/*    */   private final int code;
/*    */   
/*    */   ResultCode(int code) {
/* 16 */     this.code = code;
/*    */   }
/*    */   
/*    */   public int code() {
/* 20 */     return this.code;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\ResultCode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */