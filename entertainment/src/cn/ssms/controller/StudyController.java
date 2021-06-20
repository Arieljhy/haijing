/*     */ package cn.ssms.controller;
/*     */ import cn.afterturn.easypoi.excel.ExcelExportUtil;
/*     */ import cn.afterturn.easypoi.excel.entity.ExportParams;
/*     */ import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.ssms.dao.MainTestMapper;
/*     */ import cn.ssms.dao.TestTitleMapper;
/*     */ import cn.ssms.model.MainTest;
/*     */ import cn.ssms.model.Result;
/*     */ import cn.ssms.model.TestTitle;
/*     */ import cn.ssms.service.StudyService;
/*     */ import cn.ssms.util.DataAccessException;
/*     */ import cn.ssms.util.ExcelStyleUtil;
/*     */ import cn.ssms.util.GetRequestParam;
/*     */ import cn.ssms.util.ImportWord;
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.poi.ss.usermodel.Workbook;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.*;
/*     */
/*     */
/*     */
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/study"})
/*     */ public class StudyController {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(StudyController.class); @Autowired
/*     */   private StudyService studyService; @Autowired
/*     */   private MainTestMapper testMapper; @Autowired
/*     */   private TestTitleMapper testTitleMapper; @RequestMapping({"/contentList"})
/*     */   public String contentList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  48 */     return "study/contentList";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getContentList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String classfiyList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  55 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  56 */     return this.studyService.getContentList(map);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/contentInfo"})
/*     */   public String contentInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  63 */     return "study/contentInfo";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/findContentOne"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String findContentOne(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  70 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  71 */     return this.studyService.findContentOne(map);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/addContent"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addContent(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  77 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  78 */     return this.studyService.addContent(map);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updContent"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updContent(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  84 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  85 */     return this.studyService.updContent(map);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/removeContent"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeContent(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  91 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  92 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     try {
/*  94 */       return this.studyService.removeContent(map);
/*  95 */     } catch (DataAccessException e) {
/*  96 */       resultMap.put("flag", Boolean.valueOf(false));
/*  97 */       resultMap.put("msg", "删除失败!");
/*  98 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping({"/testList"})
/*     */   public String testList(HttpServletRequest request, HttpServletResponse response, Model model, String type, @ModelAttribute("importFlag") String importFlag) {
/* 105 */     request.setAttribute("type", type);
/* 106 */     request.setAttribute("importFlag", importFlag);
/* 107 */     System.out.println("importFlag:" + importFlag);
/* 108 */     return "study/testList";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getTestList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getTestList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 114 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 115 */     return this.studyService.getTestList(map);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/titleList"})
/*     */   public String titleList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 122 */     return "study/titleList";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateTitle"}, method = {RequestMethod.GET})
/*     */   public String updateTitle(HttpServletRequest request, HttpServletResponse response, Model model, String type) {
/* 128 */     request.setAttribute("type", type);
/* 129 */     return "study/updateTitle";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/addTitle"})
/*     */   @ResponseBody
/*     */   public String addTitle(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
/* 137 */     String str = IOUtils.toString((InputStream)request.getInputStream(), "utf-8");
/* 138 */     log.info(str);
/* 139 */     TestTitle testTitle = (TestTitle)JSON.parseObject(str, TestTitle.class);
/* 140 */     this.studyService.addTitle(testTitle);
/*     */ 
/*     */ 
/*     */     
/* 144 */     Map<String, Object> map = new HashMap<>();
/* 145 */     map.put("code", Integer.valueOf(1));
/* 146 */     map.put("data", testTitle);
/* 147 */     return (new Gson()).toJson(map);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getTitleList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getTitleList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 155 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     try {
/* 157 */       return this.studyService.getTitleList(map);
/* 158 */     } catch (DataAccessException e) {
/* 159 */       Map<String, Object> resultMap = new HashMap<>();
/* 160 */       resultMap.put("flag", Boolean.valueOf(false));
/* 161 */       resultMap.put("msg", "删除失败!");
/* 162 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getMainTestDetail"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getMainTestDetail(HttpServletRequest request, HttpServletResponse response, Model model, Integer id) {
/* 173 */     MainTest mainTest = this.testMapper.findTestOne(id);
/* 174 */     return (new Gson()).toJson(mainTest);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/exportExcel"})
/*     */   public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
/* 184 */     Map<String, Object> params = new HashMap<>();
/* 185 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 186 */     String idStr = (String)map.get("id");
/*     */     
/* 188 */     Integer id = Integer.valueOf(idStr);
/* 189 */     params.put("mainId", id);
/* 190 */     Gson gson = new Gson();
/* 191 */     MainTest testOne = this.testMapper.findTestOne(id);
/* 192 */     String testName = testOne.getTitle();
/* 193 */     List<Result> resultList = this.testTitleMapper.getReplyList(params);
/* 194 */     ExportParams exportParams = new ExportParams(testName + "答题情况", "答题情况");
/* 195 */     exportParams.setStyle(ExcelStyleUtil.class);
/* 196 */     exportParams.setType(ExcelType.XSSF);
/* 197 */     Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Result.class, resultList);
/* 198 */     String fileName = testName + "答题情况.xlsx";
/*     */     try {
/* 200 */       response.setCharacterEncoding("UTF-8");
/* 201 */       response.setHeader("content-Type", "application/vnd.ms-excel");
/* 202 */       response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
/* 203 */       workbook.write((OutputStream)response.getOutputStream());
/* 204 */     } catch (IOException e) {
/* 205 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/replyList"})
/*     */   public String replyList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 214 */     return "study/replyList";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getReplyList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getReplyList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 220 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 221 */     return this.studyService.getReplyList(map);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping({"/userAnserList"})
/*     */   public String userAnserList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 227 */     return "study/userAnserList";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getUserAnserList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getUserAnserList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 233 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 234 */     return this.studyService.getUserAnserList(map);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping({"/anserDetail"})
/*     */   public String anserDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 240 */     return "study/anserDetail";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getAnserDetail"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getAnserDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 246 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     try {
/* 248 */       return this.studyService.getResultDetail(map);
/* 249 */     } catch (DataAccessException e) {
/* 250 */       Map<String, Object> resultMap = new HashMap<>();
/* 251 */       resultMap.put("flag", Boolean.valueOf(false));
/* 252 */       resultMap.put("msg", "删除失败!");
/* 253 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/removeTest"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeTest(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 261 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 262 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     try {
/* 264 */       return this.studyService.removeTest(map);
/* 265 */     } catch (DataAccessException e) {
/* 266 */       resultMap.put("flag", Boolean.valueOf(false));
/* 267 */       resultMap.put("msg", "删除失败!");
/* 268 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/uploadWord"})
/*     */   public String uploadWordByTeacher(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request, Integer type, RedirectAttributes attr) {
/*     */     try {
/* 283 */       Map<String, List<String>> result = ImportWord.getWordAndStyle(file.getInputStream());
/* 284 */       List<String> titles = result.get("titles");
/* 285 */       List<String> answers = result.get("answers");
/* 286 */       if (result.isEmpty() || null == titles || null == answers || titles.size() <= 0 || answers.size() <= 0) {
/* 287 */         attr.addFlashAttribute("importFlag", "导入失败,导入的题目和答案不能为空");
/* 288 */         return "redirect:testList.html?type=" + type;
/* 289 */       }  if (titles.size() == 4 || answers.size() == 4) {
/* 290 */         attr.addFlashAttribute("importFlag", "导入失败,导入的题目和答案不能为空");
/* 291 */         return "redirect:testList.html?type=" + type;
/*     */       } 
/* 293 */       this.studyService.importWord(titles, answers, type);
/* 294 */       attr.addFlashAttribute("importFlag", "导入成功");
/* 295 */     } catch (RuntimeException e) {
/* 296 */       log.error("导入试卷失败", e);
/* 297 */       attr.addFlashAttribute("importFlag", e.getMessage());
/* 298 */     } catch (Exception e) {
/* 299 */       log.error("导入试卷失败", e);
/* 300 */       attr.addFlashAttribute("importFlag", "导入失败");
/*     */     } 
/*     */     
/* 303 */     return "redirect:testList.html?type=" + type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/addWord"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Object addWord(Model model, HttpServletRequest request) throws IOException {
/* 316 */     String str = IOUtils.toString((InputStream)request.getInputStream(), "utf-8");
/* 317 */     log.info(str);
/* 318 */     Params params = (Params)JSON.parseObject(str, Params.class);
/* 319 */     log.info(JSON.toJSONString(params));
/* 320 */     this.studyService.addWord(params.getMainTest(), params.getTestTitles());
/* 321 */     Map<String, Object> map = new HashMap<>();
/* 322 */     map.put("code", Integer.valueOf(1));
/* 323 */     return (new Gson()).toJson(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateTitle"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Object updateTitle(Model model, HttpServletRequest request) throws IOException {
/* 335 */     String str = IOUtils.toString((InputStream)request.getInputStream(), "utf-8");
/* 336 */     log.info(str);
/* 337 */     TestTitle testTitle = (TestTitle)JSON.parseObject(str, TestTitle.class);
/* 338 */     this.studyService.updateTitle(testTitle);
/*     */ 
/*     */ 
/*     */     
/* 342 */     Map<String, Object> map = new HashMap<>();
/* 343 */     map.put("code", Integer.valueOf(1));
/* 344 */     return (new Gson()).toJson(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/delTitle"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Object delTitle(Model model, HttpServletRequest request) throws IOException {
/* 357 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 358 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     try {
/* 360 */       return this.studyService.removeTitle(map);
/* 361 */     } catch (DataAccessException e) {
/* 362 */       resultMap.put("flag", Boolean.valueOf(false));
/* 363 */       resultMap.put("msg", "删除失败!");
/* 364 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateWord"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Object updateWord(Model model, HttpServletRequest request) throws IOException {
/* 379 */     String str = IOUtils.toString((InputStream)request.getInputStream(), "utf-8");
/* 380 */     log.info(str);
/* 381 */     MainTest mainTest = (MainTest)JSON.parseObject(str, MainTest.class);
/* 382 */     this.studyService.updateWord(mainTest);
/*     */ 
/*     */ 
/*     */     
/* 386 */     Map<String, Object> map = new HashMap<>();
/* 387 */     map.put("code", Integer.valueOf(1));
/* 388 */     return (new Gson()).toJson(map);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping({"/toTestInfo"})
/*     */   public String toClassfiyInfo(HttpServletRequest request, HttpServletResponse response, Model model, String type) {
/* 394 */     request.setAttribute("type", type);
/* 395 */     return "study/testInfo";
/*     */   }
/*     */   
/*     */   static class Params
/*     */     implements Serializable {
/*     */     private MainTest mainTest;
/*     */     private List<TestTitle> testTitles;
/*     */     
/*     */     public MainTest getMainTest() {
/* 404 */       return this.mainTest;
/*     */     }
/*     */     
/*     */     public void setMainTest(MainTest mainTest) {
/* 408 */       this.mainTest = mainTest;
/*     */     }
/*     */     
/*     */     public List<TestTitle> getTestTitles() {
/* 412 */       return this.testTitles;
/*     */     }
/*     */     
/*     */     public void setTestTitles(List<TestTitle> testTitles) {
/* 416 */       this.testTitles = testTitles;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\StudyController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */