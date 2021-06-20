/*     */ package cn.ssms.ueditor.hunter;
/*     */ 
/*     */ import com.baidu.ueditor.PathFormat;
/*     */ import com.baidu.ueditor.define.BaseState;
/*     */ import com.baidu.ueditor.define.MIMEType;
/*     */ import com.baidu.ueditor.define.MultiState;
/*     */ import com.baidu.ueditor.define.State;
/*     */ import com.baidu.ueditor.upload.StorageManager;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.InetAddress;
/*     */ import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageHunter
/*     */ {
/*  26 */   private String filename = null;
/*  27 */   private String savePath = null;
/*  28 */   private String rootPath = null;
/*  29 */   private List<String> allowTypes = null;
/*  30 */   private long maxSize = -1L;
/*     */   
/*  32 */   private List<String> filters = null;
/*     */ 
/*     */   
/*     */   public ImageHunter(Map<String, Object> conf) {
/*  36 */     this.filename = (String)conf.get("filename");
/*  37 */     this.savePath = (String)conf.get("savePath");
/*  38 */     this.rootPath = (String)conf.get("rootPath");
/*  39 */     this.maxSize = ((Long)conf.get("maxSize")).longValue();
/*  40 */     this.allowTypes = Arrays.asList((String[])conf.get("allowFiles"));
/*  41 */     this.filters = Arrays.asList((String[])conf.get("filter"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public State capture(String[] list) {
/*  47 */     MultiState state = new MultiState(true);
/*     */     
/*  49 */     for (String source : list) {
/*  50 */       state.addState(captureRemoteData(source));
/*     */     }
/*     */     
/*  53 */     return (State)state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public State captureRemoteData(String urlStr) {
/*  59 */     HttpURLConnection connection = null;
/*  60 */     URL url = null;
/*  61 */     String suffix = null;
/*     */     
/*     */     try {
/*  64 */       url = new URL(urlStr);
/*     */       
/*  66 */       if (!validHost(url.getHost())) {
/*  67 */         return (State)new BaseState(false, 201);
/*     */       }
/*     */       
/*  70 */       connection = (HttpURLConnection)url.openConnection();
/*     */       
/*  72 */       connection.setInstanceFollowRedirects(true);
/*  73 */       connection.setUseCaches(true);
/*     */       
/*  75 */       if (!validContentState(connection.getResponseCode())) {
/*  76 */         return (State)new BaseState(false, 202);
/*     */       }
/*     */       
/*  79 */       suffix = MIMEType.getSuffix(connection.getContentType());
/*     */       
/*  81 */       if (!validFileType(suffix)) {
/*  82 */         return (State)new BaseState(false, 8);
/*     */       }
/*     */       
/*  85 */       if (!validFileSize(connection.getContentLength())) {
/*  86 */         return (State)new BaseState(false, 1);
/*     */       }
/*     */       
/*  89 */       String savePath = getPath(this.savePath, this.filename, suffix);
/*  90 */       String physicalPath = this.rootPath + savePath;
/*     */       
/*  92 */       State state = StorageManager.saveFileByInputStream(connection.getInputStream(), physicalPath);
/*     */       
/*  94 */       if (state.isSuccess()) {
/*  95 */         state.putInfo("url", PathFormat.format(savePath));
/*  96 */         state.putInfo("source", urlStr);
/*     */       } 
/*     */       
/*  99 */       return state;
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       return (State)new BaseState(false, 203);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getPath(String savePath, String filename, String suffix) {
/* 109 */     return PathFormat.parse(savePath + suffix, filename);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean validHost(String hostname) {
/*     */     try {
/* 115 */       InetAddress ip = InetAddress.getByName(hostname);
/*     */       
/* 117 */       if (ip.isSiteLocalAddress()) {
/* 118 */         return false;
/*     */       }
/* 120 */     } catch (UnknownHostException e) {
/* 121 */       return false;
/*     */     } 
/*     */     
/* 124 */     return !this.filters.contains(hostname);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validContentState(int code) {
/* 130 */     return (200 == code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validFileType(String type) {
/* 136 */     return this.allowTypes.contains(type);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean validFileSize(int size) {
/* 141 */     return (size < this.maxSize);
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\hunter\ImageHunter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */