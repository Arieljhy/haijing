/*     */ package cn.ssms.xss;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletRequestWrapper;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class XssHttpServletRequestWrapper
/*     */   extends HttpServletRequestWrapper
/*     */ {
/*     */   HttpServletRequest originRequest;
/*  28 */   private static final HTMLFilter htmlFilter = new HTMLFilter();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private static final Map<String, Object> WhiteList = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  42 */     WhiteList.put("describe", "");
/*     */     
/*  44 */     WhiteList.put("mobileDescribe", "");
/*     */     
/*  46 */     WhiteList.put("describeText", "");
/*     */     
/*  48 */     WhiteList.put("uploadImg", "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public XssHttpServletRequestWrapper(HttpServletRequest request) {
/*  54 */     super(request);
/*  55 */     this.originRequest = request;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParameter(String parameter) {
/*  60 */     String value = super.getParameter(xssEncode(parameter));
/*  61 */     if (StringUtils.isNotBlank(value)) {
/*  62 */       value = WhiteList.containsKey(parameter) ? value : xssEncode(value);
/*     */     }
/*  64 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParameterValues(String parameter) {
/*  69 */     String[] values = super.getParameterValues(parameter);
/*  70 */     if (values == null || values.length == 0) {
/*  71 */       return null;
/*     */     }
/*     */     
/*  74 */     for (int i = 0; i < values.length; i++) {
/*  75 */       values[i] = WhiteList.containsKey(parameter) ? values[i] : xssEncode(values[i]);
/*     */     }
/*  77 */     return values;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String[]> getParameterMap() {
/*  82 */     Map<String, String[]> map = (Map)new LinkedHashMap<>();
/*  83 */     Map<String, String[]> parameters = super.getParameterMap();
/*  84 */     for (String key : parameters.keySet()) {
/*  85 */       String[] values = parameters.get(key);
/*  86 */       for (int i = 0; i < values.length; i++) {
/*  87 */         values[i] = xssEncode(values[i]);
/*     */       }
/*  89 */       map.put(key, values);
/*     */     } 
/*  91 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeader(String name) {
/*  96 */     String value = super.getHeader(xssEncode(name));
/*  97 */     if (StringUtils.isNotBlank(value)) {
/*  98 */       value = xssEncode(value);
/*     */     }
/* 100 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String xssEncode(String input) {
/* 111 */     return htmlFilter.filter(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpServletRequest getOrgRequest() {
/* 119 */     return this.originRequest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
/* 126 */     if (request instanceof XssHttpServletRequestWrapper) {
/* 127 */       return ((XssHttpServletRequestWrapper)request).getOrgRequest();
/*     */     }
/*     */     
/* 130 */     return request;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\xss\XssHttpServletRequestWrapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */