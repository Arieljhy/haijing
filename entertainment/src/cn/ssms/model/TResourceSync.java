/*    */ package cn.ssms.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
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
/*    */ public class TResourceSync
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private Date syncTime;
/*    */   private Date createTime;
/*    */   private String syncPath;
/*    */   
/*    */   public Integer getId() {
/* 33 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Date getSyncTime() {
/* 49 */     return this.syncTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSyncTime(Date syncTime) {
/* 58 */     this.syncTime = syncTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Date getCreateTime() {
/* 67 */     return this.createTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCreateTime(Date createTime) {
/* 76 */     this.createTime = createTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSyncPath() {
/* 85 */     return this.syncPath;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSyncPath(String syncPath) {
/* 94 */     this.syncPath = syncPath;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\TResourceSync.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */