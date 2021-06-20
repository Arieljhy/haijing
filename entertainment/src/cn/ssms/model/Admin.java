/*     */ package cn.ssms.model;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class Admin
/*     */ {
/*     */   private Integer id;
/*     */   private String userCode;
/*     */   private String userName;
/*     */   private String password;
/*     */   private String payPwd;
/*     */   private String tel;
/*     */   private String post;
/*     */   private String provinceId;
/*     */   private String cityId;
/*     */   private String areaId;
/*     */   private String province;
/*     */   private String city;
/*     */   private String area;
/*     */   private String idCard;
/*     */   private String culture;
/*     */   private String jobNo;
/*     */   private Integer status;
/*     */   private Float wages;
/*     */   private String addDate;
/*     */   private String updateDate;
/*     */   private String delDate;
/*     */   private Integer type;
/*     */   private String rolesStr;
/*     */   private String roles;
/*     */   private List<RegionAuthor> regions;
/*     */   private Integer count;
/*     */   private String bankNo;
/*     */   private String regionStr;
/*     */   private String bankName;
/*     */   private String bankCode;
/*     */   
/*     */   public List<RegionAuthor> getRegions() {
/*  84 */     return this.regions;
/*     */   }
/*     */   
/*     */   public void setRegions(List<RegionAuthor> regions) {
/*  88 */     this.regions = regions;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  92 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getUserCode() {
/*  96 */     return this.userCode;
/*     */   }
/*     */   
/*     */   public String getUserName() {
/* 100 */     return this.userName;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/* 104 */     return this.password;
/*     */   }
/*     */   
/*     */   public String getPost() {
/* 108 */     return this.post;
/*     */   }
/*     */   
/*     */   public String getProvinceId() {
/* 112 */     return this.provinceId;
/*     */   }
/*     */   
/*     */   public String getCityId() {
/* 116 */     return this.cityId;
/*     */   }
/*     */   
/*     */   public String getAreaId() {
/* 120 */     return this.areaId;
/*     */   }
/*     */   
/*     */   public String getIdCard() {
/* 124 */     return this.idCard;
/*     */   }
/*     */   
/*     */   public String getCulture() {
/* 128 */     return this.culture;
/*     */   }
/*     */   
/*     */   public String getJobNo() {
/* 132 */     return this.jobNo;
/*     */   }
/*     */   
/*     */   public Integer getStatus() {
/* 136 */     return this.status;
/*     */   }
/*     */   
/*     */   public Float getWages() {
/* 140 */     return this.wages;
/*     */   }
/*     */   
/*     */   public String getAddDate() {
/* 144 */     return this.addDate;
/*     */   }
/*     */   
/*     */   public String getUpdateDate() {
/* 148 */     return this.updateDate;
/*     */   }
/*     */   
/*     */   public String getDelDate() {
/* 152 */     return this.delDate;
/*     */   }
/*     */   
/*     */   public String getRolesStr() {
/* 156 */     return this.rolesStr;
/*     */   }
/*     */   
/*     */   public String getRoles() {
/* 160 */     return this.roles;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 164 */     this.id = id;
/*     */   }
/*     */   
/*     */   public void setUserCode(String userCode) {
/* 168 */     this.userCode = userCode;
/*     */   }
/*     */   
/*     */   public void setUserName(String userName) {
/* 172 */     this.userName = userName;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 176 */     this.password = password;
/*     */   }
/*     */   
/*     */   public void setPost(String post) {
/* 180 */     this.post = post;
/*     */   }
/*     */   
/*     */   public void setProvinceId(String provinceId) {
/* 184 */     this.provinceId = provinceId;
/*     */   }
/*     */   
/*     */   public void setCityId(String cityId) {
/* 188 */     this.cityId = cityId;
/*     */   }
/*     */   
/*     */   public void setAreaId(String areaId) {
/* 192 */     this.areaId = areaId;
/*     */   }
/*     */   
/*     */   public void setIdCard(String idCard) {
/* 196 */     this.idCard = idCard;
/*     */   }
/*     */   
/*     */   public void setCulture(String culture) {
/* 200 */     this.culture = culture;
/*     */   }
/*     */   
/*     */   public void setJobNo(String jobNo) {
/* 204 */     this.jobNo = jobNo;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/* 208 */     this.status = status;
/*     */   }
/*     */   
/*     */   public void setWages(Float wages) {
/* 212 */     this.wages = wages;
/*     */   }
/*     */   
/*     */   public void setAddDate(String addDate) {
/* 216 */     this.addDate = addDate;
/*     */   }
/*     */   
/*     */   public void setUpdateDate(String updateDate) {
/* 220 */     this.updateDate = updateDate;
/*     */   }
/*     */   
/*     */   public void setDelDate(String delDate) {
/* 224 */     this.delDate = delDate;
/*     */   }
/*     */   
/*     */   public void setRolesStr(String rolesStr) {
/* 228 */     this.rolesStr = rolesStr;
/*     */   }
/*     */   
/*     */   public void setRoles(String roles) {
/* 232 */     this.roles = roles;
/*     */   }
/*     */   
/*     */   public Integer getType() {
/* 236 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/* 240 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getProvince() {
/* 244 */     return this.province;
/*     */   }
/*     */   
/*     */   public String getCity() {
/* 248 */     return this.city;
/*     */   }
/*     */   
/*     */   public String getArea() {
/* 252 */     return this.area;
/*     */   }
/*     */   
/*     */   public void setProvince(String province) {
/* 256 */     this.province = province;
/*     */   }
/*     */   
/*     */   public void setCity(String city) {
/* 260 */     this.city = city;
/*     */   }
/*     */   
/*     */   public void setArea(String area) {
/* 264 */     this.area = area;
/*     */   }
/*     */   
/*     */   public Integer getCount() {
/* 268 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(Integer count) {
/* 272 */     this.count = count;
/*     */   }
/*     */   
/*     */   public String getBankNo() {
/* 276 */     return this.bankNo;
/*     */   }
/*     */   
/*     */   public void setBankNo(String bankNo) {
/* 280 */     this.bankNo = bankNo;
/*     */   }
/*     */   
/*     */   public String getRegionStr() {
/* 284 */     return this.regionStr;
/*     */   }
/*     */   
/*     */   public void setRegionStr(String regionStr) {
/* 288 */     this.regionStr = regionStr;
/*     */   }
/*     */   
/*     */   public String getPayPwd() {
/* 292 */     return this.payPwd;
/*     */   }
/*     */   
/*     */   public void setPayPwd(String payPwd) {
/* 296 */     this.payPwd = payPwd;
/*     */   }
/*     */   
/*     */   public String getBankName() {
/* 300 */     return this.bankName;
/*     */   }
/*     */   
/*     */   public String getBankCode() {
/* 304 */     return this.bankCode;
/*     */   }
/*     */   
/*     */   public void setBankName(String bankName) {
/* 308 */     this.bankName = bankName;
/*     */   }
/*     */   
/*     */   public void setBankCode(String bankCode) {
/* 312 */     this.bankCode = bankCode;
/*     */   }
/*     */   
/*     */   public String getTel() {
/* 316 */     return this.tel;
/*     */   }
/*     */   
/*     */   public void setTel(String tel) {
/* 320 */     this.tel = tel;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\Admin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */