package cn.ssms.ueditor.define;

public interface State {
  boolean isSuccess();
  
  void putInfo(String paramString1, String paramString2);
  
  void putInfo(String paramString, long paramLong);
  
  String toJSONString();
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssm\\ueditor\define\State.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */