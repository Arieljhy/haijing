/*    */ package cn.ssms.controller;
/*    */ 
/*    */ import cn.ssms.util.tokenUtil.StringUtil;
/*    */ import com.google.gson.Gson;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ import org.springframework.web.multipart.commons.CommonsMultipartFile;
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
/*    */ @RequestMapping({"/file"})
/*    */ public class FileControler
/*    */ {
/*    */   @RequestMapping(value = {"/upload"}, produces = {"application/json;charset=UTF-8"})
/*    */   @ResponseBody
/*    */   public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) {
/* 35 */     Map<String, Object> resultMap = new HashMap<>();
/* 36 */     long time = System.currentTimeMillis();
/* 37 */     String originalFilename = file.getOriginalFilename();
/* 38 */     String basePath = request.getSession().getServletContext().getRealPath("/");
/*    */     
/* 40 */     String path1 = "picture/icon/" + String.valueOf(time) + originalFilename.substring(originalFilename.lastIndexOf("."));
/* 41 */     String path = basePath + path1;
/* 42 */     File newFile = new File(path);
/* 43 */     Gson gosn = new Gson();
/*    */     try {
/* 45 */       file.transferTo(newFile);
/* 46 */     } catch (IllegalStateException e) {
/* 47 */       e.printStackTrace();
/* 48 */       resultMap.put("flag", Boolean.valueOf(false));
/* 49 */       resultMap.put("msg", "上传失败！");
/* 50 */       return gosn.toJson(resultMap);
/* 51 */     } catch (IOException e) {
/* 52 */       e.printStackTrace();
/* 53 */       resultMap.put("flag", Boolean.valueOf(false));
/* 54 */       resultMap.put("msg", "上传失败！");
/* 55 */       return gosn.toJson(resultMap);
/*    */     } 
/* 57 */     resultMap.put("flag", Boolean.valueOf(true));
/* 58 */     resultMap.put("msg", "上传成功！");
/* 59 */     resultMap.put("data", StringUtil.readProperties("file.uploadUrl") + path1);
/* 60 */     return gosn.toJson(resultMap);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 64 */     String originalFilename = "123.png";
/* 65 */     System.out.println(originalFilename.substring(originalFilename.lastIndexOf(".")));
/*    */   }
/*    */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\FileControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */