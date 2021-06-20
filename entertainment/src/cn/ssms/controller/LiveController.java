/*     */ package cn.ssms.controller;
/*     */ 
/*     */ import cn.ssms.model.Live;
/*     */ import cn.ssms.service.LiveService;
/*     */ import cn.ssms.util.Contant;
/*     */ import cn.ssms.util.GetRequestParam;
/*     */ import cn.ssms.util.tokenUtil.StringUtil;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
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
/*     */ @RequestMapping({"live"})
/*     */ public class LiveController
/*     */ {
/*     */   @Autowired
/*     */   private LiveService liveService;
/*     */   
/*     */   @RequestMapping({"/liveList"})
/*     */   public String liveList(HttpServletRequest request, Model model) {
/*  46 */     return "live/liveList";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getLiveList"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getLiveList(HttpServletRequest request) {
/*  58 */     Integer page = Integer.valueOf((request.getParameter("page") == null) ? 1 : Integer.valueOf(request.getParameter("page")).intValue());
/*  59 */     Integer pageSize = (request.getParameter("pageSize") == null) ? Contant.PAGESIZE : Integer.valueOf(request.getParameter("pageSize"));
/*  60 */     Map<String, Object> map = GetRequestParam.setMap(request);
/*  61 */     map.put("index", page);
/*  62 */     map.put("page", Integer.valueOf((page.intValue() - 1) * pageSize.intValue()));
/*  63 */     map.put("pageSize", pageSize);
/*  64 */     String result = this.liveService.getLiveList(map);
/*  65 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/removeLive"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String removeLive(HttpServletRequest request) {
/*  76 */     Map<String, Object> params = GetRequestParam.setMap(request);
/*  77 */     return (new Gson()).toJson(this.liveService.removeById(params));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/liveInfo"})
/*     */   public String liveInfo(Model model) {
/*  88 */     return "live/liveInfo";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/startLive"})
/*     */   public String startLive(Model model) {
/*  98 */     String s = StringUtil.readProperties("live.location");
/*  99 */     String s1 = StringUtil.readProperties("play.location");
/* 100 */     model.addAttribute("liveLocation", s);
/* 101 */     model.addAttribute("playLocation", s1);
/* 102 */     return "live/startLive";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/recordLive"})
/*     */   public String recordLive(Model model) {
/* 112 */     String s = StringUtil.readProperties("record.location");
/* 113 */     model.addAttribute("recordLocation", s);
/* 114 */     return "live/recordLive";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getLiveInfo"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String getLiveInfo(String id) {
/* 125 */     Live live = null;
/* 126 */     if (StringUtils.isNotEmpty(id)) {
/* 127 */       live = this.liveService.getLive(id);
/*     */     }
/* 129 */     return (new Gson()).toJson(live);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/saveOrUpdateLive"}, produces = {"application/json;charset=UTF-8"})
/*     */   @ResponseBody
/*     */   public String saveOrUpdateLive(HttpServletRequest request) throws ParseException {
/* 140 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 141 */     Live live = new Live();
/* 142 */     live.setId((params.get("id") != null) ? Integer.valueOf(Integer.parseInt(params.get("id").toString())) : null);
/* 143 */     live.setName(params.get("name").toString());
/* 144 */     live.setCode(params.get("code").toString());
/* 145 */     live.setTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(params.get("time").toString()));
/* 146 */     live.setPerson(params.get("person").toString());
/* 147 */     live.setRecord(params.get("record").toString());
/*     */     
/* 149 */     Map<String, Object> map = this.liveService.saveOrUpdateLive(live);
/* 150 */     return (new Gson()).toJson(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"publish"}, method = {RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public String publish(HttpServletRequest request) {
/* 162 */     System.out.println("publish");
/* 163 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 164 */     String app = params.get("app").toString();
/* 165 */     String code = params.get("name").toString();
/* 166 */     Live live = this.liveService.getLiveByCode(code);
/* 167 */     if ("myapp".equals(app)) {
/* 168 */       live.setState("2");
/* 169 */       live.setId(live.getId());
/* 170 */       this.liveService.saveOrUpdateLive(live);
/*     */     } 
/* 172 */     return "publish";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"publishDone"}, method = {RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public String publishDone(HttpServletRequest request) {
/* 184 */     System.out.println("publishDone");
/* 185 */     Map<String, Object> params = GetRequestParam.setMap(request);
/* 186 */     String app = params.get("app").toString();
/* 187 */     String code = params.get("name").toString();
/* 188 */     Live live = this.liveService.getLiveByCode(code);
/*     */     
/* 190 */     if ("myapp".equals(app)) {
/* 191 */       live.setState("4");
/* 192 */       live.setId(live.getId());
/* 193 */       this.liveService.saveOrUpdateLive(live);
/* 194 */       transcode(code, live);
/*     */     } 
/*     */     
/* 197 */     return "publishDone";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void transcode(final String code, final Live live) {
/* 204 */     final String stream = StringUtil.readProperties("stream.location");
/*     */     
/* 206 */     (new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/* 209 */             String command = "cmd /c start /b %sffmpeg\\bin\\ffmpeg.exe -i %sffmpeg\\bin\\temp\\%s.flv  -vcodec copy -acodec copy %srecord\\%s.mp4";
/*     */             
/* 211 */              command = String.format(command, stream, stream, code, stream, code);
/* 212 */             System.out.println(command);
/* 213 */             Process process = null;
/*     */             try {
/* 215 */               process = Runtime.getRuntime().exec(command);
/* 216 */               int i = 0;
/*     */               try {
/* 218 */                 i = process.waitFor();
/* 219 */               } catch (InterruptedException e) {
/* 220 */                 throw new IOException();
/*     */               } 
/* 222 */               if (i == 0) {
/* 223 */                 live.setState("3");
/* 224 */                 live.setId(live.getId());
/* 225 */                 LiveController.this.liveService.saveOrUpdateLive(live);
/*     */               } else {
/* 227 */                 live.setState("5");
/* 228 */                 live.setId(live.getId());
/* 229 */                 LiveController.this.liveService.saveOrUpdateLive(live);
/*     */               }
/*     */             
/* 232 */             } catch (IOException e) {
/* 233 */               live.setState("5");
/* 234 */               live.setId(live.getId());
/* 235 */               LiveController.this.liveService.saveOrUpdateLive(live);
/*     */             } 
/* 237 */             System.out.println("编码结束");
/*     */           }
/*     */         })).start();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException {
/* 244 */     Process process = Runtime.getRuntime().exec("cmd /c start E:\\tools\\nginx-flv\\objs\\ffmpeg\\bin\\ffmpeg.exe -i E:\\tools\\nginx-flv\\objs\\ffmpeg\\bin\\temp\\XinWenZhiBoJianShiXiYanXiZhiBo.flv -vcodec libx264 -acodec aac -y -f flv  E:\\tools\\nginx-flv\\objs\\record\\XinWenZhiBoJianShiXiYanXiZhiBo.flv");
/* 245 */     InputStream is = process.getInputStream();
/* 246 */     InputStreamReader isr = new InputStreamReader(is);
/* 247 */     BufferedReader br = new BufferedReader(isr);
/* 248 */     String content = br.readLine();
/* 249 */     while (content != null) {
/* 250 */       System.out.println(content);
/* 251 */       content = br.readLine();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\controller\LiveController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */