/*     */ package cn.ssms.model;
/*     */ 
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
/*     */ public class UserAnser
/*     */ {
/*     */   private Integer id;
/*     */   private Integer userId;
/*     */   private Integer testId;
/*     */   private Integer titleId;
/*     */   private String title;
/*     */   private Integer sequence;
/*     */   private String type;
/*     */   private String trueAnser;
/*     */   private List<Anser> anserList;
/*     */   private String anserIds;
/*     */   private Integer score;
/*     */   
/*     */   public String getTrueAnser() {
/*  28 */     return this.trueAnser;
/*     */   }
/*     */   
/*     */   public void setTrueAnser(String trueAnser) {
/*  32 */     this.trueAnser = trueAnser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Anser> getAnserList() {
/*  40 */     return this.anserList;
/*     */   }
/*     */   
/*     */   public void setAnserList(List<Anser> anserList) {
/*  44 */     this.anserList = anserList;
/*     */   }
/*     */   
/*     */   public Integer getSequence() {
/*  48 */     return this.sequence;
/*     */   }
/*     */   
/*     */   public void setSequence(Integer sequence) {
/*  52 */     this.sequence = sequence;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  56 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  60 */     this.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getScore() {
/*  68 */     return this.score;
/*     */   }
/*     */   
/*     */   public void setScore(Integer score) {
/*  72 */     this.score = score;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  76 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  80 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getUserId() {
/*  84 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(Integer userId) {
/*  88 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getTestId() {
/*  94 */     return this.testId;
/*     */   }
/*     */   
/*     */   public void setTestId(Integer testId) {
/*  98 */     this.testId = testId;
/*     */   }
/*     */   
/*     */   public Integer getTitleId() {
/* 102 */     return this.titleId;
/*     */   }
/*     */   
/*     */   public void setTitleId(Integer titleId) {
/* 106 */     this.titleId = titleId;
/*     */   }
/*     */   
/*     */   public String getAnserIds() {
/* 110 */     return this.anserIds;
/*     */   }
/*     */   
/*     */   public void setAnserIds(String anserIds) {
/* 114 */     this.anserIds = anserIds;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\UserAnser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */