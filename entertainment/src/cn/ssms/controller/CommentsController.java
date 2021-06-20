/*    */ package cn.ssms.controller;
/*    */ 
/*    */ import cn.ssms.dao.ConfigureMapper;
/*    */ import cn.ssms.model.Configure;
/*    */ import cn.ssms.realm.ShiroDbRealm;
/*    */ import cn.ssms.service.CommentsService;
/*    */ import cn.ssms.util.GetRequestParam;
/*    */ import com.google.gson.Gson;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
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
/*    */ @Controller
/*    */ @RequestMapping({"/comment"})
/*    */ public class CommentsController
/*    */ {
/*    */   @Autowired
/*    */   CommentsService commentsService;
/*    */   @Autowired
/*    */   private ConfigureMapper configureMapper;
/* 33 */   private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
/*    */   
/*    */   @RequestMapping({"/commentsList"})
/*    */   public String commentsList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 37 */     logger.debug("来自IP[" + request.getRemoteHost() + "]的访问 ");
/* 38 */     return "comments/commentsList";
/*    */   }
/*    */   
/*    */   @RequestMapping({"commentsReply"})
/*    */   public String topicReply(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 43 */     return "comments/commentsReply";
/*    */   }
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/getCommentsClassify"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String getCommentsClassify(HttpServletRequest request, HttpServletResponse response) {
/* 50 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 51 */     return this.commentsService.getCommentsClassify(map);
/*    */   }
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/getComments"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String getComments(HttpServletRequest request, HttpServletResponse response) {
/* 58 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 59 */     return this.commentsService.getComments(map);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/deleteComments"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String deleteComments(HttpServletRequest request, HttpServletResponse response) {
/* 67 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 68 */     return this.commentsService.deleteComments(map);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/getClassify"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String getSection(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 77 */     List<Configure> getClassify = this.configureMapper.getClassifyOfInfor();
/*    */     
/* 79 */     Configure configure = new Configure();
/* 80 */     configure.setId(999);
/* 81 */     configure.setName("影视管理");
/* 82 */     getClassify.add(configure);
/*    */     
/* 84 */     configure = new Configure();
/* 85 */     configure.setId(9999);
/* 86 */     configure.setName("音乐管理");
/* 87 */     getClassify.add(configure);
/*    */     
/* 89 */     configure = new Configure();
/* 90 */     configure.setId(99999);
/* 91 */     configure.setName("书籍管理");
/* 92 */     getClassify.add(configure);
/*    */     
/* 94 */     return (new Gson()).toJson(getClassify);
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\CommentsController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */