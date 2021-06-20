/*     */ package cn.ssms.model;
/*     */ 
/*     */ import java.util.Date;
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
/*     */ public class Live
/*     */ {
/*     */   private Integer id;
/*     */   private String code;
/*     */   private String name;
/*     */   private Date time;
/*     */   private String record;
/*     */   private String person;
/*     */   private String state;
/*     */   private String stateStr;
/*     */   private String timeStr;
/*     */   private Date createTime;
/*     */   private Date modifyTime;
/*     */   
/*     */   public String getStateStr() {
/*  29 */     return this.stateStr;
/*     */   }
/*     */   
/*     */   public void setStateStr(String stateStr) {
/*  33 */     this.stateStr = stateStr;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  37 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  45 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  49 */     this.code = (code == null) ? null : code.trim();
/*     */   }
/*     */   
/*     */   public String getName() {
/*  53 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  57 */     this.name = (name == null) ? null : name.trim();
/*     */   }
/*     */   
/*     */   public Date getTime() {
/*  61 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(Date time) {
/*  65 */     this.time = time;
/*     */   }
/*     */   
/*     */   public String getRecord() {
/*  69 */     return this.record;
/*     */   }
/*     */   
/*     */   public void setRecord(String record) {
/*  73 */     this.record = (record == null) ? null : record.trim();
/*     */   }
/*     */   
/*     */   public String getPerson() {
/*  77 */     return this.person;
/*     */   }
/*     */   
/*     */   public void setPerson(String person) {
/*  81 */     this.person = (person == null) ? null : person.trim();
/*     */   }
/*     */   
/*     */   public String getState() {
/*  85 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(String state) {
/*  89 */     this.state = (state == null) ? null : state.trim();
/*     */   }
/*     */   
/*     */   public Date getCreateTime() {
/*  93 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/*  97 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */   public Date getModifyTime() {
/* 101 */     return this.modifyTime;
/*     */   }
/*     */   
/*     */   public void setModifyTime(Date modifyTime) {
/* 105 */     this.modifyTime = modifyTime;
/*     */   }
/*     */   
/*     */   public String getTimeStr() {
/* 109 */     return this.timeStr;
/*     */   }
/*     */   
/*     */   public void setTimeStr(String timeStr) {
/* 113 */     this.timeStr = timeStr;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Live.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */