/*     */ package cn.ssms.controller;
/*     */ 
/*     */ import cn.ssms.dao.ConfigureMapper;
/*     */ import cn.ssms.model.Bbs;
/*     */ import cn.ssms.model.Classfiy;
/*     */ import cn.ssms.model.Configure;
/*     */ import cn.ssms.service.AdvertService;
/*     */ import cn.ssms.service.BbsService;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.DataAccessException;
/*     */ import cn.ssms.util.GetRequestParam;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/bbs"})
/*     */ public class BbsControler
/*     */ {
/*     */   @Autowired
/*     */   private BbsService bbsService;
/*     */   @Autowired
/*     */   private AdvertService advertService;
/*     */   
/*     */   @RequestMapping({"/toClassfiyList"})
/*     */   public String toUserList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  43 */     return "bbs/classfiyList";
/*     */   }
/*     */   @Autowired
/*     */   private ConfigureMapper configureMapper; @Autowired
/*     */   private BbsService bbsMapper;
/*     */   @RequestMapping({"/toClassfiyInfo"})
/*     */   public String toClassfiyInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  50 */     return "bbs/classfiyInfo";
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
/*     */   @RequestMapping(value = {"/classfiyList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String classfiyList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  64 */     Gson gson = new Gson();
/*  65 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  66 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/*  68 */       result.put("flag", Boolean.valueOf(true));
/*  69 */       result.put("msg", "获取成功！");
/*  70 */       String classfiys = this.bbsService.classfiyList(map);
/*     */       
/*  72 */       result.put("data", classfiys);
/*  73 */       return this.bbsService.classfiyList(map);
/*  74 */     } catch (Exception e) {
/*     */       
/*  76 */       e.printStackTrace();
/*  77 */       result.put("flag", Boolean.valueOf(false));
/*  78 */       result.put("msg", "获取失败！");
/*  79 */       return gson.toJson(result);
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
/*     */   @RequestMapping(value = {"/addClassfiy"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addClassfiy(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  94 */     Gson gson = new Gson();
/*  95 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  96 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/*  98 */       Classfiy classfiy = this.advertService.addClassfiy(map);
/*  99 */       if (classfiy == null) {
/* 100 */         result.put("flag", Boolean.valueOf(false));
/* 101 */         result.put("msg", "新增失败！");
/*     */       } else {
/* 103 */         result.put("flag", Boolean.valueOf(true));
/* 104 */         result.put("msg", "新增成功！");
/*     */       } 
/* 106 */       return gson.toJson(result);
/* 107 */     } catch (Exception e) {
/* 108 */       e.printStackTrace();
/* 109 */       result.put("flag", Boolean.valueOf(false));
/* 110 */       result.put("msg", "新增失败！");
/* 111 */       return gson.toJson(result);
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
/*     */   @RequestMapping(value = {"/updClassfiy"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updClassfiy(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 126 */     Gson gson = new Gson();
/* 127 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 128 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/* 130 */       Classfiy classfiy = this.advertService.updClassfiy(map);
/* 131 */       if (classfiy == null) {
/* 132 */         result.put("flag", Boolean.valueOf(false));
/* 133 */         result.put("msg", "修改失败！");
/*     */       } else {
/* 135 */         result.put("flag", Boolean.valueOf(true));
/* 136 */         result.put("msg", "修改成功！");
/*     */       } 
/* 138 */       return gson.toJson(result);
/* 139 */     } catch (Exception e) {
/* 140 */       e.printStackTrace();
/* 141 */       result.put("flag", Boolean.valueOf(false));
/* 142 */       result.put("msg", "修改失败！");
/* 143 */       return gson.toJson(result);
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
/*     */   
/*     */   @RequestMapping(value = {"/removeClassfiy"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeClassfiy(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 159 */     Gson gson = new Gson();
/* 160 */     Map<String, Object> resultMap = new HashMap<>();
/* 161 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 162 */     List<Classfiy> classfiys = null;
/*     */     try {
/* 164 */       classfiys = this.bbsService.removeClassfiy((String)map.get("ids"));
/* 165 */     } catch (Exception e) {
/* 166 */       e.printStackTrace();
/* 167 */       resultMap.put("flag", Boolean.valueOf(false));
/* 168 */       resultMap.put("msg", "删除失败!");
/* 169 */       return gson.toJson(resultMap);
/*     */     } 
/*     */     
/* 172 */     if (classfiys == null) {
/* 173 */       resultMap.put("flag", Boolean.valueOf(false));
/* 174 */       resultMap.put("msg", "删除失败!");
/* 175 */       return gson.toJson(resultMap);
/*     */     } 
/* 177 */     resultMap.put("flag", Boolean.valueOf(true));
/* 178 */     resultMap.put("msg", "删除成功!");
/* 179 */     resultMap.put("data", classfiys);
/* 180 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getClassfiyById"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getClassfiyById(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 190 */     Gson gson = new Gson();
/* 191 */     Map<String, Object> resultMap = new HashMap<>();
/* 192 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     try {
/* 194 */       Classfiy classfiy = this.advertService.getClassfiyById((String)map.get("id"));
/* 195 */       if (classfiy != null) {
/* 196 */         resultMap.put("flag", Boolean.valueOf(true));
/* 197 */         resultMap.put("msg", "获取成功!");
/* 198 */         resultMap.put("data", classfiy);
/*     */       } else {
/*     */         
/* 201 */         resultMap.put("flag", Boolean.valueOf(false));
/* 202 */         resultMap.put("msg", "获取失败!");
/*     */       } 
/* 204 */     } catch (Exception e) {
/* 205 */       e.printStackTrace();
/* 206 */       resultMap.put("flag", Boolean.valueOf(false));
/* 207 */       resultMap.put("msg", "获取失败!");
/*     */     } 
/* 209 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/toBbsList"})
/*     */   public String toBbsList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 216 */     return "bbs/bbsList";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/bbsInfo"})
/*     */   public String toBbsfiyInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 223 */     return "bbs/bbsInfo";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getBbsList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String bbsList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 229 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 230 */     return this.bbsService.getBbsList(map);
/*     */   }
/*     */   
/*     */   @RequestMapping({"bbsReply"})
/*     */   public String topicReply(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 235 */     System.out.println("跳转帖子详情列表");
/* 236 */     return "bbs/bbsReply";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getBbsReply"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String replyList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 242 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 243 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     try {
/* 245 */       return this.bbsService.selectReply(map);
/* 246 */     } catch (DataAccessException e) {
/* 247 */       resultMap.put("flag", Boolean.valueOf(false));
/* 248 */       resultMap.put("msg", "修改失败!");
/* 249 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getBbsById"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String findBbsOne(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 256 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 257 */     return this.bbsService.findBbsOne(map);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updBbs"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updBbs(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 263 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 264 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     try {
/* 266 */       return this.bbsService.updBbsDetail(map);
/* 267 */     } catch (DataAccessException e) {
/* 268 */       resultMap.put("flag", Boolean.valueOf(false));
/* 269 */       resultMap.put("msg", "修改失败!");
/* 270 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/removeBbs"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeBbs(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 277 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 278 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     try {
/* 280 */       return this.bbsService.removeBbs(map);
/* 281 */     } catch (DataAccessException e) {
/* 282 */       resultMap.put("flag", Boolean.valueOf(false));
/* 283 */       resultMap.put("msg", "删除失败!");
/* 284 */       return (new Gson()).toJson(resultMap);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getClassify"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getSection(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 292 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 293 */     List<Configure> getClassify = this.configureMapper.getClassify();
/* 294 */     return (new Gson()).toJson(getClassify);
/*     */   }
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping({"upload"})
/*     */   public String upload(HttpServletRequest request, HttpServletResponse response) {
/* 301 */     Map<String, Object> resultMap = new HashMap<>();
/*     */     
/* 303 */     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
/* 304 */     if (multipartResolver.isMultipart(request)) {
/* 305 */       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
/*     */       
/* 307 */       Iterator<String> iter = multipartRequest.getFileNames();
/* 308 */       String route = request.getRealPath("/") + "picture\\bbs\\";
/*     */       
/* 310 */       File dir = new File(route);
/* 311 */       if (!dir.exists()) {
/* 312 */         dir.mkdirs();
/*     */       }
/* 314 */       while (iter.hasNext()) {
/*     */         
/* 316 */         List<MultipartFile> files = multipartRequest.getFiles(iter.next());
/* 317 */         String fileNames = "";
/* 318 */         String suffix = "";
/* 319 */         String paths = "";
/* 320 */         String newFileNames = "";
/* 321 */         String relativePaths = "";
/* 322 */         for (int i = 0; i < files.size(); i++) {
/* 323 */           MultipartFile file = files.get(i);
/* 324 */           String fileName = file.getOriginalFilename();
/* 325 */           String newFileName = (new Date()).getTime() + "-0" + i + fileName.substring(fileName.indexOf("."));
/* 326 */           String path = route + newFileName;
/* 327 */           File localFile = new File(path);
/*     */           
/* 329 */           String relativePath = Contant.bbsPhotoUrlString + "/picture/bbs/" + newFileName;
/* 330 */           String fileName1 = (String)fileName.subSequence(0, fileName.lastIndexOf("."));
/* 331 */           String suffix1 = fileName.substring(fileName.indexOf(".") + 1);
/*     */           
/* 333 */           if (fileNames.trim().equals("")) {
/* 334 */             fileNames = fileName1;
/*     */           } else {
/* 336 */             fileNames = fileNames + "," + fileName1;
/* 337 */           }  if (suffix.trim().equals("")) {
/* 338 */             suffix = suffix1;
/*     */           } else {
/* 340 */             suffix = suffix + "," + suffix1;
/* 341 */           }  if (paths.trim().equals("")) {
/* 342 */             paths = path;
/*     */           } else {
/* 344 */             paths = paths + "," + path;
/* 345 */           }  if (relativePaths.trim().equals("")) {
/* 346 */             relativePaths = relativePath;
/*     */           } else {
/* 348 */             relativePaths = relativePaths + "," + relativePath;
/*     */           }  try {
/* 350 */             file.transferTo(localFile);
/* 351 */           } catch (Exception e) {
/* 352 */             resultMap.put("succeed", Boolean.valueOf(false));
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 361 */         resultMap.put("fileName", fileNames);
/* 362 */         resultMap.put("suffix", suffix);
/* 363 */         resultMap.put("path", paths);
/*     */         
/* 365 */         resultMap.put("relativePaths", relativePaths);
/* 366 */         resultMap.put("succeed", Boolean.valueOf(true));
/*     */       } 
/*     */     } 
/* 369 */     return (new Gson()).toJson(resultMap);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/isReName2"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isReName(HttpServletRequest request, HttpServletResponse response, String name, String id) {
/* 376 */     Gson gson = new Gson();
/* 377 */     Map<String, Object> map = new HashMap<>();
/* 378 */     map.put("name", name);
/* 379 */     map.put("type", Integer.valueOf(2));
/* 380 */     if (id != null) {
/* 381 */       map.put("id", id);
/*     */     }
/* 383 */     List<Classfiy> list = this.advertService.isReName(map);
/* 384 */     Map<String, Object> resultMap = new HashMap<>();
/* 385 */     if (list.size() > 0) {
/* 386 */       resultMap.put("flag", Integer.valueOf(1));
/* 387 */       resultMap.put("msg", "板块名称重复");
/* 388 */       return gson.toJson(resultMap);
/*     */     } 
/* 390 */     resultMap.put("flag", Integer.valueOf(0));
/* 391 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/isReQuence2"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isReQuence(HttpServletRequest request, HttpServletResponse response, String sequence, String id) {
/* 399 */     Gson gson = new Gson();
/* 400 */     Map<String, Object> map = new HashMap<>();
/* 401 */     map.put("sequence", sequence);
/* 402 */     map.put("type", Integer.valueOf(2));
/* 403 */     if (id != null) {
/* 404 */       map.put("id", id);
/*     */     }
/* 406 */     List<Classfiy> list = this.advertService.isReSequene(map);
/* 407 */     Map<String, Object> resultMap = new HashMap<>();
/* 408 */     if (list.size() > 0) {
/* 409 */       resultMap.put("flag", Integer.valueOf(1));
/* 410 */       resultMap.put("msg", "板块序列重复");
/* 411 */       return gson.toJson(resultMap);
/*     */     } 
/* 413 */     resultMap.put("flag", Integer.valueOf(0));
/* 414 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/isData"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isData(HttpServletRequest request, HttpServletResponse response, String ids) {
/* 422 */     Gson gson = new Gson();
/* 423 */     Map<String, Object> resultMap = new HashMap<>();
/* 424 */     if (!ids.isEmpty()) {
/* 425 */       String[] idsStr = ids.split(",");
/* 426 */       if (idsStr.length == 1) {
/* 427 */         Map<String, Object> map = new HashMap<>();
/* 428 */         map.put("classifyId", ids);
/*     */         
/* 430 */         List<Bbs> list = this.bbsMapper.getTbbsByClassFiyId(map);
/*     */         
/* 432 */         if (list.size() > 0) {
/* 433 */           resultMap.put("flag", Integer.valueOf(1));
/* 434 */           resultMap.put("msg", "该板块有发布帖子，不能删除");
/* 435 */           return gson.toJson(resultMap);
/*     */         } 
/* 437 */         resultMap.put("flag", Integer.valueOf(0));
/* 438 */         return gson.toJson(resultMap);
/*     */       } 
/*     */       
/* 441 */       resultMap.put("flag", Integer.valueOf(0));
/* 442 */       return gson.toJson(resultMap);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 447 */     resultMap.put("flag", Integer.valueOf(0));
/* 448 */     return gson.toJson(resultMap);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\BbsControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */