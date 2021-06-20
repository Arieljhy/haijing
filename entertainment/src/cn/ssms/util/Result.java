/*    */ package cn.ssms.util;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Result<T>
/*    */   implements Serializable
/*    */ {
/* 11 */   private int code = ResultCode.SUCCESS.code();
/*    */   private String message;
/*    */   private T data;
/*    */   
/*    */   public Result setCode(ResultCode resultCode) {
/* 16 */     this.code = resultCode.code();
/* 17 */     return this;
/*    */   }
/*    */   
/*    */   public int getCode() {
/* 21 */     return this.code;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 25 */     return this.message;
/*    */   }
/*    */   
/*    */   public Result setMessage(String message) {
/* 29 */     this.message = message;
/* 30 */     return this;
/*    */   }
/*    */   
/*    */   public T getData() {
/* 34 */     return this.data;
/*    */   }
/*    */   
/*    */   public Result setData(T data) {
/* 38 */     this.data = data;
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   public static Result isOk() {
/* 43 */     return new Result();
/*    */   }
/*    */   public static Result isFail() {
/* 46 */     Result result = new Result();
/* 47 */     result.setCode(ResultCode.FAIL);
/* 48 */     return result;
/*    */   }
/*    */   
/*    */   public Result msg(String msg) {
/* 52 */     setMessage(msg);
/* 53 */     return this;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return JSON.toJSONString(this);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\Result.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */