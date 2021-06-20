/*     */ package cn.ssms.controller;
/*     */ 
/*     */ import cn.ssms.model.Advert;
/*     */ import cn.ssms.model.Classfiy;
/*     */ import cn.ssms.model.Infor;
/*     */ import cn.ssms.service.AdvertService;
/*     */ import cn.ssms.service.InforService;
/*     */ import cn.ssms.service.UserService;
/*     */ import cn.ssms.ueditor.ActionEnter;
/*     */ import cn.ssms.util.GetRequestParam;
/*     */ import cn.ssms.util.TextUtil;
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/advert"})
/*     */ public class AdvertControler {
/*  35 */   private static final Logger log = LoggerFactory.getLogger(StudyController.class);
/*     */   @Autowired
/*     */   private AdvertService advertService;
/*     */   @Autowired
/*     */   private UserService userService;
/*     */   @Autowired
/*     */   private InforService inforService;
/*     */   
/*     */   @RequestMapping({"/toClassfiyList"})
/*     */   public String toUserList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
/*  45 */     Gson gson = new Gson();
/*     */     
/*  47 */     String classfiy = this.advertService.classfiyList(request.getParameterMap());
/*     */ 
/*     */     
/*  50 */     return "advert/classfiyList";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/toClassfiyInfo"})
/*     */   public String toClassfiyInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  57 */     return "advert/classfiyInfo";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping({"/toAdvertList"})
/*     */   public String toAdvertList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  63 */     List<Classfiy> classfiys = this.advertService.getList(new HashMap<String,Object>());
/*  64 */     model.addAttribute("classfiys", classfiys);
/*  65 */     return "advert/advert";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/toAdvertInfo"})
/*     */   public String toAdvertInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  70 */     List<Classfiy> classfiys = this.advertService.getList(new HashMap<String,Object>());
/*  71 */     model.addAttribute("classfiys", classfiys);
/*  72 */     String userName = TextUtil.getString(request.getSession().getAttribute("userName"));
/*  73 */     model.addAttribute("userName", userName);
/*  74 */     return "advert/advertInfo";
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
/*  88 */     Gson gson = new Gson();
/*  89 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  90 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/*  92 */       result.put("flag", Boolean.valueOf(true));
/*  93 */       result.put("msg", "获取成功！");
/*     */       
/*  95 */       String classfiys = this.advertService.classfiyList(map);
/*  96 */       result.put("data", classfiys);
/*  97 */       return this.advertService.classfiyList(map);
/*  98 */     } catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */       result.put("flag", Boolean.valueOf(false));
/* 101 */       result.put("msg", "获取失败！");
/* 102 */       return gson.toJson(result);
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
/* 117 */     Gson gson = new Gson();
/* 118 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 119 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/* 121 */       Classfiy classfiy = this.advertService.addClassfiy(map);
/* 122 */       if (classfiy == null) {
/* 123 */         result.put("flag", Boolean.valueOf(false));
/* 124 */         result.put("msg", "新增失败！");
/*     */       } else {
/* 126 */         result.put("flag", Boolean.valueOf(true));
/* 127 */         result.put("msg", "新增成功！");
/*     */       } 
/* 129 */       return gson.toJson(result);
/* 130 */     } catch (Exception e) {
/* 131 */       e.printStackTrace();
/* 132 */       result.put("flag", Boolean.valueOf(false));
/* 133 */       result.put("msg", "新增失败！");
/* 134 */       return gson.toJson(result);
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
/* 149 */     Gson gson = new Gson();
/* 150 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 151 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/* 153 */       Classfiy classfiy = this.advertService.updClassfiy(map);
/* 154 */       if (classfiy == null) {
/* 155 */         result.put("flag", Boolean.valueOf(false));
/* 156 */         result.put("msg", "修改失败！");
/*     */       } else {
/* 158 */         result.put("flag", Boolean.valueOf(true));
/* 159 */         result.put("msg", "修改成功！");
/*     */       } 
/* 161 */       return gson.toJson(result);
/* 162 */     } catch (Exception e) {
/* 163 */       e.printStackTrace();
/* 164 */       result.put("flag", Boolean.valueOf(false));
/* 165 */       result.put("msg", "修改失败！");
/* 166 */       return gson.toJson(result);
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
/*     */   @RequestMapping(value = {"/removeClassfiy"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeClassfiy(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 181 */     Gson gson = new Gson();
/* 182 */     Map<String, Object> resultMap = new HashMap<>();
/* 183 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 184 */     List<Classfiy> classfiys = null;
/*     */     try {
/* 186 */       classfiys = this.advertService.removeClassfiy((String)map.get("ids"));
/* 187 */     } catch (Exception e) {
/* 188 */       e.printStackTrace();
/* 189 */       resultMap.put("flag", Boolean.valueOf(false));
/* 190 */       resultMap.put("msg", "删除失败!");
/* 191 */       return gson.toJson(resultMap);
/*     */     } 
/*     */     
/* 194 */     if (classfiys == null) {
/* 195 */       resultMap.put("flag", Boolean.valueOf(false));
/* 196 */       resultMap.put("msg", "删除失败!");
/* 197 */       return gson.toJson(resultMap);
/*     */     } 
/* 199 */     resultMap.put("flag", Boolean.valueOf(true));
/* 200 */     resultMap.put("msg", "删除成功!");
/* 201 */     resultMap.put("data", classfiys);
/* 202 */     return gson.toJson(resultMap);
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
/*     */   @RequestMapping(value = {"/getClassfiyById"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getClassfiyById(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 217 */     Gson gson = new Gson();
/* 218 */     Map<String, Object> resultMap = new HashMap<>();
/* 219 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     try {
/* 221 */       Classfiy classfiy = this.advertService.getClassfiyById((String)map.get("id"));
/* 222 */       if (classfiy != null) {
/* 223 */         resultMap.put("flag", Boolean.valueOf(true));
/* 224 */         resultMap.put("msg", "获取成功!");
/* 225 */         resultMap.put("data", classfiy);
/*     */       } else {
/*     */         
/* 228 */         resultMap.put("flag", Boolean.valueOf(false));
/* 229 */         resultMap.put("msg", "获取失败!");
/*     */       } 
/* 231 */     } catch (Exception e) {
/* 232 */       e.printStackTrace();
/* 233 */       resultMap.put("flag", Boolean.valueOf(false));
/* 234 */       resultMap.put("msg", "获取失败!");
/*     */     } 
/* 236 */     return gson.toJson(resultMap);
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
/*     */   @RequestMapping(value = {"/advertList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String advertList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 250 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     
/*     */     try {
/* 253 */       return this.advertService.getAdvertList(map);
/* 254 */     } catch (Exception e) {
/* 255 */       e.printStackTrace();
/* 256 */       return null;
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
/*     */   @RequestMapping(value = {"/addAdvert"}, method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addAdvert(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
/* 271 */     Gson gson = new Gson();
/* 272 */     String str = IOUtils.toString((InputStream)request.getInputStream(), "utf-8");
/* 273 */     log.info(str);
/*     */     
/* 275 */     Advert advert1 = (Advert)JSON.parseObject(str, Advert.class);
/* 276 */     Map<String, Object> result = new HashMap<>();
/*     */ 
/*     */     
/*     */     try {
/* 280 */       Advert advert = this.advertService.addAdvert(advert1);
/* 281 */       if (advert == null) {
/* 282 */         result.put("flag", Boolean.valueOf(false));
/* 283 */         result.put("msg", "新增失败！");
/*     */       } else {
/* 285 */         result.put("flag", Boolean.valueOf(true));
/* 286 */         result.put("msg", "新增成功！");
/*     */       } 
/* 288 */       return gson.toJson(result);
/* 289 */     } catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       result.put("flag", Boolean.valueOf(false));
/* 292 */       result.put("msg", "新增失败！");
/* 293 */       return gson.toJson(result);
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
/*     */   @RequestMapping(value = {"/updAdvert"}, method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String updAdvert(HttpServletRequest request, Model model) throws IOException {
/* 307 */     String str = IOUtils.toString((InputStream)request.getInputStream(), "utf-8");
/* 308 */     log.info(str);
/*     */     
/* 310 */     Advert advert1 = (Advert)JSON.parseObject(str, Advert.class);
/*     */     
/* 312 */     Gson gson = new Gson();
/* 313 */     Map<String, Object> result = new HashMap<>();
/*     */     try {
/* 315 */       Advert advert = this.advertService.updAdvert(advert1);
/* 316 */       if (advert == null) {
/* 317 */         result.put("flag", Boolean.valueOf(false));
/* 318 */         result.put("msg", "修改失败！");
/*     */       } else {
/* 320 */         result.put("flag", Boolean.valueOf(true));
/* 321 */         result.put("msg", "修改成功！");
/*     */       } 
/* 323 */       return gson.toJson(result);
/* 324 */     } catch (Exception e) {
/* 325 */       e.printStackTrace();
/* 326 */       result.put("flag", Boolean.valueOf(false));
/* 327 */       result.put("msg", "修改失败！");
/* 328 */       return gson.toJson(result);
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
/*     */   @RequestMapping(value = {"/removeAdvert"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeAdvert(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 343 */     Gson gson = new Gson();
/* 344 */     Map<String, Object> resultMap = new HashMap<>();
/* 345 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 346 */     List<Advert> adverts = null;
/*     */     try {
/* 348 */       adverts = this.advertService.removeAdvert((String)map.get("ids"));
/* 349 */     } catch (Exception e) {
/* 350 */       e.printStackTrace();
/* 351 */       resultMap.put("flag", Boolean.valueOf(false));
/* 352 */       resultMap.put("msg", "删除失败!");
/* 353 */       return gson.toJson(resultMap);
/*     */     } 
/*     */     
/* 356 */     if (adverts == null) {
/* 357 */       resultMap.put("flag", Boolean.valueOf(false));
/* 358 */       resultMap.put("msg", "删除失败!");
/* 359 */       return gson.toJson(resultMap);
/*     */     } 
/* 361 */     resultMap.put("flag", Boolean.valueOf(true));
/* 362 */     resultMap.put("msg", "删除成功!");
/* 363 */     resultMap.put("data", adverts);
/* 364 */     return gson.toJson(resultMap);
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
/*     */   @RequestMapping(value = {"/getAdvertById"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getAdvertById(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 379 */     Gson gson = new Gson();
/* 380 */     Map<String, Object> resultMap = new HashMap<>();
/* 381 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*     */     
/*     */     try {
/* 384 */       List<Classfiy> classfiys = this.advertService.getList(new HashMap<String,Object>());
/* 385 */       resultMap.put("classfiys", classfiys);
/*     */       
/* 387 */       Advert advert = this.advertService.getAdvertById((String)map.get("id"));
/* 388 */       if (advert != null) {
/* 389 */         resultMap.put("flag", Boolean.valueOf(true));
/* 390 */         resultMap.put("msg", "获取成功!");
/* 391 */         resultMap.put("data", advert);
/*     */       } else {
/*     */         
/* 394 */         resultMap.put("flag", Boolean.valueOf(false));
/* 395 */         resultMap.put("msg", "获取失败!");
/*     */       } 
/* 397 */     } catch (Exception e) {
/* 398 */       e.printStackTrace();
/* 399 */       resultMap.put("flag", Boolean.valueOf(false));
/* 400 */       resultMap.put("msg", "获取失败!");
/*     */     } 
/* 402 */     return gson.toJson(resultMap);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/isReName"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isReName(HttpServletRequest request, HttpServletResponse response, String name, String id) {
/* 408 */     Gson gson = new Gson();
/* 409 */     Map<String, Object> map = new HashMap<>();
/* 410 */     map.put("name", name);
/* 411 */     map.put("type", Integer.valueOf(1));
/* 412 */     if (id != null) {
/* 413 */       map.put("id", id);
/*     */     }
/* 415 */     List<Classfiy> list = this.advertService.isReName(map);
/* 416 */     Map<String, Object> resultMap = new HashMap<>();
/* 417 */     if (list.size() > 0) {
/* 418 */       resultMap.put("flag", Integer.valueOf(1));
/* 419 */       resultMap.put("msg", "板块名称重复");
/* 420 */       return gson.toJson(resultMap);
/*     */     } 
/* 422 */     resultMap.put("flag", Integer.valueOf(0));
/* 423 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/isReQuence1"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isReQuence(HttpServletRequest request, HttpServletResponse response, String sequence, String id) {
/* 430 */     Gson gson = new Gson();
/* 431 */     Map<String, Object> map = new HashMap<>();
/* 432 */     map.put("sequence", sequence);
/* 433 */     map.put("type", Integer.valueOf(1));
/* 434 */     if (id != null) {
/* 435 */       map.put("id", id);
/*     */     }
/* 437 */     List<Classfiy> list = this.advertService.isReSequene(map);
/* 438 */     Map<String, Object> resultMap = new HashMap<>();
/* 439 */     if (list.size() > 0) {
/* 440 */       resultMap.put("flag", Integer.valueOf(1));
/* 441 */       resultMap.put("msg", "板块序列重复");
/* 442 */       return gson.toJson(resultMap);
/*     */     } 
/* 444 */     resultMap.put("flag", Integer.valueOf(0));
/* 445 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/isData"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String isData(HttpServletRequest request, HttpServletResponse response, String ids) {
/* 452 */     Gson gson = new Gson();
/* 453 */     Map<String, Object> resultMap = new HashMap<>();
/* 454 */     if (!ids.isEmpty()) {
/* 455 */       String[] idsStr = ids.split(",");
/* 456 */       if (idsStr.length == 1) {
/* 457 */         Map<String, Object> map = new HashMap<>();
/* 458 */         map.put("classifyId", ids);
/*     */         
/* 460 */         List<Infor> list = this.inforService.getInforByClassFiyId(map);
/*     */         
/* 462 */         if (list.size() > 0) {
/* 463 */           resultMap.put("flag", Integer.valueOf(1));
/* 464 */           resultMap.put("msg", "该板块有发布资讯，不能删除");
/* 465 */           return gson.toJson(resultMap);
/*     */         } 
/* 467 */         resultMap.put("flag", Integer.valueOf(0));
/* 468 */         return gson.toJson(resultMap);
/*     */       } 
/*     */ 
/*     */       
/* 472 */       resultMap.put("flag", Integer.valueOf(0));
/* 473 */       return gson.toJson(resultMap);
/*     */     } 
/*     */ 
/*     */     
/* 477 */     resultMap.put("flag", Integer.valueOf(0));
/* 478 */     return gson.toJson(resultMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/config"})
/*     */   public void config(HttpServletRequest request, HttpServletResponse response) {
/* 486 */     response.setContentType("application/json");
/* 487 */     String rootPath = request.getSession().getServletContext().getRealPath("/");
/*     */     try {
/* 489 */       String exec = (new ActionEnter(request, rootPath)).exec();
/* 490 */       PrintWriter writer = response.getWriter();
/* 491 */       writer.write(exec);
/* 492 */       writer.flush();
/* 493 */       writer.close();
/* 494 */     } catch (IOException e) {
/* 495 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\AdvertControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */