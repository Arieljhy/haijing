/*     */ package cn.ssms.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestTitle
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private Integer mainId;
/*     */   private String title;
/*     */   private String remark;
/*     */   private Integer sequence;
/*     */   private String trueAnser;
/*     */   private Integer type;
/*     */   private Integer status;
/*     */   private String addDate;
/*     */   private List<Anser> anserList;
/*     */   
/*     */   public Integer getId() {
/*  38 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  42 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getMainId() {
/*  46 */     return this.mainId;
/*     */   }
/*     */   
/*     */   public void setMainId(Integer mainId) {
/*  50 */     this.mainId = mainId;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  54 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  58 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  62 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  66 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getSequence() {
/*  71 */     return this.sequence;
/*     */   }
/*     */   
/*     */   public void setSequence(Integer sequence) {
/*  75 */     this.sequence = sequence;
/*     */   }
/*     */   
/*     */   public String getTrueAnser() {
/*  79 */     return this.trueAnser;
/*     */   }
/*     */   
/*     */   public void setTrueAnser(String trueAnser) {
/*  83 */     this.trueAnser = trueAnser;
/*     */   }
/*     */   
/*     */   public Integer getType() {
/*  87 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/*  91 */     this.type = type;
/*     */   }
/*     */   
/*     */   public List<Anser> getAnserList() {
/*  95 */     return this.anserList;
/*     */   }
/*     */   
/*     */   public void setAnserList(List<Anser> anserList) {
/*  99 */     this.anserList = anserList;
/*     */   }
/*     */   
/*     */   public Integer getStatus() {
/* 103 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/* 107 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getAddDate() {
/* 111 */     return this.addDate;
/*     */   }
/*     */   
/*     */   public void setAddDate(String addDate) {
/* 115 */     this.addDate = addDate;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\TestTitle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */