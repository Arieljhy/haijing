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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Advert
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String title;
/*    */   private String content;
/*    */   private Integer status;
/*    */   private String startDate;
/*    */   private String addDate;
/*    */   private Integer sort;
/*    */   private String author;
/*    */   private Integer type;
/*    */   private String abstracts;
/*    */   
/*    */   public Integer getId() {
/* 41 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 44 */     this.id = id;
/*    */   }
/*    */   public String getTitle() {
/* 47 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 50 */     this.title = title;
/*    */   }
/*    */   public String getContent() {
/* 53 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 56 */     this.content = content;
/*    */   }
/*    */   public Integer getStatus() {
/* 59 */     return this.status;
/*    */   }
/*    */   public void setStatus(Integer status) {
/* 62 */     this.status = status;
/*    */   }
/*    */   public String getStartDate() {
/* 65 */     return this.startDate;
/*    */   }
/*    */   public void setStartDate(String startDate) {
/* 68 */     this.startDate = startDate;
/*    */   }
/*    */   public String getAddDate() {
/* 71 */     return this.addDate;
/*    */   }
/*    */   public void setAddDate(String addDate) {
/* 74 */     this.addDate = addDate;
/*    */   }
/*    */   public Integer getSort() {
/* 77 */     return this.sort;
/*    */   }
/*    */   public void setSort(Integer sort) {
/* 80 */     this.sort = sort;
/*    */   }
/*    */   public String getAuthor() {
/* 83 */     return this.author;
/*    */   }
/*    */   public void setAuthor(String author) {
/* 86 */     this.author = author;
/*    */   }
/*    */   public Integer getType() {
/* 89 */     return this.type;
/*    */   }
/*    */   public void setType(Integer type) {
/* 92 */     this.type = type;
/*    */   }
/*    */   public String getAbstracts() {
/* 95 */     return this.abstracts;
/*    */   }
/*    */   public void setAbstracts(String abstracts) {
/* 98 */     this.abstracts = abstracts;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Advert.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */