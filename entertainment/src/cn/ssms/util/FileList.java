/*    */ package cn.ssms.util;
/*    */ 
/*    */ import cn.ssms.model.Resources;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileList
/*    */ {
/*    */   public List<String> getFolders(String dir_name) throws Exception {
/* 18 */     Vector<String> ver = new Vector<>();
/* 19 */     List<String> folders_name = new ArrayList<>();
/* 20 */     ver.add(dir_name);
/* 21 */     while (ver.size() > 0) {
/* 22 */       File[] files = (new File(((String)ver.get(0)).toString())).listFiles();
/* 23 */       ver.remove(0);
/* 24 */       int len = files.length;
/* 25 */       for (int i = 0; i < len; i++) {
/* 26 */         if (files[i].isDirectory()) {
/* 27 */           folders_name.add(files[i].getName());
/*    */         }
/*    */       } 
/*    */     } 
/* 31 */     return folders_name;
/*    */   }
/*    */   
/*    */   public List<Resources> getList(String dir_name, String url) throws Exception {
/* 35 */     Vector<String> ver = new Vector<>();
/* 36 */     List<Resources> list_name = new ArrayList<>();
/* 37 */     ver.add(dir_name);
/* 38 */     while (ver.size() > 0) {
/* 39 */       File[] files = (new File(((String)ver.get(0)).toString())).listFiles();
/* 40 */       ver.remove(0);
/* 41 */       int len = files.length;
/* 42 */       for (int i = 0; i < len; i++) {
/* 43 */         String tmp = files[i].getAbsolutePath();
/* 44 */         if (files[i].isDirectory()) {
/* 45 */           ver.add(tmp);
/*    */         } else {
/* 47 */           Resources resources = new Resources();
/* 48 */           resources.setUrl(url + files[i].getName());
/* 49 */           System.out.println(url + "=====================" + resources.getUrl());
/* 50 */           list_name.add(resources);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return list_name;
/*    */   }
/*    */   
/*    */   public List<Resources> getListName(String name, String dir_name, String url) throws Exception {
/* 59 */     Vector<String> ver = new Vector<>();
/* 60 */     List<Resources> list_name = new ArrayList<>();
/* 61 */     ver.add(dir_name);
/* 62 */     while (ver.size() > 0) {
/* 63 */       File[] files = (new File(((String)ver.get(0)).toString())).listFiles();
/* 64 */       ver.remove(0);
/* 65 */       int len = files.length;
/* 66 */       for (int i = 0; i < len; i++) {
/* 67 */         String tmp = files[i].getAbsolutePath();
/* 68 */         if (files[i].isDirectory()) {
/* 69 */           ver.add(tmp);
/*    */         }
/* 71 */         else if (files[i].getName().startsWith(name)) {
/* 72 */           Resources resources = new Resources();
/* 73 */           resources.setUrl(url + files[i].getName());
/* 74 */           list_name.add(resources);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 80 */     return list_name;
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\FileList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */