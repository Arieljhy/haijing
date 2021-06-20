package cn.ssms.service;

import cn.ssms.model.Configure;
import java.util.List;
import java.util.Map;

public interface ConfigureService {
  List<Configure> getConfigList();
  
  List<Configure> getConfigByType(Integer paramInteger);
  
  int addConfig(Map<String, Object> paramMap);
  
  List<Configure> isReName(Map<String, Object> paramMap);
  
  int updateConfig(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\ConfigureService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */