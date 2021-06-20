/*    */ package cn.ssms.model;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class Role
/*    */ {
/*    */   private Integer id;
/*    */   private String roleName;
/*    */   private Integer state;
/*    */   private String stateName;
/*    */   private String addDate;
/*    */   private String delDate;
/*    */   private List<Menu> menus;
/*    */   
/*    */   public Integer getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 39 */     this.id = id;
/*    */   }
/*    */   public String getRoleName() {
/* 42 */     return this.roleName;
/*    */   }
/*    */   public void setRoleName(String roleName) {
/* 45 */     this.roleName = roleName;
/*    */   }
/*    */   public Integer getState() {
/* 48 */     return this.state;
/*    */   }
/*    */   public void setState(Integer state) {
/* 51 */     this.state = state;
/*    */   }
/*    */   public String getAddDate() {
/* 54 */     return this.addDate;
/*    */   }
/*    */   public void setAddDate(String addDate) {
/* 57 */     this.addDate = addDate;
/*    */   }
/*    */   public String getDelDate() {
/* 60 */     return this.delDate;
/*    */   }
/*    */   public void setDelDate(String delDate) {
/* 63 */     this.delDate = delDate;
/*    */   }
/*    */   public List<Menu> getMenus() {
/* 66 */     return this.menus;
/*    */   }
/*    */   public void setMenus(List<Menu> menus) {
/* 69 */     this.menus = menus;
/*    */   }
/*    */   public String getStateName() {
/* 72 */     return this.stateName;
/*    */   }
/*    */   public void setStateName(String stateName) {
/* 75 */     this.stateName = stateName;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Role.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */