/*     */ package cn.ssms.service;
/*     */ 
/*     */ import cn.ssms.dao.AdvertMapper;
/*     */ import cn.ssms.dao.ClassfiyMapper;
/*     */ import cn.ssms.dao.InforMapper;
/*     */ import cn.ssms.model.Advert;
/*     */ import cn.ssms.model.Classfiy;
/*     */ import cn.ssms.model.Infor;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.PageHelper;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service("advertService")
/*     */ public class AdvertServiceImpl
/*     */   implements AdvertService
/*     */ {
/*     */   @Autowired
/*     */   private ClassfiyMapper classfiyMapper;
/*     */   @Autowired
/*     */   private AdvertMapper advertMapper;
/*     */   @Autowired
/*     */   private InforMapper inforMapper;
/*     */   
/*     */   public List<Classfiy> getList(Map<String, Object> map) {
/*  34 */     map.put("type", Integer.valueOf(1));
/*  35 */     return this.classfiyMapper.getClassfiyByType(map);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Classfiy> removeClassfiy(String idsStr) {
/*  41 */     List<Classfiy> classfiys = new LinkedList<>();
/*  42 */     String[] ids = idsStr.split(",");
/*  43 */     for (int i = 0; i < ids.length; i++) {
/*  44 */       Map<String, Object> params = new HashMap<>();
/*  45 */       params.put("classifyId", ids[i]);
/*  46 */       List<Infor> list = this.inforMapper.getInforByClassFiyId(params);
/*  47 */       if (list.size() <= 0) {
/*  48 */         Classfiy classfiy = new Classfiy();
/*  49 */         Integer id = Integer.valueOf(ids[i]);
/*  50 */         classfiy.setId(id);
/*  51 */         classfiy.setState(Integer.valueOf(3));
/*  52 */         int count = this.classfiyMapper.updClassfiy(classfiy);
/*  53 */         if (count < 1) {
/*  54 */           classfiy.setState(Integer.valueOf(0));
/*     */         }
/*  56 */         classfiys.add(classfiy);
/*     */       } 
/*     */     } 
/*  59 */     return classfiys;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Classfiy updClassfiy(Map<String, Object> map) {
/*  65 */     String id = (String)map.get("id");
/*  66 */     String name = (String)map.get("name");
/*  67 */     String remark = (String)map.get("remark");
/*  68 */     String sequence = (String)map.get("sequence");
/*  69 */     String type = (String)map.get("type");
/*  70 */     String state = (String)map.get("state");
/*     */     
/*  72 */     Classfiy classfiy = new Classfiy();
/*  73 */     classfiy.setId(Integer.valueOf(id));
/*  74 */     classfiy.setName(name);
/*  75 */     classfiy.setRemark(remark);
/*  76 */     classfiy.setSequence(Integer.valueOf(sequence));
/*  77 */     classfiy.setState(Integer.valueOf(state));
/*  78 */     classfiy.setType(Integer.valueOf(type));
/*     */     
/*  80 */     this.classfiyMapper.updClassfiy(classfiy);
/*     */     
/*  82 */     return classfiy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Classfiy addClassfiy(Map<String, Object> map) {
/*  88 */     String name = (String)map.get("name");
/*  89 */     String remark = (String)map.get("remark");
/*  90 */     String sequence = (String)map.get("sequence");
/*  91 */     String type = (String)map.get("type");
/*  92 */     String state = (String)map.get("state");
/*     */     
/*  94 */     Classfiy classfiy = new Classfiy();
/*  95 */     classfiy.setName(name);
/*  96 */     classfiy.setRemark(remark);
/*  97 */     classfiy.setSequence(Integer.valueOf(sequence));
/*  98 */     classfiy.setState(Integer.valueOf(state));
/*  99 */     classfiy.setType(Integer.valueOf(type));
/*     */     
/* 101 */     this.classfiyMapper.addClassfiy(classfiy);
/*     */     
/* 103 */     return classfiy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Classfiy getClassfiyById(String idStr) {
/* 113 */     Integer id = Integer.valueOf(idStr);
/* 114 */     Classfiy classfiy = this.classfiyMapper.getClassfiyById(id);
/*     */     
/* 116 */     return classfiy;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAdvertList(Map<String, Object> map) throws Exception {
/* 121 */     Map<String, Object> params = new HashMap<>();
/* 122 */     Integer page = Integer.valueOf(1);
/* 123 */     Integer pageSize = Contant.PAGESIZE;
/*     */ 
/*     */     
/* 126 */     String type = (String)map.get("type");
/* 127 */     String author = (String)map.get("author");
/* 128 */     String title = (String)map.get("title");
/*     */     
/* 130 */     String pageStr = (String)map.get("page");
/*     */     
/*     */     try {
/* 133 */       if (pageStr != null && !pageStr.equals("")) {
/* 134 */         page = Integer.valueOf(pageStr);
/*     */       }
/* 136 */       if (type != null && !type.equals("")) {
/* 137 */         params.put("type", Integer.valueOf(type));
/*     */       }
/* 139 */       if (author != null && !author.equals("")) {
/* 140 */         params.put("author", author);
/*     */       }
/* 142 */       if (title != null && !title.equals(""))
/*     */       {
/* 144 */         params.put("title", title);
/*     */       }
/*     */       
/* 147 */       params.put("beginNum", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 148 */       params.put("limitNum", pageSize);
/*     */     }
/* 150 */     catch (Exception e) {
/* 151 */       throw e;
/*     */     } 
/* 153 */     List<Advert> users = this.advertMapper.selectAdvertByParams(params);
/* 154 */     Integer count = this.advertMapper.countAdvertByParams(params);
/* 155 */     String path = "userList.html?page=";
/* 156 */     Gson gson = new Gson();
/* 157 */     PageHelper result = new PageHelper(count, pageSize, page, gson.toJson(users), path);
/* 158 */     return gson.toJson(result);
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
/*     */   public Advert addAdvert(Advert advert) {
/* 178 */     this.advertMapper.insert(advert);
/*     */     
/* 180 */     return advert;
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
/*     */   public Advert updAdvert(Advert advert) {
/* 201 */     this.advertMapper.updateByPrimaryKeySelective(advert);
/* 202 */     return advert;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Advert> removeAdvert(String idsStr) {
/* 207 */     List<Advert> adverts = new LinkedList<>();
/* 208 */     String[] ids = idsStr.split(",");
/* 209 */     for (int i = 0; i < ids.length; i++) {
/* 210 */       Advert advert = new Advert();
/* 211 */       Integer id = Integer.valueOf(ids[i]);
/* 212 */       advert.setId(id);
/* 213 */       advert.setStatus(Integer.valueOf(0));
/* 214 */       int count = this.advertMapper.updateByPrimaryKeySelective(advert);
/* 215 */       if (count < 1) {
/* 216 */         advert.setStatus(Integer.valueOf(1));
/*     */       }
/* 218 */       adverts.add(advert);
/*     */     } 
/* 220 */     return adverts;
/*     */   }
/*     */ 
/*     */   
/*     */   public Advert getAdvertById(String idStr) {
/* 225 */     Integer id = Integer.valueOf(idStr);
/* 226 */     return this.advertMapper.selectByPrimaryKey(id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String classfiyList(Map<String, Object> map) throws Exception {
/* 232 */     Map<String, Object> params = new HashMap<>();
/* 233 */     Integer page = Integer.valueOf(1);
/* 234 */     Integer pageSize = Contant.PAGESIZE;
/*     */ 
/*     */     
/* 237 */     String name = (String)map.get("name");
/* 238 */     String remark = (String)map.get("remark");
/* 239 */     String state = (String)map.get("state");
/*     */     
/* 241 */     String pageStr = (String)map.get("page");
/* 242 */     String pageSizeStr = (String)map.get("pageSize");
/* 243 */     System.out.println("分页-------------");
/*     */     try {
/* 245 */       if (name != null && !name.equals("")) {
/* 246 */         params.put("name", name);
/*     */       }
/* 248 */       if (remark != null && !remark.equals(""))
/*     */       {
/* 250 */         params.put("remark", remark); } 
/* 251 */       if (state != null && !state.equals("")) {
/* 252 */         Integer stateStr = Integer.valueOf(state);
/* 253 */         params.put("state", stateStr);
/*     */       } 
/* 255 */       if (pageStr != null && !pageStr.equals("")) {
/* 256 */         page = Integer.valueOf(pageStr);
/*     */       }
/* 258 */       if (pageSizeStr != null && !pageSizeStr.equals("")) {
/* 259 */         pageSize = Integer.valueOf(pageSizeStr);
/*     */       }
/* 261 */       params.put("type", Integer.valueOf(1));
/* 262 */       params.put("beginNum", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 263 */       params.put("limitNum", pageSize);
/*     */     }
/* 265 */     catch (Exception e) {
/* 266 */       throw e;
/*     */     } 
/* 268 */     List<Classfiy> classfiy = this.classfiyMapper.getClassfiyByTypeList(params);
/* 269 */     Integer count = this.classfiyMapper.getClassfiyByTypeCount(params);
/* 270 */     String path = "classfiyList.html?page=";
/* 271 */     Gson gson = new Gson();
/* 272 */     PageHelper result = new PageHelper(count, pageSize, page, gson.toJson(classfiy), path);
/* 273 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Classfiy> isReName(Map<String, Object> map) {
/* 278 */     return this.classfiyMapper.isReName(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Classfiy> isReSequene(Map<String, Object> map) {
/* 283 */     return this.classfiyMapper.isReSequene(map);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\AdvertServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */