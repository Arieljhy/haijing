/*    */ package cn.ssms.ueditor.define;
/*    */ 
/*    */ import com.baidu.ueditor.Encoder;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BaseState
/*    */   implements State
/*    */ {
/*    */   private boolean state = false;
/* 12 */   private String info = null;
/*    */   
/* 14 */   private Map<String, String> infoMap = new HashMap<>();
/*    */   
/*    */   public BaseState() {
/* 17 */     this.state = true;
/*    */   }
/*    */   
/*    */   public BaseState(boolean state) {
/* 21 */     setState(state);
/*    */   }
/*    */   
/*    */   public BaseState(boolean state, String info) {
/* 25 */     setState(state);
/* 26 */     this.info = info;
/*    */   }
/*    */   
/*    */   public BaseState(boolean state, int infoCode) {
/* 30 */     setState(state);
/* 31 */     this.info = AppInfo.getStateInfo(infoCode);
/*    */   }
/*    */   
/*    */   public boolean isSuccess() {
/* 35 */     return this.state;
/*    */   }
/*    */   
/*    */   public void setState(boolean state) {
/* 39 */     this.state = state;
/*    */   }
/*    */   
/*    */   public void setInfo(String info) {
/* 43 */     this.info = info;
/*    */   }
/*    */   
/*    */   public void setInfo(int infoCode) {
/* 47 */     this.info = AppInfo.getStateInfo(infoCode);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toJSONString() {
/* 52 */     return toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String key = null;
/* 58 */     String stateVal = isSuccess() ? AppInfo.getStateInfo(0) : this.info;
/*    */     
/* 60 */     StringBuilder builder = new StringBuilder();
/*    */     
/* 62 */     builder.append("{\"state\": \"" + stateVal + "\"");
/*    */     
/* 64 */     Iterator<String> iterator = this.infoMap.keySet().iterator();
/*    */     
/* 66 */     while (iterator.hasNext()) {
/*    */       
/* 68 */       key = iterator.next();
/*    */       
/* 70 */       builder.append(",\"" + key + "\": \"" + (String)this.infoMap.get(key) + "\"");
/*    */     } 
/*    */ 
/*    */     
/* 74 */     builder.append("}");
/*    */     
/* 76 */     return Encoder.toUnicode(builder.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void putInfo(String name, String val) {
/* 82 */     this.infoMap.put(name, val);
/*    */   }
/*    */ 
/*    */   
/*    */   public void putInfo(String name, long val) {
/* 87 */     putInfo(name, val + "");
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\define\BaseState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */