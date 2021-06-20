/*    */ package cn.ssms.controller;
/*    */ 
/*    */ import cn.ssms.model.Dic;
/*    */ import cn.ssms.service.DicService;
/*    */ import com.google.gson.Gson;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/dic"})
/*    */ public class DicController
/*    */ {
/*    */   @Autowired
/*    */   DicService dicService;
/*    */   
/*    */   @RequestMapping(value = {"/getRoleList"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String getRoleList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 35 */     List<Dic> roleList = this.dicService.getAllRoleList();
/* 36 */     return (new Gson()).toJson(roleList);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\DicController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */