/*     */ package cn.ssms.xss;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public final class HTMLFilter
/*     */ {
/*     */   private static final int REGEX_FLAGS_SI = 34;
/*  47 */   private static final Pattern P_COMMENTS = Pattern.compile("<!--(.*?)-->", 32);
/*  48 */   private static final Pattern P_COMMENT = Pattern.compile("^!--(.*)--$", 34);
/*  49 */   private static final Pattern P_TAGS = Pattern.compile("<(.*?)>", 32);
/*  50 */   private static final Pattern P_END_TAG = Pattern.compile("^/([a-z0-9]+)", 34);
/*  51 */   private static final Pattern P_START_TAG = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", 34);
/*  52 */   private static final Pattern P_QUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", 34);
/*  53 */   private static final Pattern P_UNQUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", 34);
/*  54 */   private static final Pattern P_PROTOCOL = Pattern.compile("^([^:]+):", 34);
/*  55 */   private static final Pattern P_ENTITY = Pattern.compile("&#(\\d+);?");
/*  56 */   private static final Pattern P_ENTITY_UNICODE = Pattern.compile("&#x([0-9a-f]+);?");
/*  57 */   private static final Pattern P_ENCODE = Pattern.compile("%([0-9a-f]{2});?");
/*  58 */   private static final Pattern P_VALID_ENTITIES = Pattern.compile("&([^&;]*)(?=(;|&|$))");
/*  59 */   private static final Pattern P_VALID_QUOTES = Pattern.compile("(>|^)([^<]+?)(<|$)", 32);
/*  60 */   private static final Pattern P_END_ARROW = Pattern.compile("^>");
/*  61 */   private static final Pattern P_BODY_TO_END = Pattern.compile("<([^>]*?)(?=<|$)");
/*  62 */   private static final Pattern P_XML_CONTENT = Pattern.compile("(^|>)([^<]*?)(?=>)");
/*  63 */   private static final Pattern P_STRAY_LEFT_ARROW = Pattern.compile("<([^>]*?)(?=<|$)");
/*  64 */   private static final Pattern P_STRAY_RIGHT_ARROW = Pattern.compile("(^|>)([^<]*?)(?=>)");
/*  65 */   private static final Pattern P_AMP = Pattern.compile("&");
/*  66 */   private static final Pattern P_QUOTE = Pattern.compile("\"");
/*  67 */   private static final Pattern P_LEFT_ARROW = Pattern.compile("<");
/*  68 */   private static final Pattern P_RIGHT_ARROW = Pattern.compile(">");
/*  69 */   private static final Pattern P_BOTH_ARROWS = Pattern.compile("<>");
/*     */ 
/*     */   
/*  72 */   private static final ConcurrentMap<String, Pattern> P_REMOVE_PAIR_BLANKS = new ConcurrentHashMap<>();
/*  73 */   private static final ConcurrentMap<String, Pattern> P_REMOVE_SELF_BLANKS = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*     */   private final Map<String, List<String>> vAllowed;
/*     */   
/*  78 */   private final Map<String, Integer> vTagCounts = new HashMap<>();
/*     */ 
/*     */   
/*     */   private final String[] vSelfClosingTags;
/*     */ 
/*     */   
/*     */   private final String[] vNeedClosingTags;
/*     */ 
/*     */   
/*     */   private final String[] vDisallowed;
/*     */ 
/*     */   
/*     */   private final String[] vProtocolAtts;
/*     */ 
/*     */   
/*     */   private final String[] vAllowedProtocols;
/*     */   
/*     */   private final String[] vRemoveBlanks;
/*     */   
/*     */   private final String[] vAllowedEntities;
/*     */   
/*     */   private final boolean stripComment;
/*     */   
/*     */   private final boolean encodeQuotes;
/*     */   
/*     */   private boolean vDebug = false;
/*     */   
/*     */   private final boolean alwaysMakeTags;
/*     */ 
/*     */   
/*     */   public HTMLFilter() {
/* 109 */     this.vAllowed = new HashMap<>();
/*     */     
/* 111 */     ArrayList<String> a_atts = new ArrayList<>();
/* 112 */     a_atts.add("href");
/* 113 */     a_atts.add("target");
/* 114 */     this.vAllowed.put("a", a_atts);
/*     */     
/* 116 */     ArrayList<String> img_atts = new ArrayList<>();
/* 117 */     img_atts.add("src");
/* 118 */     img_atts.add("width");
/* 119 */     img_atts.add("height");
/* 120 */     img_atts.add("alt");
/* 121 */     this.vAllowed.put("img", img_atts);
/*     */     
/* 123 */     ArrayList<String> no_atts = new ArrayList<>();
/* 124 */     this.vAllowed.put("b", no_atts);
/* 125 */     this.vAllowed.put("strong", no_atts);
/* 126 */     this.vAllowed.put("i", no_atts);
/* 127 */     this.vAllowed.put("em", no_atts);
/*     */     
/* 129 */     this.vSelfClosingTags = new String[] { "img" };
/* 130 */     this.vNeedClosingTags = new String[] { "a", "b", "strong", "i", "em" };
/* 131 */     this.vDisallowed = new String[0];
/* 132 */     this.vAllowedProtocols = new String[] { "http", "mailto", "https" };
/* 133 */     this.vProtocolAtts = new String[] { "src", "href" };
/* 134 */     this.vRemoveBlanks = new String[] { "a", "b", "strong", "i", "em" };
/* 135 */     this.vAllowedEntities = new String[] { "amp", "gt", "lt", "quot" };
/* 136 */     this.stripComment = true;
/* 137 */     this.encodeQuotes = true;
/* 138 */     this.alwaysMakeTags = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HTMLFilter(boolean debug) {
/* 146 */     this();
/* 147 */     this.vDebug = debug;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HTMLFilter(Map<String, Object> conf) {
/* 157 */     assert conf.containsKey("vAllowed") : "configuration requires vAllowed";
/* 158 */     assert conf.containsKey("vSelfClosingTags") : "configuration requires vSelfClosingTags";
/* 159 */     assert conf.containsKey("vNeedClosingTags") : "configuration requires vNeedClosingTags";
/* 160 */     assert conf.containsKey("vDisallowed") : "configuration requires vDisallowed";
/* 161 */     assert conf.containsKey("vAllowedProtocols") : "configuration requires vAllowedProtocols";
/* 162 */     assert conf.containsKey("vProtocolAtts") : "configuration requires vProtocolAtts";
/* 163 */     assert conf.containsKey("vRemoveBlanks") : "configuration requires vRemoveBlanks";
/* 164 */     assert conf.containsKey("vAllowedEntities") : "configuration requires vAllowedEntities";
/*     */     
/* 166 */     this.vAllowed = Collections.unmodifiableMap((HashMap)conf.get("vAllowed"));
/* 167 */     this.vSelfClosingTags = (String[])conf.get("vSelfClosingTags");
/* 168 */     this.vNeedClosingTags = (String[])conf.get("vNeedClosingTags");
/* 169 */     this.vDisallowed = (String[])conf.get("vDisallowed");
/* 170 */     this.vAllowedProtocols = (String[])conf.get("vAllowedProtocols");
/* 171 */     this.vProtocolAtts = (String[])conf.get("vProtocolAtts");
/* 172 */     this.vRemoveBlanks = (String[])conf.get("vRemoveBlanks");
/* 173 */     this.vAllowedEntities = (String[])conf.get("vAllowedEntities");
/* 174 */     this.stripComment = conf.containsKey("stripComment") ? ((Boolean)conf.get("stripComment")).booleanValue() : true;
/* 175 */     this.encodeQuotes = conf.containsKey("encodeQuotes") ? ((Boolean)conf.get("encodeQuotes")).booleanValue() : true;
/* 176 */     this.alwaysMakeTags = conf.containsKey("alwaysMakeTags") ? ((Boolean)conf.get("alwaysMakeTags")).booleanValue() : true;
/*     */   }
/*     */   
/*     */   private void reset() {
/* 180 */     this.vTagCounts.clear();
/*     */   }
/*     */   
/*     */   private void debug(String msg) {
/* 184 */     if (this.vDebug) {
/* 185 */       Logger.getAnonymousLogger().info(msg);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String chr(int decimal) {
/* 192 */     return String.valueOf((char)decimal);
/*     */   }
/*     */   
/*     */   public static String htmlSpecialChars(String s) {
/* 196 */     String result = s;
/* 197 */     result = regexReplace(P_AMP, "&amp;", result);
/* 198 */     result = regexReplace(P_QUOTE, "&quot;", result);
/* 199 */     result = regexReplace(P_LEFT_ARROW, "&lt;", result);
/* 200 */     result = regexReplace(P_RIGHT_ARROW, "&gt;", result);
/* 201 */     return result;
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
/*     */   public String filter(String input) {
/* 213 */     reset();
/* 214 */     String s = input;
/*     */     
/* 216 */     debug("************************************************");
/* 217 */     debug("              INPUT: " + input);
/*     */     
/* 219 */     s = escapeComments(s);
/* 220 */     debug("     escapeComments: " + s);
/*     */     
/* 222 */     s = balanceHTML(s);
/* 223 */     debug("        balanceHTML: " + s);
/*     */     
/* 225 */     s = checkTags(s);
/* 226 */     debug("          checkTags: " + s);
/*     */     
/* 228 */     s = processRemoveBlanks(s);
/* 229 */     debug("processRemoveBlanks: " + s);
/*     */     
/* 231 */     s = validateEntities(s);
/* 232 */     debug("    validateEntites: " + s);
/*     */     
/* 234 */     debug("************************************************\n\n");
/* 235 */     return s;
/*     */   }
/*     */   
/*     */   public boolean isAlwaysMakeTags() {
/* 239 */     return this.alwaysMakeTags;
/*     */   }
/*     */   
/*     */   public boolean isStripComments() {
/* 243 */     return this.stripComment;
/*     */   }
/*     */   
/*     */   private String escapeComments(String s) {
/* 247 */     Matcher m = P_COMMENTS.matcher(s);
/* 248 */     StringBuffer buf = new StringBuffer();
/* 249 */     if (m.find()) {
/* 250 */       String match = m.group(1);
/* 251 */       m.appendReplacement(buf, Matcher.quoteReplacement("<!--" + htmlSpecialChars(match) + "-->"));
/*     */     } 
/* 253 */     m.appendTail(buf);
/*     */     
/* 255 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private String balanceHTML(String s) {
/* 259 */     if (this.alwaysMakeTags) {
/*     */ 
/*     */ 
/*     */       
/* 263 */       s = regexReplace(P_END_ARROW, "", s);
/* 264 */       s = regexReplace(P_BODY_TO_END, "<$1>", s);
/* 265 */       s = regexReplace(P_XML_CONTENT, "$1<$2", s);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 271 */       s = regexReplace(P_STRAY_LEFT_ARROW, "&lt;$1", s);
/* 272 */       s = regexReplace(P_STRAY_RIGHT_ARROW, "$1$2&gt;<", s);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       s = regexReplace(P_BOTH_ARROWS, "", s);
/*     */     } 
/*     */     
/* 282 */     return s;
/*     */   }
/*     */   
/*     */   private String checkTags(String s) {
/* 286 */     Matcher m = P_TAGS.matcher(s);
/*     */     
/* 288 */     StringBuffer buf = new StringBuffer();
/* 289 */     while (m.find()) {
/* 290 */       String replaceStr = m.group(1);
/* 291 */       replaceStr = processTag(replaceStr);
/* 292 */       m.appendReplacement(buf, Matcher.quoteReplacement(replaceStr));
/*     */     } 
/* 294 */     m.appendTail(buf);
/*     */     
/* 296 */     s = buf.toString();
/*     */ 
/*     */ 
/*     */     
/* 300 */     for (String key : this.vTagCounts.keySet()) {
/* 301 */       for (int ii = 0; ii < ((Integer)this.vTagCounts.get(key)).intValue(); ii++) {
/* 302 */         s = s + "</" + key + ">";
/*     */       }
/*     */     } 
/*     */     
/* 306 */     return s;
/*     */   }
/*     */   
/*     */   private String processRemoveBlanks(String s) {
/* 310 */     String result = s;
/* 311 */     for (String tag : this.vRemoveBlanks) {
/* 312 */       if (!P_REMOVE_PAIR_BLANKS.containsKey(tag)) {
/* 313 */         P_REMOVE_PAIR_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?></" + tag + ">"));
/*     */       }
/* 315 */       result = regexReplace(P_REMOVE_PAIR_BLANKS.get(tag), "", result);
/* 316 */       if (!P_REMOVE_SELF_BLANKS.containsKey(tag)) {
/* 317 */         P_REMOVE_SELF_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?/>"));
/*     */       }
/* 319 */       result = regexReplace(P_REMOVE_SELF_BLANKS.get(tag), "", result);
/*     */     } 
/*     */     
/* 322 */     return result;
/*     */   }
/*     */   
/*     */   private static String regexReplace(Pattern regex_pattern, String replacement, String s) {
/* 326 */     Matcher m = regex_pattern.matcher(s);
/* 327 */     return m.replaceAll(replacement);
/*     */   }
/*     */ 
/*     */   
/*     */   private String processTag(String s) {
/* 332 */     Matcher m = P_END_TAG.matcher(s);
/* 333 */     if (m.find()) {
/* 334 */       String name = m.group(1).toLowerCase();
/* 335 */       if (allowed(name) && 
/* 336 */         !inArray(name, this.vSelfClosingTags) && 
/* 337 */         this.vTagCounts.containsKey(name)) {
/* 338 */         this.vTagCounts.put(name, Integer.valueOf(((Integer)this.vTagCounts.get(name)).intValue() - 1));
/* 339 */         return "</" + name + ">";
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 346 */     m = P_START_TAG.matcher(s);
/* 347 */     if (m.find()) {
/* 348 */       String name = m.group(1).toLowerCase();
/* 349 */       String body = m.group(2);
/* 350 */       String ending = m.group(3);
/*     */ 
/*     */       
/* 353 */       if (allowed(name)) {
/* 354 */         String params = "";
/*     */         
/* 356 */         Matcher m2 = P_QUOTED_ATTRIBUTES.matcher(body);
/* 357 */         Matcher m3 = P_UNQUOTED_ATTRIBUTES.matcher(body);
/* 358 */         List<String> paramNames = new ArrayList<>();
/* 359 */         List<String> paramValues = new ArrayList<>();
/* 360 */         while (m2.find()) {
/* 361 */           paramNames.add(m2.group(1));
/* 362 */           paramValues.add(m2.group(3));
/*     */         } 
/* 364 */         while (m3.find()) {
/* 365 */           paramNames.add(m3.group(1));
/* 366 */           paramValues.add(m3.group(3));
/*     */         } 
/*     */ 
/*     */         
/* 370 */         for (int ii = 0; ii < paramNames.size(); ii++) {
/* 371 */           String paramName = ((String)paramNames.get(ii)).toLowerCase();
/* 372 */           String paramValue = paramValues.get(ii);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 378 */           if (allowedAttribute(name, paramName)) {
/* 379 */             if (inArray(paramName, this.vProtocolAtts)) {
/* 380 */               paramValue = processParamProtocol(paramValue);
/*     */             }
/* 382 */             params = params + " " + paramName + "=\"" + paramValue + "\"";
/*     */           } 
/*     */         } 
/*     */         
/* 386 */         if (inArray(name, this.vSelfClosingTags)) {
/* 387 */           ending = " /";
/*     */         }
/*     */         
/* 390 */         if (inArray(name, this.vNeedClosingTags)) {
/* 391 */           ending = "";
/*     */         }
/*     */         
/* 394 */         if (ending == null || ending.length() < 1) {
/* 395 */           if (this.vTagCounts.containsKey(name)) {
/* 396 */             this.vTagCounts.put(name, Integer.valueOf(((Integer)this.vTagCounts.get(name)).intValue() + 1));
/*     */           } else {
/* 398 */             this.vTagCounts.put(name, Integer.valueOf(1));
/*     */           } 
/*     */         } else {
/* 401 */           ending = " /";
/*     */         } 
/* 403 */         return "<" + name + params + ending + ">";
/*     */       } 
/* 405 */       return "";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 410 */     m = P_COMMENT.matcher(s);
/* 411 */     if (!this.stripComment && m.find()) {
/* 412 */       return "<" + m.group() + ">";
/*     */     }
/*     */     
/* 415 */     return "";
/*     */   }
/*     */   
/*     */   private String processParamProtocol(String s) {
/* 419 */     s = decodeEntities(s);
/* 420 */     Matcher m = P_PROTOCOL.matcher(s);
/* 421 */     if (m.find()) {
/* 422 */       String protocol = m.group(1);
/* 423 */       if (!inArray(protocol, this.vAllowedProtocols)) {
/*     */         
/* 425 */         s = "#" + s.substring(protocol.length() + 1, s.length());
/* 426 */         if (s.startsWith("#//")) {
/* 427 */           s = "#" + s.substring(3, s.length());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 432 */     return s;
/*     */   }
/*     */   
/*     */   private String decodeEntities(String s) {
/* 436 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 438 */     Matcher m = P_ENTITY.matcher(s);
/* 439 */     while (m.find()) {
/* 440 */       String match = m.group(1);
/* 441 */       int decimal = Integer.decode(match).intValue();
/* 442 */       m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
/*     */     } 
/* 444 */     m.appendTail(buf);
/* 445 */     s = buf.toString();
/*     */     
/* 447 */     buf = new StringBuffer();
/* 448 */     m = P_ENTITY_UNICODE.matcher(s);
/* 449 */     while (m.find()) {
/* 450 */       String match = m.group(1);
/* 451 */       int decimal = Integer.valueOf(match, 16).intValue();
/* 452 */       m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
/*     */     } 
/* 454 */     m.appendTail(buf);
/* 455 */     s = buf.toString();
/*     */     
/* 457 */     buf = new StringBuffer();
/* 458 */     m = P_ENCODE.matcher(s);
/* 459 */     while (m.find()) {
/* 460 */       String match = m.group(1);
/* 461 */       int decimal = Integer.valueOf(match, 16).intValue();
/* 462 */       m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
/*     */     } 
/* 464 */     m.appendTail(buf);
/* 465 */     s = buf.toString();
/*     */     
/* 467 */     s = validateEntities(s);
/* 468 */     return s;
/*     */   }
/*     */   
/*     */   private String validateEntities(String s) {
/* 472 */     StringBuffer buf = new StringBuffer();
/*     */ 
/*     */     
/* 475 */     Matcher m = P_VALID_ENTITIES.matcher(s);
/* 476 */     while (m.find()) {
/* 477 */       String one = m.group(1);
/* 478 */       String two = m.group(2);
/* 479 */       m.appendReplacement(buf, Matcher.quoteReplacement(checkEntity(one, two)));
/*     */     } 
/* 481 */     m.appendTail(buf);
/*     */     
/* 483 */     return encodeQuotes(buf.toString());
/*     */   }
/*     */   
/*     */   private String encodeQuotes(String s) {
/* 487 */     if (this.encodeQuotes) {
/* 488 */       StringBuffer buf = new StringBuffer();
/* 489 */       Matcher m = P_VALID_QUOTES.matcher(s);
/* 490 */       while (m.find()) {
/* 491 */         String one = m.group(1);
/* 492 */         String two = m.group(2);
/* 493 */         String three = m.group(3);
/* 494 */         m.appendReplacement(buf, Matcher.quoteReplacement(one + regexReplace(P_QUOTE, "&quot;", two) + three));
/*     */       } 
/* 496 */       m.appendTail(buf);
/* 497 */       return buf.toString();
/*     */     } 
/* 499 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String checkEntity(String preamble, String term) {
/* 505 */     return (";".equals(term) && isValidEntity(preamble)) ? ('&' + preamble) : ("&amp;" + preamble);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidEntity(String entity) {
/* 511 */     return inArray(entity, this.vAllowedEntities);
/*     */   }
/*     */   
/*     */   private static boolean inArray(String s, String[] array) {
/* 515 */     for (String item : array) {
/* 516 */       if (item != null && item.equals(s)) {
/* 517 */         return true;
/*     */       }
/*     */     } 
/* 520 */     return false;
/*     */   }
/*     */   
/*     */   private boolean allowed(String name) {
/* 524 */     return ((this.vAllowed.isEmpty() || this.vAllowed.containsKey(name)) && !inArray(name, this.vDisallowed));
/*     */   }
/*     */   
/*     */   private boolean allowedAttribute(String name, String paramName) {
/* 528 */     return (allowed(name) && (this.vAllowed.isEmpty() || ((List)this.vAllowed.get(name)).contains(paramName)));
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\xss\HTMLFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */