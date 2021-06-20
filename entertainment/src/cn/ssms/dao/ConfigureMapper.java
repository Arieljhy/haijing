package cn.ssms.dao;

import cn.ssms.model.Configure;
import java.util.List;
import java.util.Map;

public interface ConfigureMapper {
  List<Configure> getConfigByType(Integer paramInteger);
  
  List<Configure> getClassify();
  
  List<Configure> getClassifyOfInfor();
  
  List<Configure> isReName(Map<String, Object> paramMap);
  
  List<Configure> getConfigList();
  
  int addConfig(Map<String, Object> paramMap);
  
  int updateConfig(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\ConfigureMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */