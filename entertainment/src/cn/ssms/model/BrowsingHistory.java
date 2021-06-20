/*    */ package cn.ssms.model;
/*    */ 
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
/*    */ public class BrowsingHistory
/*    */ {
/*    */   private Integer id;
/*    */   private Integer type;
/*    */   private Integer foreignId;
/*    */   private Integer state;
/*    */   private Date addDate;
/*    */   
/*    */   public Integer getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 34 */     this.id = id;
/*    */   }
/*    */   public Integer getType() {
/* 37 */     return this.type;
/*    */   }
/*    */   public void setType(Integer type) {
/* 40 */     this.type = type;
/*    */   }
/*    */   public Integer getForeignId() {
/* 43 */     return this.foreignId;
/*    */   }
/*    */   public void setForeignId(Integer foreignId) {
/* 46 */     this.foreignId = foreignId;
/*    */   }
/*    */   public Integer getState() {
/* 49 */     return this.state;
/*    */   }
/*    */   public void setState(Integer state) {
/* 52 */     this.state = state;
/*    */   }
/*    */   public Date getAddDate() {
/* 55 */     return this.addDate;
/*    */   }
/*    */   public void setAddDate(Date addDate) {
/* 58 */     this.addDate = addDate;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\BrowsingHistory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */