/*    */ package cn.ssms.model;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class Anser
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private Integer titleId;
/*    */   private String content;
/*    */   private String remark;
/*    */   private Integer score;
/*    */   private Integer status;
/*    */   private String addDate;
/*    */   
/*    */   public Integer getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 35 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Integer getTitleId() {
/* 39 */     return this.titleId;
/*    */   }
/*    */   
/*    */   public void setTitleId(Integer titleId) {
/* 43 */     this.titleId = titleId;
/*    */   }
/*    */   
/*    */   public String getContent() {
/* 47 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(String content) {
/* 51 */     this.content = content;
/*    */   }
/*    */   
/*    */   public String getRemark() {
/* 55 */     return this.remark;
/*    */   }
/*    */   
/*    */   public void setRemark(String remark) {
/* 59 */     this.remark = remark;
/*    */   }
/*    */   
/*    */   public Integer getScore() {
/* 63 */     return this.score;
/*    */   }
/*    */   
/*    */   public void setScore(Integer score) {
/* 67 */     this.score = score;
/*    */   }
/*    */   
/*    */   public Integer getStatus() {
/* 71 */     return this.status;
/*    */   }
/*    */   
/*    */   public void setStatus(Integer status) {
/* 75 */     this.status = status;
/*    */   }
/*    */   
/*    */   public String getAddDate() {
/* 79 */     return this.addDate;
/*    */   }
/*    */   
/*    */   public void setAddDate(String addDate) {
/* 83 */     this.addDate = addDate;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Anser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */