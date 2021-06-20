package cn.ssms.service;

import cn.ssms.model.Live;
import java.util.Map;

public interface LiveService {
  String getLiveList(Map<String, Object> paramMap);
  
  Map<String, Object> removeById(Map<String, Object> paramMap);
  
  Live getLive(String paramString);
  
  Map<String, Object> saveOrUpdateLive(Live paramLive);
  
  Live getLiveByCode(String paramString);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\LiveService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */