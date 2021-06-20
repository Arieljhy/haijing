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
/*     */ public class Menu
/*     */ {
/*     */   private Integer id;
/*     */   private String menuName;
/*     */   private Integer parentMenuId;
/*     */   private String url;
/*     */   private String iconcls;
/*     */   private Integer privilegeId;
/*     */   private Integer menuState;
/*     */   private Date addDate;
/*     */   private String sonMenu;
/*     */   String authorList;
/*     */   
/*     */   public Integer getId() {
/*  43 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/*  46 */     this.id = id;
/*     */   }
/*     */   public String getMenuName() {
/*  49 */     return this.menuName;
/*     */   }
/*     */   public void setMenuName(String menuName) {
/*  52 */     this.menuName = menuName;
/*     */   }
/*     */   public Integer getParentMenuId() {
/*  55 */     return this.parentMenuId;
/*     */   }
/*     */   public void setParentMenuId(Integer parentMenuId) {
/*  58 */     this.parentMenuId = parentMenuId;
/*     */   }
/*     */   public String getUrl() {
/*  61 */     return this.url;
/*     */   }
/*     */   public void setUrl(String url) {
/*  64 */     this.url = url;
/*     */   }
/*     */   public String getIconcls() {
/*  67 */     return this.iconcls;
/*     */   }
/*     */   public void setIconcls(String iconcls) {
/*  70 */     this.iconcls = iconcls;
/*     */   }
/*     */   public Integer getPrivilegeId() {
/*  73 */     return this.privilegeId;
/*     */   }
/*     */   public void setPrivilegeId(Integer privilegeId) {
/*  76 */     this.privilegeId = privilegeId;
/*     */   }
/*     */   public Integer getMenuState() {
/*  79 */     return this.menuState;
/*     */   }
/*     */   public void setMenuState(Integer menuState) {
/*  82 */     this.menuState = menuState;
/*     */   }
/*     */   public Date getAddDate() {
/*  85 */     return this.addDate;
/*     */   }
/*     */   public void setAddDate(Date addDate) {
/*  88 */     this.addDate = addDate;
/*     */   }
/*     */   public String getSonMenu() {
/*  91 */     return this.sonMenu;
/*     */   }
/*     */   public void setSonMenu(String sonMenu) {
/*  94 */     this.sonMenu = sonMenu;
/*     */   }
/*     */   public String getAuthorList() {
/*  97 */     return this.authorList;
/*     */   }
/*     */   public void setAuthorList(String authorList) {
/* 100 */     this.authorList = authorList;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Menu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */