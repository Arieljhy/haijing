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
/*    */ 
/*    */ 
/*    */ public class Comments
/*    */ {
/*    */   private Integer id;
/*    */   private Integer foreignId;
/*    */   private Integer type;
/*    */   private String content;
/*    */   private Integer critics;
/*    */   private Integer state;
/*    */   private Date addDate;
/*    */   
/*    */   public Integer getId() {
/* 35 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 38 */     this.id = id;
/*    */   }
/*    */   public Integer getForeignId() {
/* 41 */     return this.foreignId;
/*    */   }
/*    */   public void setForeignId(Integer foreignId) {
/* 44 */     this.foreignId = foreignId;
/*    */   }
/*    */   public Integer getType() {
/* 47 */     return this.type;
/*    */   }
/*    */   public void setType(Integer type) {
/* 50 */     this.type = type;
/*    */   }
/*    */   public String getContent() {
/* 53 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 56 */     this.content = content;
/*    */   }
/*    */   public Integer getCritics() {
/* 59 */     return this.critics;
/*    */   }
/*    */   public void setCritics(Integer critics) {
/* 62 */     this.critics = critics;
/*    */   }
/*    */   public Integer getState() {
/* 65 */     return this.state;
/*    */   }
/*    */   public void setState(Integer state) {
/* 68 */     this.state = state;
/*    */   }
/*    */   public Date getAddDate() {
/* 71 */     return this.addDate;
/*    */   }
/*    */   public void setAddDate(Date addDate) {
/* 74 */     this.addDate = addDate;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Comments.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */