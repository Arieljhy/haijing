/*    */ package cn.ssms.util;
/*    */ 
/*    */ import cn.ssms.model.Menu;
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
/*    */ public class MenuHelper
/*    */ {
/*    */   public String menuHTML(List<Menu> menus, String name) {
/* 22 */     StringBuffer displayInfo = new StringBuffer();
/* 23 */     displayInfo.append("<ul class='sidebar-menu' id='nav-accordion'>");
/*    */ 
/*    */     
/* 26 */     displayInfo.append("<li class='mt'>");
/* 27 */     displayInfo.append("<a class='active' href='home.html' onclick='javascript:haschild=0;child_title00=&apos;首页&apos;;'  target='h_content'>");
/* 28 */     displayInfo.append("<i class='fa'></i>");
/* 29 */     displayInfo.append("<span>首页</span>");
/* 30 */     displayInfo.append("</a></li>");
/* 31 */     for (int i = 0; i < menus.size(); i++) {
/* 32 */       if (((Menu)menus.get(i)).getParentMenuId().intValue() == 0 && ((Menu)menus.get(i)).getUrl() != null && !"".equals(((Menu)menus.get(i)).getUrl()) && "#".equals(((Menu)menus.get(i)).getUrl())) {
/*    */         
/* 34 */         displayInfo.append("<li class='sub-menu'>");
/* 35 */         displayInfo.append("<a href=javascript:;>");
/* 36 */         displayInfo.append("<i class='" + ((Menu)menus.get(i)).getIconcls() + "'></i>");
/* 37 */         displayInfo.append("<span>" + ((Menu)menus.get(i)).getMenuName() + "</span>");
/* 38 */         displayInfo.append("</a>");
/* 39 */         displayInfo.append("<ul class='sub'>");
/*    */         
/* 41 */         for (int j = 0; j < menus.size(); j++) {
/* 42 */           if (((Menu)menus.get(j)).getParentMenuId() == ((Menu)menus.get(i)).getId()) {
/* 43 */             displayInfo.append("<li><a  href='" + ((Menu)menus.get(j)).getUrl() + "' onclick='javascript:haschild=1;child_title=&apos;" + ((Menu)menus.get(j)).getMenuName() + "&apos;;child_title00=&apos;" + ((Menu)menus.get(i)).getMenuName() + "&apos;;' target='h_content'>" + ((Menu)menus.get(j)).getMenuName() + "</a></li>");
/*    */           }
/*    */         } 
/* 46 */         displayInfo.append("</ul></li>");
/* 47 */       } else if (((Menu)menus.get(i)).getParentMenuId().intValue() == 0 && ((Menu)menus.get(i)).getUrl() != null && !"".equals(((Menu)menus.get(i)).getUrl()) && !"#".equals(((Menu)menus.get(i)).getUrl())) {
/* 48 */         displayInfo.append("<li class='sub-menu'>");
/* 49 */         displayInfo.append("<a href='" + ((Menu)menus.get(i)).getUrl() + "' onclick='javascript:haschild=0;child_title00=&apos;" + ((Menu)menus.get(i)).getMenuName() + "&apos;;' target='h_content'>");
/* 50 */         displayInfo.append("<i class='" + ((Menu)menus.get(i)).getIconcls() + "'></i>");
/* 51 */         displayInfo.append("<span>" + ((Menu)menus.get(i)).getMenuName() + "</span>");
/* 52 */         displayInfo.append("</a></li>");
/*    */       } 
/*    */     } 
/* 55 */     displayInfo.append("</ul>");
/* 56 */     return displayInfo.toString();
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\MenuHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */