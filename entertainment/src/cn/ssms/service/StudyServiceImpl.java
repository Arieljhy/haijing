/*     */ package cn.ssms.service;
/*     */ 
/*     */ import cn.ssms.dao.MainTestMapper;
/*     */ import cn.ssms.dao.TestTitleMapper;
/*     */ import cn.ssms.model.Anser;
/*     */ import cn.ssms.model.MainContent;
/*     */ import cn.ssms.model.MainTest;
/*     */ import cn.ssms.model.Result;
/*     */ import cn.ssms.model.TestTitle;
/*     */ import cn.ssms.model.UserAnser;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.DataAccessException;
/*     */ import cn.ssms.util.PageHelper;
/*     */ import cn.ssms.util.ResultConstant;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.joda.time.DateTime;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("StudyService")
/*     */ public class StudyServiceImpl
/*     */   implements StudyService {
/*     */   public String getContentList(Map<String, Object> map) {
/*     */     try {
/*  32 */       Map<String, Object> params = new HashMap<>();
/*  33 */       String pageStr = (String)map.get("page");
/*  34 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/*  35 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/*  36 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  37 */       params.put("pageSize", pageSize);
/*  38 */       if (map.get("type") != null && !map.get("type").equals("")) {
/*  39 */         params.put("type", map.get("type"));
/*     */       }
/*     */       
/*  42 */       Gson gson = new Gson();
/*  43 */       List<MainContent> contentList = this.mainTestMapper.getContentList(params);
/*  44 */       Integer total = Integer.valueOf(this.mainTestMapper.getContentListTotal(params));
/*  45 */       String path = "getContentList.html?page=";
/*  46 */       PageHelper result = new PageHelper(total, pageSize, page, gson.toJson(contentList), path);
/*  47 */       return gson.toJson(result);
/*  48 */     } catch (Exception e) {
/*  49 */       e.printStackTrace();
/*  50 */       return null;
/*     */     } 
/*     */   }
/*     */   @Autowired
/*     */   private TestTitleMapper testTitleMapper; @Autowired
/*     */   private MainTestMapper mainTestMapper;
/*     */   public String addContent(Map<String, Object> map) {
/*  57 */     String title = (String)map.get("title");
/*  58 */     String content = (String)map.get("content");
/*  59 */     String remark = (String)map.get("remark");
/*  60 */     Integer type = Integer.valueOf((String)map.get("type"));
/*  61 */     MainContent mainContent = new MainContent();
/*  62 */     mainContent.setTitle(title);
/*  63 */     mainContent.setContent(content);
/*  64 */     mainContent.setRemark(remark);
/*  65 */     mainContent.setType(type);
/*  66 */     mainContent.setStatus(Integer.valueOf(1));
/*  67 */     mainContent.setAddDate("1");
/*  68 */     this.mainTestMapper.addContent(mainContent);
/*  69 */     Map<String, Object> result = new HashMap<>();
/*  70 */     result.put("flag", Boolean.valueOf(true));
/*  71 */     result.put("message", "新增成功");
/*  72 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String updContent(Map<String, Object> map) {
/*  77 */     String title = (String)map.get("title");
/*  78 */     String content = (String)map.get("content");
/*  79 */     String remark = (String)map.get("remark");
/*  80 */     Integer type = Integer.valueOf((String)map.get("type"));
/*  81 */     String idStr = (String)map.get("id");
/*  82 */     MainContent mainContent = new MainContent();
/*  83 */     mainContent.setTitle(title);
/*  84 */     mainContent.setContent(content);
/*  85 */     mainContent.setRemark(remark);
/*  86 */     mainContent.setType(type);
/*  87 */     mainContent.setId(Integer.valueOf(idStr));
/*  88 */     this.mainTestMapper.updContentById(mainContent);
/*  89 */     Map<String, Object> result = new HashMap<>();
/*  90 */     result.put("flag", Boolean.valueOf(true));
/*  91 */     result.put("message", "修改成功");
/*  92 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeContent(Map<String, Object> map) throws DataAccessException {
/*  98 */     String idsStr = (String)map.get("ids");
/*  99 */     System.out.println(idsStr);
/* 100 */     String[] ids = idsStr.split(",");
/* 101 */     for (int i = 0; i < ids.length; i++) {
/* 102 */       MainContent mainContent = new MainContent();
/* 103 */       mainContent.setStatus(Integer.valueOf(0));
/* 104 */       mainContent.setId(Integer.valueOf(ids[i]));
/* 105 */       this.mainTestMapper.updContentById(mainContent);
/*     */     } 
/*     */     
/* 108 */     Map<String, Object> result = new HashMap<>();
/* 109 */     result.put("flag", Boolean.valueOf(true));
/* 110 */     result.put("message", "修改成功");
/* 111 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTestList(Map<String, Object> map) {
/*     */     try {
/* 117 */       Map<String, Object> params = new HashMap<>();
/* 118 */       String pageStr = (String)map.get("page");
/* 119 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/* 120 */       Integer pageSize = Integer.valueOf((map.get("pageSize") == null) ? 10 : Integer.valueOf(map.get("pageSize").toString()).intValue());
/* 121 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 122 */       params.put("pageSize", pageSize);
/* 123 */       if (map.get("type") != null && !map.get("type").equals("")) {
/* 124 */         params.put("type", map.get("type"));
/*     */       }
/* 126 */       Gson gson = new Gson();
/* 127 */       List<MainTest> contentList = this.mainTestMapper.getTestList(params);
/* 128 */       Integer total = Integer.valueOf(this.mainTestMapper.getTestListTotal(params));
/* 129 */       String path = "getTestList.html?page=";
/* 130 */       PageHelper result = new PageHelper(total, pageSize, page, gson.toJson(contentList), path);
/* 131 */       return gson.toJson(result);
/* 132 */     } catch (Exception e) {
/* 133 */       e.printStackTrace();
/* 134 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String findTestOne(Map<String, Object> map) {
/* 142 */     String idStr = (String)map.get("id");
/* 143 */     MainTest mainTest = this.mainTestMapper.findTestOne(Integer.valueOf(idStr));
/*     */     
/* 145 */     return (new Gson()).toJson(mainTest);
/*     */   }
/*     */ 
/*     */   
/*     */   public String findContentOne(Map<String, Object> map) {
/* 150 */     String idStr = (String)map.get("id");
/* 151 */     MainContent mainContent = this.mainTestMapper.findContentOne(Integer.valueOf(idStr));
/*     */     
/* 153 */     return (new Gson()).toJson(mainContent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitleList(Map<String, Object> map) throws DataAccessException {
/*     */     try {
/* 163 */       Map<String, Object> params = new HashMap<>();
/* 164 */       String pageStr = (String)map.get("page");
/* 165 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/* 166 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/* 167 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 168 */       params.put("pageSize", pageSize);
/* 169 */       String idStr = (String)map.get("id");
/* 170 */       Integer id = Integer.valueOf(idStr);
/* 171 */       params.put("mainId", id);
/* 172 */       Gson gson = new Gson();
/* 173 */       List<TestTitle> titleList = this.testTitleMapper.getTestTitleList(params);
/* 174 */       Integer total = Integer.valueOf(this.testTitleMapper.getTestTitleListCount(params));
/* 175 */       Integer titleId = Integer.valueOf(0);
/* 176 */       List<Anser> anserList = new ArrayList<>();
/* 177 */       for (TestTitle testTitle : titleList) {
/* 178 */         titleId = testTitle.getId();
/* 179 */         anserList = this.testTitleMapper.getAnserList(titleId);
/* 180 */         testTitle.setAnserList(anserList);
/*     */       } 
/* 182 */       String path = "getTitleList.html?page=";
/* 183 */       PageHelper result = new PageHelper(total, pageSize, page, gson.toJson(titleList), path);
/* 184 */       return gson.toJson(result);
/* 185 */     } catch (NumberFormatException e) {
/*     */       
/* 187 */       e.printStackTrace();
/* 188 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReplyList(Map<String, Object> map) {
/*     */     try {
/* 196 */       Map<String, Object> params = new HashMap<>();
/* 197 */       String pageStr = (String)map.get("page");
/* 198 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/* 199 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/* 200 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 201 */       params.put("pageSize", pageSize);
/* 202 */       String idStr = (String)map.get("id");
/* 203 */       Integer id = Integer.valueOf(idStr);
/* 204 */       params.put("mainId", id);
/* 205 */       Gson gson = new Gson();
/* 206 */       List<Result> resultList = this.testTitleMapper.getReplyList(params);
/* 207 */       Integer total = Integer.valueOf(this.testTitleMapper.getReplyListTotal(params));
/* 208 */       String path = "getReplyList.html?page=";
/* 209 */       PageHelper result = new PageHelper(total, pageSize, page, gson.toJson(resultList), path);
/* 210 */       return gson.toJson(result);
/* 211 */     } catch (NumberFormatException e) {
/*     */       
/* 213 */       e.printStackTrace();
/* 214 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserAnserList(Map<String, Object> map) {
/*     */     try {
/* 222 */       Map<String, Object> params = new HashMap<>();
/* 223 */       String pageStr = (String)map.get("page");
/* 224 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/* 225 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/* 226 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 227 */       params.put("pageSize", pageSize);
/* 228 */       String idStr = (String)map.get("id");
/* 229 */       Integer id = Integer.valueOf(idStr);
/* 230 */       params.put("mainId", id);
/* 231 */       params.put("userId", Integer.valueOf((String)map.get("userId")));
/* 232 */       Gson gson = new Gson();
/* 233 */       List<UserAnser> resultList = this.testTitleMapper.getUserAnserList(params);
/* 234 */       Integer total = Integer.valueOf(this.testTitleMapper.getUserAnserListTotal(params));
/* 235 */       String path = "getUserAnserList.html?page=";
/*     */       
/* 237 */       PageHelper result = new PageHelper(total, pageSize, page, gson.toJson(resultList), path);
/* 238 */       return gson.toJson(result);
/* 239 */     } catch (NumberFormatException e) {
/*     */       
/* 241 */       e.printStackTrace();
/* 242 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResultDetail(Map<String, Object> map) throws DataAccessException {
/* 248 */     Map<String, Object> result = new HashMap<>();
/* 249 */     String testId = (String)map.get("titleId");
/* 250 */     String userId = (String)map.get("userId");
/* 251 */     if (testId.equals("") || userId.equals("")) {
/* 252 */       result.put("code", "error");
/* 253 */       result.put("msg", "参数错误");
/* 254 */       return (new Gson()).toJson(result);
/*     */     } 
/* 256 */     Map<String, Object> params = new HashMap<>();
/* 257 */     params.put("titleId", Integer.valueOf(testId));
/* 258 */     params.put("userId", Integer.valueOf(userId));
/* 259 */     UserAnser userAnser = this.testTitleMapper.getTitleDetail(params);
/* 260 */     List<Anser> anserList = this.testTitleMapper.getAnserList(Integer.valueOf(testId));
/* 261 */     userAnser.setAnserList(anserList);
/* 262 */     return (new Gson()).toJson(userAnser);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeTest(Map<String, Object> map) throws DataAccessException {
/* 268 */     String idsStr = (String)map.get("ids");
/* 269 */     System.out.println(idsStr);
/* 270 */     String[] ids = idsStr.split(",");
/* 271 */     for (int i = 0; i < ids.length; i++) {
/* 272 */       MainTest mainTest = new MainTest();
/* 273 */       mainTest.setStatus(Integer.valueOf(0));
/* 274 */       mainTest.setId(Integer.valueOf(ids[i]));
/* 275 */       this.mainTestMapper.updTestById(mainTest);
/*     */     } 
/* 277 */     Map<String, Object> result = new HashMap<>();
/* 278 */     result.put("flag", Boolean.valueOf(true));
/* 279 */     result.put("message", "修改成功");
/* 280 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String importWord(List<String> titles, List<String> answers, Integer type) {
/* 285 */     MainTest mainTest = new MainTest();
/* 286 */     List<TestTitle> titleList = new ArrayList<>();
/* 287 */     List<Anser> anserList = new ArrayList<>();
/* 288 */     TestTitle title = new TestTitle();
/* 289 */     Anser anser = new Anser();
/* 290 */     int titleCount = 1;
/* 291 */     if (titles.size() == answers.size() && titles.size() > 0 && answers.size() > 0) {
/* 292 */       for (int i = 0; i < titles.size(); i++)
/*     */       {
/* 294 */         if ("标题".equals(titles.get(i))) {
/* 295 */           mainTest.setStatus(Integer.valueOf(1));
/* 296 */           mainTest.setTitle(ResultConstant.replaceTrim(answers.get(i)).replaceAll("(?:<br>|</br>|<br/>)", ""));
/* 297 */         } else if ("内容".equals(titles.get(i))) {
/* 298 */           mainTest.setRemark(ResultConstant.replaceTrim(answers.get(i)).replaceAll("(?:<br>|</br>|<br/>)", ""));
/* 299 */         } else if ("评分标准".equals(titles.get(i))) {
/* 300 */           mainTest.setResultAnalyse(ResultConstant.replaceTrim(answers.get(i)));
/* 301 */         } else if ("类型".equals(titles.get(i))) {
/* 302 */           if ("心理问卷".equals(ResultConstant.replaceTrim(answers.get(i))) && 1 == type.intValue()) {
/* 303 */             mainTest.setType(Integer.valueOf(1));
/* 304 */           } else if ("在线考试".equals(ResultConstant.replaceTrim(answers.get(i))) && 2 == type.intValue()) {
/* 305 */             mainTest.setType(Integer.valueOf(2));
/*     */           } else {
/* 307 */             log.error("导入的试卷类型错误:" + (String)answers.get(i));
/* 308 */             throw new RuntimeException("导入的试卷类型错误");
/*     */           }
/*     */         
/* 311 */         } else if ("A".equals(titles.get(i))) {
/* 312 */           anser.setContent(ResultConstant.replaceTrim(answers.get(i)));
/* 313 */         } else if ("B".equals(titles.get(i))) {
/* 314 */           anser.setContent(ResultConstant.replaceTrim(answers.get(i)));
/* 315 */         } else if ("C".equals(titles.get(i))) {
/* 316 */           anser.setContent(ResultConstant.replaceTrim(answers.get(i)));
/* 317 */         } else if ("D".equals(titles.get(i))) {
/* 318 */           anser.setContent(ResultConstant.replaceTrim(answers.get(i)));
/* 319 */         } else if ("E".equals(titles.get(i))) {
/* 320 */           anser.setContent(ResultConstant.replaceTrim(answers.get(i)));
/* 321 */         } else if ("F".equals(titles.get(i))) {
/* 322 */           anser.setContent(ResultConstant.replaceTrim(answers.get(i)));
/* 323 */         } else if ("分值".equals(titles.get(i))) {
/* 324 */           anser.setScore(Integer.valueOf(ResultConstant.replaceTrim(answers.get(i))));
/* 325 */           anser.setStatus(Integer.valueOf(1));
/* 326 */           anserList.add(anser);
/* 327 */           anser = new Anser();
/* 328 */         } else if ("题目".equals(titles.get(i))) {
/* 329 */           anserList = new ArrayList<>();
/* 330 */           title = new TestTitle();
/* 331 */           title.setTitle(ResultConstant.replaceTrim(answers.get(i)));
/*     */           
/* 333 */           title.setAnserList(anserList);
/* 334 */           title.setStatus(Integer.valueOf(1));
/*     */           
/* 336 */           title.setSequence(Integer.valueOf(titleCount));
/* 337 */           titleList.add(title);
/* 338 */           titleCount++;
/* 339 */         } else if ("题目类型".equals(titles.get(i))) {
/* 340 */           title.setType(Integer.valueOf(Integer.parseInt(ResultConstant.replaceTrim(answers.get(i)))));
/* 341 */         } else if ("正确答案".equals(titles.get(i))) {
/* 342 */           title.setTrueAnser(ResultConstant.replaceTrim(answers.get(i)));
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 349 */       throw new RuntimeException("导入试卷失败，请检查导入文件的格式");
/*     */     } 
/*     */     
/* 352 */     this.mainTestMapper.addTest(mainTest);
/*     */     
/* 354 */     Integer mainId = mainTest.getId();
/*     */     
/* 356 */     Integer titleId = Integer.valueOf(0);
/*     */ 
/*     */ 
/*     */     
/* 360 */     for (TestTitle title2 : titleList) {
/* 361 */       title2.setMainId(mainId);
/* 362 */       this.testTitleMapper.addTitle(title2);
/* 363 */       titleId = title2.getId();
/* 364 */       anserList = title2.getAnserList();
/* 365 */       for (Anser anser2 : anserList) {
/* 366 */         anser2.setTitleId(titleId);
/* 367 */         anser2.setStatus(Integer.valueOf(1));
/* 368 */         this.testTitleMapper.addAnser(anser2);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 373 */     return null;
/*     */   }
/* 375 */   private static final Logger log = LoggerFactory.getLogger(StudyServiceImpl.class);
/*     */   
/*     */   public void addWord(MainTest mainTest, List<TestTitle> testTitles) {
/* 378 */     int titleCount = 1;
/* 379 */     List<Anser> anserList = new ArrayList<>();
/* 380 */     mainTest.setStatus(Integer.valueOf(1));
/* 381 */     mainTest.setAddDate(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
/* 382 */     for (int i = 0; i < testTitles.size(); i++) {
/* 383 */       ((TestTitle)testTitles.get(i)).setStatus(Integer.valueOf(1));
/*     */     }
/* 385 */     mainTest.setTitle(StringUtils.isNotBlank(mainTest.getTitle()) ? mainTest.getTitle().replaceAll("(?:<br>|</br>)", "") : "");
/* 386 */     mainTest.setRemark(StringUtils.isNotBlank(mainTest.getRemark()) ? mainTest.getRemark().replaceAll("(?:<br>|</br>)", "") : "");
/* 387 */     this.mainTestMapper.addTest(mainTest);
/*     */     
/* 389 */     Integer mainId = mainTest.getId();
/*     */     
/* 391 */     Integer titleId = Integer.valueOf(0);
/*     */ 
/*     */ 
/*     */     
/* 395 */     for (TestTitle title2 : testTitles) {
/* 396 */       title2.setMainId(mainId);
/* 397 */       title2.setStatus(Integer.valueOf(1));
/* 398 */       title2.setSequence(Integer.valueOf(titleCount));
/* 399 */       title2.setAddDate(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
/* 400 */       titleCount++;
/* 401 */       this.testTitleMapper.addTitle(title2);
/* 402 */       titleId = title2.getId();
/* 403 */       anserList = title2.getAnserList();
/* 404 */       for (Anser anser2 : anserList) {
/* 405 */         anser2.setTitleId(titleId);
/* 406 */         anser2.setStatus(Integer.valueOf(1));
/* 407 */         anser2.setAddDate(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
/* 408 */         this.testTitleMapper.addAnser(anser2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTitle(TestTitle testTitle) {
/* 419 */     if (null == testTitle || testTitle.getMainId() == null || testTitle.getId() == null) {
/*     */       return;
/*     */     }
/* 422 */     int count = this.testTitleMapper.updateTestTitle(testTitle);
/* 423 */     if (count == 1) {
/*     */       
/* 425 */       List<Anser> existsAnserList = new ArrayList<>();
/*     */       
/* 427 */       for (int i = 0; i < testTitle.getAnserList().size(); i++) {
/* 428 */         if (null != ((Anser)testTitle.getAnserList().get(i)).getId()) {
/* 429 */           existsAnserList.add(testTitle.getAnserList().get(i));
/*     */         } else {
/* 431 */           ((Anser)testTitle.getAnserList().get(i)).setTitleId(testTitle.getId());
/* 432 */           ((Anser)testTitle.getAnserList().get(i)).setStatus(Integer.valueOf(1));
/* 433 */           ((Anser)testTitle.getAnserList().get(i)).setAddDate(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
/* 434 */           this.testTitleMapper.addAnser(testTitle.getAnserList().get(i));
/*     */         } 
/*     */       } 
/* 437 */       if (existsAnserList.size() > 0) {
/* 438 */         this.testTitleMapper.updateBatchAnser(existsAnserList);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWord(MainTest mainTest) {
/* 445 */     mainTest.setTitle(StringUtils.isNotBlank(mainTest.getTitle()) ? mainTest.getTitle().replaceAll("(?:<br>|</br>|<br/>)", "") : "");
/* 446 */     mainTest.setRemark(StringUtils.isNotBlank(mainTest.getRemark()) ? mainTest.getRemark().replaceAll("(?:<br>|</br>|<br/>)", "") : "");
/* 447 */     this.mainTestMapper.updTestById(mainTest);
/*     */   }
/*     */ 
/*     */   
/*     */   public TestTitle addTitle(TestTitle testTitle) {
/* 452 */     int sequence = this.testTitleMapper.getSequenceCount(testTitle.getMainId());
/* 453 */     testTitle.setSequence(Integer.valueOf(++sequence));
/* 454 */     testTitle.setStatus(Integer.valueOf(1));
/* 455 */     this.testTitleMapper.addTitle(testTitle);
/* 456 */     for (Anser anser : testTitle.getAnserList()) {
/* 457 */       anser.setStatus(Integer.valueOf(1));
/* 458 */       anser.setTitleId(testTitle.getId());
/* 459 */       this.testTitleMapper.addAnser(anser);
/*     */     } 
/* 461 */     return testTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   public String removeTitle(Map<String, Object> map) throws DataAccessException {
/* 466 */     String mainId = (String)map.get("mainId");
/* 467 */     String id = (String)map.get("id");
/* 468 */     this.testTitleMapper.removeTitle(mainId, id);
/* 469 */     Map<String, Object> result = new HashMap<>();
/* 470 */     result.put("flag", Boolean.valueOf(true));
/* 471 */     result.put("message", "修改成功");
/* 472 */     return (new Gson()).toJson(result);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 476 */     System.out.println("<br>1232132143423432</br>".replaceAll("(?:<br>|</br>)", ""));
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\StudyServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */