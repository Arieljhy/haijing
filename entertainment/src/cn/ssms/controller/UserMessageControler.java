/*    */ package cn.ssms.controller;
/*    */ 
/*    */ import cn.ssms.service.UserMessageService;
/*    */ import cn.ssms.util.GetRequestParam;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/userMessage"})
/*    */ public class UserMessageControler
/*    */ {
/*    */   @Autowired
/*    */   private UserMessageService userMessageService;
/*    */   
/*    */   @RequestMapping({"/toUserMessageList"})
/*    */   public String toUserMessageList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 31 */     return "userMessage/messageList";
/*    */   }
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/userMessageList"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String userMessageList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 38 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*    */     
/*    */     try {
/* 41 */       return this.userMessageService.getlist(map);
/* 42 */     } catch (Exception e) {
/*    */       
/* 44 */       e.printStackTrace();
/* 45 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\UserMessageControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */