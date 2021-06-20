/*     */ package cn.ssms.util;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class ValidateTools
/*     */ {
/*     */   private static final String V_INTEGER = "^-?[1-9]\\d*$";
/*     */   private static final String V_Z_INDEX = "^[1-9]\\d*$";
/*     */   private static final String V_NEGATIVE_INTEGER = "^-[1-9]\\d*$";
/*     */   private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";
/*     */   private static final String V_POSITIVE_NUMBER = "^[1-9]\\d*|0$";
/*     */   private static final String V_NEGATINE_NUMBER = "^-[1-9]\\d*|0$";
/*     */   private static final String V_FLOAT = "^([+-]?)\\d*\\.\\d+$";
/*     */   private static final String V_POSTTIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";
/*     */   private static final String V_NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";
/*     */   private static final String V_UNPOSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";
/*     */   private static final String V_UN_NEGATIVE_FLOAT = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";
/*     */   private static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
/*     */   private static final String V_COLOR = "^[a-fA-F0-9]{6}$";
/*     */   private static final String V_URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";
/*     */   private static final String V_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
/*     */   private static final String V_ASCII = "^[\\x00-\\xFF]+$";
/*     */   private static final String V_ZIPCODE = "^\\d{6}$";
/*     */   private static final String V_MOBILE = "^(13|14|15|17|18|19)[0-9]{9}$";
/*     */   private static final String V_CTCC_MOBILE = "^(((133)|(153)|(149)|(180)|(181)|(189)|(177)|(173)|(199))+\\d{8})$";
/*     */   private static final String V_IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";
/*     */   private static final String V_NOTEMPTY = "^\\S+$";
/*     */   private static final String V_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";
/*     */   private static final String V_VIDEO = "(.*)\\.(mp4|rmvb|avi|rm|mov|mtv|wmv|amv|flv|dmv|3gp)$";
/*     */   private static final String V_AUDIO = "(.*)\\.(CD|WAV|WAVE|AIFF|MPEG|MP3|MPEG-4|MIDI|WMA|VQF|AMR|FLAC|AAC)$";
/*     */   private static final String V_GAME = "(.*)\\.(apk)$";
/*     */   private static final String V_DOC = "(.*)\\.(txt|rtf|pdf|doc|docx|xls|xlsx|ppt|pptx|html|htm|wpd|pdf)$";
/*     */   private static final String V_RAR = "(.*)\\.(rar|zip|7zip|tgz)$";
/*     */   private static final String V_DATETIME = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
/*     */   private static final String V_DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
/*     */   private static final String V_QQ_NUMBER = "^[1-9]*[1-9][0-9]*$";
/*     */   private static final String V_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
/*     */   private static final String V_USERNAME = "^\\w+$";
/*     */   private static final String V_LETTER = "^[A-Za-z]+$";
/*     */   private static final String V_LETTER_U = "^[A-Z]+$";
/*     */   private static final String V_LETTER_I = "^[a-z]+$";
/*     */   private static final String V_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
/*     */   private static final String V_PASSWORD_REG = "[A-Za-z]+[0-9]";
/*     */   private static final String V_PASSWORD_LENGTH = "^\\d{6,18}$";
/*     */   private static final String V_TWO＿POINT = "^[0-9]+(.[0-9]{2})?$";
/*     */   private static final String V_31DAYS = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
/*     */   
/*     */   public static boolean Integer(String value) {
/* 137 */     return match("^-?[1-9]\\d*$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Z_index(String value) {
/* 146 */     return match("^[1-9]\\d*$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Negative_integer(String value) {
/* 155 */     return match("^-[1-9]\\d*$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Number(String value) {
/* 164 */     return match("^([+-]?)\\d*\\.?\\d+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean PositiveNumber(String value) {
/* 173 */     return match("^[1-9]\\d*|0$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean NegatineNumber(String value) {
/* 182 */     return match("^-[1-9]\\d*|0$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Is31Days(String value) {
/* 191 */     return match("^((0?[1-9])|((1|2)[0-9])|30|31)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean ASCII(String value) {
/* 200 */     return match("^[\\x00-\\xFF]+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Chinese(String value) {
/* 210 */     return match("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Color(String value) {
/* 221 */     return match("^[a-fA-F0-9]{6}$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean DateTime(String value) {
/* 232 */     return match("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Date(String value) {
/* 240 */     return match("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Email(String value) {
/* 249 */     return match("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Float(String value) {
/* 258 */     return match("^([+-]?)\\d*\\.\\d+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean IDcard(String value) {
/* 267 */     return match("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean IP4(String value) {
/* 276 */     return match("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Letter(String value) {
/* 285 */     return match("^[A-Za-z]+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Letter_i(String value) {
/* 294 */     return match("^[a-z]+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Letter_u(String value) {
/* 304 */     return match("^[A-Z]+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Mobile(String value) {
/* 314 */     if (StringUtils.isBlank(value)) {
/* 315 */       return false;
/*     */     }
/* 317 */     return match("^(13|14|15|17|18|19)[0-9]{9}$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean CTCC_Mobile(String value) {
/* 326 */     return match("^(((133)|(153)|(149)|(180)|(181)|(189)|(177)|(173)|(199))+\\d{8})$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Negative_float(String value) {
/* 335 */     return match("^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Notempty(String value) {
/* 344 */     return match("^\\S+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Number_length(String value) {
/* 353 */     return match("^\\d{6,18}$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Password_reg(String value) {
/* 362 */     return match("[A-Za-z]+[0-9]", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Picture(String value) {
/* 371 */     return match("(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Video(String value) {
/* 380 */     return match("(.*)\\.(mp4|rmvb|avi|rm|mov|mtv|wmv|amv|flv|dmv|3gp)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Game(String value) {
/* 388 */     return match("(.*)\\.(apk)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Doc(String value) {
/* 397 */     return match("(.*)\\.(txt|rtf|pdf|doc|docx|xls|xlsx|ppt|pptx|html|htm|wpd|pdf)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Audio(String value) {
/* 405 */     return match("(.*)\\.(CD|WAV|WAVE|AIFF|MPEG|MP3|MPEG-4|MIDI|WMA|VQF|AMR|FLAC|AAC)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Posttive_float(String value) {
/* 413 */     return match("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean QQnumber(String value) {
/* 422 */     return match("^[1-9]*[1-9][0-9]*$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Rar(String value) {
/* 431 */     return match("(.*)\\.(rar|zip|7zip|tgz)$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Tel(String value) {
/* 440 */     return match("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Two_point(String value) {
/* 449 */     return match("^[0-9]+(.[0-9]{2})?$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Un_negative_float(String value) {
/* 458 */     return match("^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Unpositive_float(String value) {
/* 467 */     return match("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Url(String value) {
/* 476 */     return match("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean UserName(String value) {
/* 485 */     return match("^\\w+$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Zipcode(String value) {
/* 494 */     return match("^\\d{6}$", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean match(String regex, String str) {
/* 504 */     if (StringUtils.isBlank(str)) {
/* 505 */       return false;
/*     */     }
/* 507 */     Pattern pattern = Pattern.compile(regex, 2);
/* 508 */     Matcher matcher = pattern.matcher(str);
/* 509 */     return matcher.matches();
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\util\ValidateTools.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */