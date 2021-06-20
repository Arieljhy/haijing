/*     */ package cn.ssms.ueditor;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
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
/*     */ public final class ConfigManager
/*     */ {
/*     */   private final String rootPath;
/*     */   private final String originalPath;
/*     */   private static final String configFileName = "config.json";
/*  29 */   private String parentPath = null;
/*     */   
/*  31 */   private JSONObject jsonConfig = null;
/*     */ 
/*     */   
/*     */   private static final String SCRAWL_FILE_NAME = "scrawl";
/*     */ 
/*     */   
/*     */   private static final String REMOTE_FILE_NAME = "remote";
/*     */ 
/*     */ 
/*     */   
/*     */   private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {
/*  42 */     rootPath = rootPath.replace("\\", "/");
/*     */     
/*  44 */     this.rootPath = rootPath;
/*     */ 
/*     */     
/*  47 */     if (contextPath.length() > 0) {
/*  48 */       this.originalPath = this.rootPath + uri.substring(contextPath.length());
/*     */     } else {
/*  50 */       this.originalPath = this.rootPath + uri;
/*     */     } 
/*     */     
/*  53 */     initEnv();
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
/*     */   public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
/*     */     try {
/*  67 */       return new ConfigManager(rootPath, contextPath, uri);
/*  68 */     } catch (Exception e) {
/*  69 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean valid() {
/*  76 */     return (this.jsonConfig != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject getAllConfig() {
/*  81 */     return this.jsonConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getConfig(int type) {
/*  87 */     Map<String, Object> conf = new HashMap<>();
/*  88 */     String savePath = null;
/*     */     
/*  90 */     switch (type) {
/*     */       
/*     */       case 4:
/*  93 */         conf.put("isBase64", "false");
/*  94 */         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("fileMaxSize")));
/*  95 */         conf.put("allowFiles", getArray("fileAllowFiles"));
/*  96 */         conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
/*  97 */         savePath = this.jsonConfig.getString("filePathFormat");
/*     */         break;
/*     */       
/*     */       case 1:
/* 101 */         conf.put("isBase64", "false");
/* 102 */         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("imageMaxSize")));
/* 103 */         conf.put("allowFiles", getArray("imageAllowFiles"));
/* 104 */         conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
/* 105 */         savePath = this.jsonConfig.getString("imagePathFormat");
/*     */         break;
/*     */       
/*     */       case 3:
/* 109 */         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("videoMaxSize")));
/* 110 */         conf.put("allowFiles", getArray("videoAllowFiles"));
/* 111 */         conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
/* 112 */         savePath = this.jsonConfig.getString("videoPathFormat");
/*     */         break;
/*     */       
/*     */       case 2:
/* 116 */         conf.put("filename", "scrawl");
/* 117 */         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("scrawlMaxSize")));
/* 118 */         conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
/* 119 */         conf.put("isBase64", "true");
/* 120 */         savePath = this.jsonConfig.getString("scrawlPathFormat");
/*     */         break;
/*     */       
/*     */       case 5:
/* 124 */         conf.put("filename", "remote");
/* 125 */         conf.put("filter", getArray("catcherLocalDomain"));
/* 126 */         conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("catcherMaxSize")));
/* 127 */         conf.put("allowFiles", getArray("catcherAllowFiles"));
/* 128 */         conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
/* 129 */         savePath = this.jsonConfig.getString("catcherPathFormat");
/*     */         break;
/*     */       
/*     */       case 7:
/* 133 */         conf.put("allowFiles", getArray("imageManagerAllowFiles"));
/* 134 */         conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
/* 135 */         conf.put("count", Integer.valueOf(this.jsonConfig.getInt("imageManagerListSize")));
/*     */         break;
/*     */       
/*     */       case 6:
/* 139 */         conf.put("allowFiles", getArray("fileManagerAllowFiles"));
/* 140 */         conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
/* 141 */         conf.put("count", Integer.valueOf(this.jsonConfig.getInt("fileManagerListSize")));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 146 */     conf.put("savePath", savePath);
/* 147 */     conf.put("rootPath", this.rootPath);
/*     */     
/* 149 */     return conf;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initEnv() throws FileNotFoundException, IOException {
/* 155 */     File file = new File(this.originalPath);
/*     */     
/* 157 */     if (!file.isAbsolute()) {
/* 158 */       file = new File(file.getAbsolutePath());
/*     */     }
/*     */     
/* 161 */     this.parentPath = file.getParent();
/*     */     
/* 163 */     String configContent = readFile(getConfigPath());
/*     */     
/*     */     try {
/* 166 */       JSONObject jsonConfig = new JSONObject(configContent);
/* 167 */       this.jsonConfig = jsonConfig;
/* 168 */     } catch (Exception e) {
/* 169 */       this.jsonConfig = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getConfigPath() {
/* 179 */     String path = this.rootPath + File.separator + "media/editor" + File.separator + "jsp" + File.separator + "config.json";
/*     */ 
/*     */ 
/*     */     
/* 183 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] getArray(String key) {
/* 189 */     JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
/* 190 */     String[] result = new String[jsonArray.length()];
/*     */     
/* 192 */     for (int i = 0, len = jsonArray.length(); i < len; i++) {
/* 193 */       result[i] = jsonArray.getString(i);
/*     */     }
/*     */     
/* 196 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String readFile(String path) throws IOException {
/* 202 */     StringBuilder builder = new StringBuilder();
/*     */ 
/*     */     
/*     */     try {
/* 206 */       InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
/* 207 */       BufferedReader bfReader = new BufferedReader(reader);
/*     */       
/* 209 */       String tmpContent = null;
/*     */       
/* 211 */       while ((tmpContent = bfReader.readLine()) != null) {
/* 212 */         builder.append(tmpContent);
/*     */       }
/*     */       
/* 215 */       bfReader.close();
/*     */     }
/* 217 */     catch (UnsupportedEncodingException e) {}
/*     */ 
/*     */ 
/*     */     
/* 221 */     return filter(builder.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String filter(String input) {
/* 228 */     return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\ConfigManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */