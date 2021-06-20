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
/*     */ public class File
/*     */ {
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer fileType;
/*     */   private Integer category;
/*     */   private String categoryStr;
/*     */   private Integer type;
/*     */   private String typeStr;
/*     */   private Integer videoRegion;
/*     */   private String videoRegionStr;
/*     */   private Integer videoYear;
/*     */   private String videoYearStr;
/*     */   private String company;
/*     */   private String author;
/*     */   private String actor;
/*     */   private String introduction;
/*     */   private String image;
/*     */   private Integer playCount;
/*     */   private Integer state;
/*     */   private Integer addPerson;
/*     */   private String addDate;
/*     */   private Integer count;
/*     */   private String episodes;
/*     */   private String userName;
/*     */   private String[] urls;
/*     */   private List<Resources> resources;


    public File() {
    }

    public File(String url) {
    }

    /*     */
/*     */   public String[] getUrls() {
/*  70 */     return this.urls;
/*     */   }
/*     */   
/*     */   public void setUrls(String[] urls) {
/*  74 */     this.urls = urls;
/*     */   }
/*     */   
/*     */   public Integer getCount() {
/*  78 */     return this.count;
/*     */   }
/*     */   public void setCount(Integer count) {
/*  81 */     this.count = count;
/*     */   }
/*     */   public Integer getId() {
/*  84 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/*  87 */     this.id = id;
/*     */   }
/*     */   public String getName() {
/*  90 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  93 */     this.name = name;
/*     */   }
/*     */   public Integer getFileType() {
/*  96 */     return this.fileType;
/*     */   }
/*     */   public void setFileType(Integer fileType) {
/*  99 */     this.fileType = fileType;
/*     */   }
/*     */   public Integer getCategory() {
/* 102 */     return this.category;
/*     */   }
/*     */   public void setCategory(Integer category) {
/* 105 */     this.category = category;
/*     */   }
/*     */   public Integer getType() {
/* 108 */     return this.type;
/*     */   }
/*     */   public void setType(Integer type) {
/* 111 */     this.type = type;
/*     */   }
/*     */   public Integer getVideoRegion() {
/* 114 */     return this.videoRegion;
/*     */   }
/*     */   public void setVideoRegion(Integer videoRegion) {
/* 117 */     this.videoRegion = videoRegion;
/*     */   }
/*     */   public Integer getVideoYear() {
/* 120 */     return this.videoYear;
/*     */   }
/*     */   public void setVideoYear(Integer videoYear) {
/* 123 */     this.videoYear = videoYear;
/*     */   }
/*     */   public String getCompany() {
/* 126 */     return this.company;
/*     */   }
/*     */   public void setCompany(String company) {
/* 129 */     this.company = company;
/*     */   }
/*     */   public String getAuthor() {
/* 132 */     return this.author;
/*     */   }
/*     */   public void setAuthor(String author) {
/* 135 */     this.author = author;
/*     */   }
/*     */   public String getActor() {
/* 138 */     return this.actor;
/*     */   }
/*     */   public void setActor(String actor) {
/* 141 */     this.actor = actor;
/*     */   }
/*     */   public String getIntroduction() {
/* 144 */     return this.introduction;
/*     */   }
/*     */   public void setIntroduction(String introduction) {
/* 147 */     this.introduction = introduction;
/*     */   }
/*     */   public String getImage() {
/* 150 */     return this.image;
/*     */   }
/*     */   public void setImage(String image) {
/* 153 */     this.image = image;
/*     */   }
/*     */   public Integer getPlayCount() {
/* 156 */     return this.playCount;
/*     */   }
/*     */   public void setPlayCount(Integer playCount) {
/* 159 */     this.playCount = playCount;
/*     */   }
/*     */   public Integer getState() {
/* 162 */     return this.state;
/*     */   }
/*     */   public void setState(Integer state) {
/* 165 */     this.state = state;
/*     */   }
/*     */   public Integer getAddPerson() {
/* 168 */     return this.addPerson;
/*     */   }
/*     */   public void setAddPerson(Integer addPerson) {
/* 171 */     this.addPerson = addPerson;
/*     */   }
/*     */   public String getCategoryStr() {
/* 174 */     return this.categoryStr;
/*     */   }
/*     */   public String getTypeStr() {
/* 177 */     return this.typeStr;
/*     */   }
/*     */   public String getVideoRegionStr() {
/* 180 */     return this.videoRegionStr;
/*     */   }
/*     */   public String getVideoYearStr() {
/* 183 */     return this.videoYearStr;
/*     */   }
/*     */   public String getAddDate() {
/* 186 */     return this.addDate;
/*     */   }
/*     */   public void setCategoryStr(String categoryStr) {
/* 189 */     this.categoryStr = categoryStr;
/*     */   }
/*     */   public void setTypeStr(String typeStr) {
/* 192 */     this.typeStr = typeStr;
/*     */   }
/*     */   public void setVideoRegionStr(String videoRegionStr) {
/* 195 */     this.videoRegionStr = videoRegionStr;
/*     */   }
/*     */   public void setVideoYearStr(String videoYearStr) {
/* 198 */     this.videoYearStr = videoYearStr;
/*     */   }
/*     */   public void setAddDate(String addDate) {
/* 201 */     this.addDate = addDate;
/*     */   }
/*     */   public String getEpisodes() {
/* 204 */     return this.episodes;
/*     */   }
/*     */   public void setEpisodes(String episodes) {
/* 207 */     this.episodes = episodes;
/*     */   }
/*     */   public String getUserName() {
/* 210 */     return this.userName;
/*     */   }
/*     */   public void setUserName(String userName) {
/* 213 */     this.userName = userName;
/*     */   }
/*     */   public List<Resources> getResources() {
/* 216 */     return this.resources;
/*     */   }
/*     */   public void setResources(List<Resources> resources) {
/* 219 */     this.resources = resources;
/*     */   }
/*     */ }


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\model\File.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */