/*     */ package cn.ssms.controller;
/*     */
/*     */

import cn.ssms.dao.FileMapper;
import cn.ssms.dao.TResourceSyncMapper;
import cn.ssms.model.Configure;
import cn.ssms.model.File;
import cn.ssms.model.Resources;
import cn.ssms.service.ResourceService;
import cn.ssms.util.Contant;
import cn.ssms.util.FileList;
import cn.ssms.util.GetRequestParam;
import cn.ssms.util.TextUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ @Controller
/*     */ @RequestMapping({"/resource"})
/*     */ public class ResourceControler
/*     */ {
/*     */   @Autowired
/*     */   private ResourceService resourceService;
/*     */   @Autowired
/*     */   private FileMapper fileMapper;
/*     */   @Autowired
/*     */   private TResourceSyncMapper tResourceSyncMapper;
/*  40 */   private static final Logger log = LoggerFactory.getLogger(ResourceControler.class);
/*     */   
/*     */   @RequestMapping({"/videoList"})
/*     */   public String videoList(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  44 */     List<Configure> category = this.resourceService.getConfigByType(Integer.valueOf(1));
/*  45 */     model.addAttribute("category", category);
/*  46 */     return "resource/videoList";
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
/*     */   @RequestMapping(value = {"/syncVideo"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String syncVideo(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  60 */     return JSON.toJSONString(this.resourceService.syncVideoResource());
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
/*     */   @RequestMapping(value = {"/syncGame"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String syncGame(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  74 */     return JSON.toJSONString(this.resourceService.syncGameResource());
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
/*     */   @RequestMapping(value = {"/syncAudio"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String syncAudio(HttpServletRequest request, HttpServletResponse response, Model model) {
/*  88 */     return JSON.toJSONString(this.resourceService.syncAudioResource());
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
/*     */   @RequestMapping(value = {"/syncBook"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String syncBook(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 103 */     return JSON.toJSONString(this.resourceService.syncBookResource());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/musicList"})
/*     */   public String musicList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 110 */     List<Configure> category = this.resourceService.getConfigByType(Integer.valueOf(7));
/* 111 */     model.addAttribute("category", category);
/* 112 */     return "resource/musicList";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/bookList"})
/*     */   public String bookList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 117 */     List<Configure> category = this.resourceService.getConfigByType(Integer.valueOf(6));
/* 118 */     model.addAttribute("category", category);
/* 119 */     return "resource/bookList";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/gameList"})
/*     */   public String gameList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 124 */     List<Configure> category = this.resourceService.getConfigByType(Integer.valueOf(9));
/* 125 */     model.addAttribute("category", category);
/* 126 */     return "resource/gameList";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/videoInfo"})
/*     */   public String videoInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 131 */     List<Configure> category = this.resourceService.getConfigByType(Integer.valueOf(1));
/* 132 */     model.addAttribute("category", category);
/* 133 */     List<Configure> type = this.resourceService.getConfigByType(Integer.valueOf(2));
/* 134 */     model.addAttribute("type", type);
/* 135 */     List<Configure> videoRegion = this.resourceService.getConfigByType(Integer.valueOf(3));
/* 136 */     model.addAttribute("videoRegion", videoRegion);
/* 137 */     List<Configure> videoYear = this.resourceService.getConfigByType(Integer.valueOf(4));
/* 138 */     model.addAttribute("videoYear", videoYear);
/* 139 */     String userName = TextUtil.getString(request.getSession().getAttribute("userName"));
/* 140 */     model.addAttribute("userName", userName);
/* 141 */     return "resource/videoInfo";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/musicInfo"})
/*     */   public String musicInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 146 */     List<Configure> type = this.resourceService.getConfigByType(Integer.valueOf(7));
/* 147 */     model.addAttribute("type", type);
/* 148 */     String userName = TextUtil.getString(request.getSession().getAttribute("userName"));
/* 149 */     model.addAttribute("userName", userName);
/* 150 */     return "resource/musicInfo";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/bookInfo"})
/*     */   public String bookInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 155 */     List<Configure> type = this.resourceService.getConfigByType(Integer.valueOf(6));
/* 156 */     model.addAttribute("type", type);
/* 157 */     String userName = TextUtil.getString(request.getSession().getAttribute("userName"));
/* 158 */     model.addAttribute("userName", userName);
/* 159 */     return "resource/bookInfo";
/*     */   }
/*     */   
/*     */   @RequestMapping({"/gameInfo"})
/*     */   public String gameInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 164 */     List<Configure> type = this.resourceService.getConfigByType(Integer.valueOf(9));
/* 165 */     model.addAttribute("type", type);
/* 166 */     String userName = TextUtil.getString(request.getSession().getAttribute("userName"));
/* 167 */     model.addAttribute("userName", userName);
/* 168 */     return "resource/gameInfo";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getResourceList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getResourceList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 181 */     Integer page = Integer.valueOf((request.getParameter("page") == null) ? 1 : Integer.valueOf(request.getParameter("page")).intValue());
/* 182 */     Integer pageSize = (request.getParameter("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(request.getParameter("pageSize"));
/* 183 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 184 */     map.put("index", page);
/* 185 */     map.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 186 */     map.put("pageSize", pageSize);
/* 187 */     String result = this.resourceService.getResourceList(map);
/* 188 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/addResource"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String addResource(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 201 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 202 */     map.put("path", request.getSession().getServletContext().getRealPath("/"));
/*     */     
/* 204 */     String id = TextUtil.getString(request.getSession().getAttribute("id"));
/* 205 */     map.put("addPerson", id);
/*     */ 
/*     */     
/* 208 */     return this.resourceService.addResource(map);
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
/*     */   @RequestMapping(value = {"/udpResource"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String udpResource(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 222 */     Map<String, Object> map = GetRequestParam.setMap(request);
/* 223 */     map.put("path", request.getSession().getServletContext().getRealPath("/"));
/*     */     
/* 225 */     return this.resourceService.udpResource(map);
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
/*     */   @RequestMapping(value = {"/getResourceById"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getResourceById(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 239 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 240 */     File file = this.resourceService.getResourceById(params);
/*     */     
/* 242 */     List<Resources> resources = this.resourceService.getResources(params);
/* 243 */     Map<String, Object> result = new HashMap<>();
/* 244 */     result.put("file", file);
/* 245 */     result.put("resources", resources);
/* 246 */     return (new Gson()).toJson(result);
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
/*     */   @RequestMapping(value = {"/removeResource"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeResource(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 260 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*     */     try {
/* 262 */       return this.resourceService.removeResource(params);
/* 263 */     } catch (Exception e) {
/* 264 */       e.printStackTrace();
/* 265 */       Map<String, Object> result = new HashMap<>();
/* 266 */       result.put("flag", Boolean.valueOf(false));
/* 267 */       result.put("message", "删除失败");
/* 268 */       return (new Gson()).toJson(result);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getFileList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getFileList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 333 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 334 */     Map<String, Object> result = new HashMap<>();
/* 335 */     List<Resources> list = new ArrayList<>();
/* 336 */     List<File> files = new ArrayList<>();
/*     */     try {
/* 338 */       FileList fileList = new FileList();
/* 339 */       String url = Contant.FILEURL + Contant.TYPE[Integer.parseInt((new StringBuilder()).append(params.get("fileType")).append("").toString()) - 1];
/* 340 */       String baseUrl = Contant.TYPE[Integer.parseInt((new StringBuilder()).append(params.get("fileType")).append("").toString()) - 1] + "/";
/* 341 */       if (Integer.parseInt((new StringBuilder()).append(params.get("fileType")).append("").toString()) == 1) {
/* 342 */         url = url + "\\" + params.get("category") + "\\" + params.get("name");
/* 343 */         baseUrl = baseUrl + params.get("category") + "/" + params.get("name") + "/";
/* 344 */         List<String> folders = fileList.getFolders(url);
/* 345 */         System.out.println(folders.toString());
/* 346 */         for (String name : folders) {
/* 347 */           Resources re = new Resources();
/* 348 */           re.setUrl(baseUrl + name + "/");
/* 349 */           list.add(re);
/* 350 */           File file = new File();
/* 351 */           file.setName(name);
/* 352 */           file.setResources(fileList.getList(url + "\\" + name, baseUrl + name + "/"));
/* 353 */           files.add(file);
/*     */         } 
/*     */       } else {
/* 356 */         url = url + "\\" + params.get("name");
/* 357 */         baseUrl = baseUrl + params.get("name") + "/";
/* 358 */         list = fileList.getList(url, baseUrl);
/* 359 */         for (Resources res : list) {
/* 360 */           List<Resources> ce = new ArrayList<>();
/* 361 */           ce.add(res);
/* 362 */           File file = new File();
/* 363 */           String name = res.getUrl();
/* 364 */           name = name.substring(name.lastIndexOf("/") + 1);
/* 365 */           name = name.substring(0, name.lastIndexOf("."));
/* 366 */           file.setName(name);
/* 367 */           file.setResources(ce);
/* 368 */           files.add(file);
/*     */         } 
/*     */       } 
/* 371 */       if (list != null && list.size() > 0) {
/* 372 */         result.put("flag", Boolean.valueOf(true));
/* 373 */         result.put("data", list);
/* 374 */         result.put("files", files);
/*     */       } else {
/* 376 */         result.put("flag", Boolean.valueOf(false));
/*     */       }
/*     */     
/*     */     }
/* 380 */     catch (Exception e1) {
/* 381 */       e1.printStackTrace();
/* 382 */       result.put("flag", Boolean.valueOf(false));
/*     */     } 
/* 384 */     return (new Gson()).toJson(result);
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
/*     */   @RequestMapping(value = {"/getDocumentFileList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getDocumentFileList(HttpServletRequest request, HttpServletResponse response, Model model) {
/* 399 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 400 */     Map<String, Object> result = new HashMap<>(2);
/* 401 */     List<String> files = new ArrayList<>();
/*     */     
/* 403 */     String url = Contant.FILEURL + Contant.TYPE[Integer.parseInt((new StringBuilder()).append(params.get("fileType")).append("").toString()) - 1];
/* 404 */     if (Integer.parseInt((new StringBuilder()).append(params.get("fileType")).append("").toString()) == 1) {
/* 405 */       url = url + "\\" + params.get("category");
/*     */     }
/* 407 */     java.io.File[] filesTemp = (new java.io.File(url)).listFiles();
/* 408 */     if (filesTemp != null) {
/* 409 */       for (int i = 0; i < filesTemp.length; i++) {
/* 410 */         java.io.File file = filesTemp[i];
/* 411 */         if (file.isDirectory() && (
/* 412 */           file.list()).length > 0) {
/* 413 */           files.add(file.getName());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 418 */     result.put("files", files);
/* 419 */     return (new Gson()).toJson(result);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getConfig"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getConfig(HttpServletRequest request, HttpServletResponse response, Model model) {
/*     */     try {
/* 426 */       String type = request.getParameter("type");
/*     */       
/* 428 */       List<Configure> list = this.resourceService.getConfigByType(Integer.valueOf(type));
/*     */       
/* 430 */       return (new Gson()).toJson(list);
/* 431 */     } catch (Exception e) {
/* 432 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\ResourceControler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */