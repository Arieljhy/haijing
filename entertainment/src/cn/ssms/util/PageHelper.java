/*     */ package cn.ssms.util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageHelper
/*     */ {
/*     */   private Integer total;
/*     */   private Integer pageSize;
/*     */   private Integer index;
/*     */   private String data;
/*     */   private String path;
/*     */   private String title;
/*     */   private String name;
/*     */   
/*     */   public String getTitle() {
/*  34 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  38 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  42 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  46 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Integer getTotal() {
/*  50 */     return this.total;
/*     */   }
/*     */   
/*     */   public void setTotal(Integer total) {
/*  54 */     this.total = total;
/*     */   }
/*     */   
/*     */   public Integer getPageSize() {
/*  58 */     return this.pageSize;
/*     */   }
/*     */   
/*     */   public void setPageSize(Integer pageSize) {
/*  62 */     this.pageSize = pageSize;
/*     */   }
/*     */   
/*     */   public Integer getIndex() {
/*  66 */     return this.index;
/*     */   }
/*     */   
/*     */   public void setIndex(Integer index) {
/*  70 */     this.index = index;
/*     */   }
/*     */   
/*     */   public String getData() {
/*  74 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(String data) {
/*  78 */     this.data = data;
/*     */   }
/*     */   
/*     */   public String getPath() {
/*  82 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/*  86 */     this.path = path;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PageHelper(Integer total, Integer pageSize, Integer index, String data, String path, String title, String name) {
/*  92 */     this.total = total;
/*  93 */     this.pageSize = pageSize;
/*  94 */     this.index = index;
/*  95 */     this.data = data;
/*  96 */     this.path = path;
/*  97 */     this.title = title;
/*  98 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PageHelper() {}
/*     */ 
/*     */   
/*     */   public PageHelper(Integer total, Integer pageSize, Integer index, String data, String path) {
/* 107 */     this.total = total;
/* 108 */     this.pageSize = pageSize;
/* 109 */     this.index = index;
/* 110 */     this.data = data;
/* 111 */     this.path = path;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\PageHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */