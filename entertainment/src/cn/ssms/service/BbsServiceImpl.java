/*     */ package cn.ssms.service;
/*     */ 
/*     */ import cn.ssms.dao.BbsMapper;
/*     */ import cn.ssms.dao.ClassfiyMapper;
/*     */ import cn.ssms.dao.InforMapper;
/*     */ import cn.ssms.model.Bbs;
/*     */ import cn.ssms.model.Classfiy;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.DataAccessException;
/*     */ import cn.ssms.util.PageHelper;
/*     */ import com.google.gson.Gson;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("BbsService")
/*     */ public class BbsServiceImpl
/*     */   implements BbsService {
/*     */   @Autowired
/*     */   private BbsMapper bbsMapper;
/*     */   @Autowired
/*     */   private InforMapper inforMapper;
/*     */   @Autowired
/*     */   private ClassfiyMapper classfiyMapper;
/*     */   
/*     */   public String getBbsList(Map<String, Object> map) {
/*     */     try {
/*  31 */       Map<String, Object> params = new HashMap<>();
/*  32 */       String pageStr = (String)map.get("page");
/*  33 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/*  34 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/*  35 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  36 */       params.put("pageSize", pageSize);
/*  37 */       if (map.get("classifyId") != null && !map.get("classifyId").equals("")) {
/*  38 */         params.put("classifyId", map.get("classifyId"));
/*     */       }
/*  40 */       if (map.get("addName") != null && !map.get("addName").equals("")) {
/*  41 */         params.put("addName", map.get("addName"));
/*     */       }
/*  43 */       if (map.get("startDate") != null && !map.get("startDate").equals("")) {
/*  44 */         params.put("startDate", map.get("startDate"));
/*     */       }
/*  46 */       if (map.get("endDate") != null && !map.get("endDate").equals("")) {
/*  47 */         params.put("endDate", map.get("endDate"));
/*     */       }
/*  49 */       if (map.get("title") != null && !map.get("title").equals("")) {
/*  50 */         params.put("title", map.get("title"));
/*     */       }
/*  52 */       Gson gson = new Gson();
/*  53 */       List<Bbs> bbsList = this.bbsMapper.getBbsList(params);
/*  54 */       Integer totalCount = this.bbsMapper.getBbsListTotal(params);
/*  55 */       String path = "getBbsList.html?page=";
/*  56 */       PageHelper result = new PageHelper(totalCount, pageSize, page, gson.toJson(bbsList), path);
/*  57 */       return gson.toJson(result);
/*  58 */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String selectReply(Map<String, Object> map) throws DataAccessException {
/*     */     try {
/*  69 */       String idStr = (String)map.get("id");
/*  70 */       Integer id = Integer.valueOf(idStr);
/*  71 */       Map<String, Object> params = new HashMap<>();
/*  72 */       if (map.get("addPerson") != null && !map.get("addPerson").equals("")) {
/*  73 */         params.put("addPerson", Integer.valueOf((String)map.get("addPerson")));
/*     */       }
/*  75 */       String pageStr = (String)map.get("page");
/*  76 */       Integer page = Integer.valueOf((pageStr == null) ? 1 : Integer.valueOf(pageStr).intValue());
/*  77 */       Integer pageSize = (map.get("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(map.get("pageSize").toString());
/*  78 */       params.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  79 */       params.put("pageSize", pageSize);
/*  80 */       params.put("id", id);
/*  81 */       Gson gson = new Gson();
/*  82 */       List<Bbs> bbsList = this.bbsMapper.selectReply(params);
/*  83 */       Integer totalCount = this.bbsMapper.selectReplyCount(params);
/*  84 */       String path = "getBbsReply.html?page=";
/*  85 */       PageHelper result = new PageHelper(totalCount, pageSize, page, gson.toJson(bbsList), path);
/*  86 */       return gson.toJson(result);
/*  87 */     } catch (Exception e) {
/*  88 */       e.printStackTrace();
/*  89 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String findBbsOne(Map<String, Object> map) {
/*  96 */     String idStr = (String)map.get("id");
/*  97 */     Bbs bbs = this.bbsMapper.selectByPrimaryKey(Integer.valueOf(idStr));
/*  98 */     Map<String, Object> result = new HashMap<>();
/*     */     
/* 100 */     result.put("flag", Boolean.valueOf(true));
/* 101 */     result.put("message", "获得数据成功");
/* 102 */     return (new Gson()).toJson(bbs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String updBbsDetail(Map<String, Object> map) throws DataAccessException {
/* 108 */     String idStr = (String)map.get("id");
/* 109 */     String classifyId = (String)map.get("classifyId");
/* 110 */     String title = (String)map.get("title");
/* 111 */     String content = (String)map.get("content");
/* 112 */     String imageList = (String)map.get("imageList");
/* 113 */     Bbs bbs = new Bbs();
/* 114 */     if (classifyId != null && !classifyId.equals("")) {
/* 115 */       bbs.setClassifyId(Integer.valueOf(classifyId));
/*     */     }
/* 117 */     bbs.setId(Integer.valueOf(idStr));
/*     */     
/* 119 */     bbs.setTitle(title);
/* 120 */     bbs.setContent(content);
/* 121 */     bbs.setImageList(imageList);
/* 122 */     this.bbsMapper.updateByPrimaryKeySelective(bbs);
/* 123 */     Map<String, Object> result = new HashMap<>();
/* 124 */     result.put("flag", Boolean.valueOf(true));
/* 125 */     result.put("message", "修改成功");
/* 126 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public String removeBbs(Map<String, Object> map) throws DataAccessException {
/* 131 */     String idsStr = (String)map.get("ids");
/* 132 */     String flag = (String)map.get("flag");
/* 133 */     System.out.println(idsStr);
/* 134 */     String[] ids = idsStr.split(",");
/* 135 */     for (int i = 0; i < ids.length; i++) {
/*     */       
/* 137 */       Bbs bbs = new Bbs();
/* 138 */       bbs.setId(Integer.valueOf(ids[i]));
/* 139 */       bbs.setState("0");
/* 140 */       bbs.setDelDate("1");
/* 141 */       this.bbsMapper.updateById(bbs);
/*     */ 
/*     */       
/* 144 */       if ("1".equals(flag)) {
/* 145 */         Bbs b1 = this.bbsMapper.selectByPrimaryKey(Integer.valueOf(ids[i]));
/* 146 */         int bbsId = b1.getBbsId().intValue();
/*     */ 
/*     */         
/* 149 */         Bbs b2 = this.bbsMapper.selectByPrimaryKey(Integer.valueOf(bbsId));
/* 150 */         int replyCount = b2.getReplyCount().intValue();
/* 151 */         b2.setReplyCount(Integer.valueOf(replyCount - 1));
/* 152 */         this.bbsMapper.updateById(b2);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 157 */     Map<String, Object> result = new HashMap<>();
/* 158 */     result.put("flag", Boolean.valueOf(true));
/* 159 */     result.put("msg", "删除成功");
/* 160 */     return (new Gson()).toJson(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Classfiy> getList(Map<String, Object> map) {
/* 166 */     map.put("type", Integer.valueOf(2));
/* 167 */     return this.classfiyMapper.getClassfiyByType(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public String classfiyList(Map<String, Object> map) throws Exception {
/* 172 */     Map<String, Object> params = new HashMap<>();
/* 173 */     Integer page = Integer.valueOf(1);
/* 174 */     Integer pageSize = Contant.PAGESIZE;
/*     */ 
/*     */     
/* 177 */     String name = (String)map.get("name");
/* 178 */     String remark = (String)map.get("remark");
/* 179 */     String state = (String)map.get("state");
/*     */     
/* 181 */     String pageStr = (String)map.get("page");
/* 182 */     String pageSizeStr = (String)map.get("pageSize");
/* 183 */     System.out.println("分页-------------");
/*     */     try {
/* 185 */       if (name != null && !name.equals("")) {
/* 186 */         params.put("name", name);
/*     */       }
/* 188 */       if (remark != null && !remark.equals(""))
/*     */       {
/* 190 */         params.put("remark", remark); } 
/* 191 */       if (state != null && !state.equals("")) {
/* 192 */         Integer stateStr = Integer.valueOf(state);
/* 193 */         params.put("state", stateStr);
/*     */       } 
/* 195 */       if (pageStr != null && !pageStr.equals("")) {
/* 196 */         page = Integer.valueOf(pageStr);
/*     */       }
/* 198 */       if (pageSizeStr != null && !pageSizeStr.equals("")) {
/* 199 */         pageSize = Integer.valueOf(pageSizeStr);
/*     */       }
/* 201 */       params.put("type", Integer.valueOf(2));
/* 202 */       params.put("beginNum", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/* 203 */       params.put("limitNum", pageSize);
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       throw e;
/*     */     } 
/* 208 */     List<Classfiy> classfiy = this.classfiyMapper.getClassfiyByTypeList(params);
/* 209 */     Integer count = this.classfiyMapper.getClassfiyByTypeCount(params);
/* 210 */     String path = "classfiyList.html?page=";
/* 211 */     Gson gson = new Gson();
/* 212 */     PageHelper result = new PageHelper(count, pageSize, page, gson.toJson(classfiy), path);
/* 213 */     return gson.toJson(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Classfiy> isReName(Map<String, Object> map) {
/* 218 */     return this.classfiyMapper.isReName(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Bbs> getTbbsByClassFiyId(Map<String, Object> map) {
/* 223 */     return this.bbsMapper.getTbbsByClassFiyId(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Classfiy> removeClassfiy(String idsStr) {
/* 228 */     List<Classfiy> classfiys = new LinkedList<>();
/* 229 */     String[] ids = idsStr.split(",");
/* 230 */     for (int i = 0; i < ids.length; i++) {
/* 231 */       Map<String, Object> params = new HashMap<>();
/* 232 */       params.put("classifyId", ids[i]);
/* 233 */       List<Bbs> list = this.bbsMapper.getTbbsByClassFiyId(params);
/* 234 */       if (list.size() <= 0) {
/* 235 */         Classfiy classfiy = new Classfiy();
/* 236 */         Integer id = Integer.valueOf(ids[i]);
/* 237 */         classfiy.setId(id);
/* 238 */         classfiy.setState(Integer.valueOf(3));
/* 239 */         int count = this.classfiyMapper.updClassfiy(classfiy);
/* 240 */         if (count < 1) {
/* 241 */           classfiy.setState(Integer.valueOf(0));
/*     */         }
/* 243 */         classfiys.add(classfiy);
/*     */       } 
/*     */     } 
/* 246 */     return classfiys;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\BbsServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */