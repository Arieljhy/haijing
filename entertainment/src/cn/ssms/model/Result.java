/*     */ package cn.ssms.model;
/*     */ 
/*     */ import cn.afterturn.easypoi.excel.annotation.Excel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Result
/*     */ {
/*     */   private Integer id;
/*     */   private Integer userId;
/*     */   private Integer testId;
/*     */   @Excel(name = "正确题数", width = 20.0D, orderNum = "2")
/*     */   private Integer trueCount;
/*     */   @Excel(name = "错误题数", width = 20.0D, orderNum = "3")
/*     */   private Integer errorCount;
/*     */   private String resultAnalyse;
/*     */   @Excel(name = "用户名", width = 20.0D, orderNum = "1")
/*     */   private String userName;
/*     */   private String title;
/*     */   @Excel(name = "总题数", width = 20.0D, orderNum = "4")
/*     */   private Integer totalCount;
/*     */   @Excel(name = "总分", width = 20.0D, orderNum = "5")
/*     */   private Integer score;
/*     */   private Integer status;
/*     */   private String addDate;
/*     */   
/*     */   public String getTitle() {
/*  30 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  34 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getUserName() {
/*  38 */     return this.userName;
/*     */   }
/*     */   
/*     */   public void setUserName(String userName) {
/*  42 */     this.userName = userName;
/*     */   }
/*     */   
/*     */   public String getResultAnalyse() {
/*  46 */     return this.resultAnalyse;
/*     */   }
/*     */   
/*     */   public void setResultAnalyse(String resultAnalyse) {
/*  50 */     this.resultAnalyse = resultAnalyse;
/*     */   }
/*     */   
/*     */   public Integer getErrorCount() {
/*  54 */     return this.errorCount;
/*     */   }
/*     */   
/*     */   public void setErrorCount(Integer errorCount) {
/*  58 */     this.errorCount = errorCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getTrueCount() {
/*  65 */     return this.trueCount;
/*     */   }
/*     */   
/*     */   public void setTrueCount(Integer trueCount) {
/*  69 */     this.trueCount = trueCount;
/*     */   }
/*     */   
/*     */   public Integer getTotalCount() {
/*  73 */     return this.totalCount;
/*     */   }
/*     */   
/*     */   public void setTotalCount(Integer totalCount) {
/*  77 */     this.totalCount = totalCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  88 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  92 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getUserId() {
/*  96 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(Integer userId) {
/* 100 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public Integer getTestId() {
/* 104 */     return this.testId;
/*     */   }
/*     */   
/*     */   public void setTestId(Integer testId) {
/* 108 */     this.testId = testId;
/*     */   }
/*     */   
/*     */   public Integer getScore() {
/* 112 */     return this.score;
/*     */   }
/*     */   
/*     */   public void setScore(Integer score) {
/* 116 */     this.score = score;
/*     */   }
/*     */   
/*     */   public Integer getStatus() {
/* 120 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/* 124 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getAddDate() {
/* 128 */     return this.addDate;
/*     */   }
/*     */   
/*     */   public void setAddDate(String addDate) {
/* 132 */     this.addDate = addDate;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Result.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */