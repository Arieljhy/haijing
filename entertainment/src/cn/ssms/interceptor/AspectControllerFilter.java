/*    */ package cn.ssms.interceptor;
/*    */ 
/*    */ import org.aspectj.lang.JoinPoint;
/*    */ import org.aspectj.lang.ProceedingJoinPoint;
/*    */ import org.aspectj.lang.annotation.After;
/*    */ import org.aspectj.lang.annotation.AfterReturning;
/*    */ import org.aspectj.lang.annotation.AfterThrowing;
/*    */ import org.aspectj.lang.annotation.Around;
/*    */ import org.aspectj.lang.annotation.Aspect;
/*    */ import org.aspectj.lang.annotation.Before;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Aspect
/*    */ public class AspectControllerFilter
/*    */ {
/*    */   @Before("execution(* cn.ssms.controller.*.*(..))")
/*    */   public void doBefore(JoinPoint jp) {}
/*    */   
/*    */   @After("execution(* cn.ssms.controller.*.*(..))")
/*    */   public void doAfter() {}
/*    */   
/*    */   @Around("execution(* cn.ssms.controller.*.*(..))")
/*    */   public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
/* 70 */     Object retVal = pjp.proceed();
/* 71 */     return retVal;
/*    */   }
/*    */ 
/*    */   
/*    */   @AfterReturning(value = "execution(* cn.ssms.controller.*.*(..))", returning = "retVal")
/*    */   public void doAfterReturning(JoinPoint jp, String retVal) {
/* 77 */     System.out.println("后置返回值通知，获得参数：" + retVal);
/*    */   }
/*    */ 
/*    */   
/*    */   @AfterThrowing(value = "execution(* cn.ssms.controller.*.*(..))", throwing = "ex")
/*    */   public void afterThrowing(JoinPoint jp, Throwable ex) {
/* 83 */     System.out.println("抛出异常通知" + ex + "==");
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\interceptor\AspectControllerFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */