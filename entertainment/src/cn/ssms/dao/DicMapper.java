package cn.ssms.dao;

import cn.ssms.model.Dic;
import java.util.List;
import java.util.Map;

public interface DicMapper {
  List<Dic> getAllRoleList();
  
  List<Dic> getRegion(Map<String, Object> paramMap);
  
  List<Dic> getRegionUser(Map<String, Object> paramMap);
  
  List<Dic> getDicByType(Map<String, Object> paramMap);
  
  List<Dic> getBankCodes(Map<String, Object> paramMap);
  
  List<Dic> getModalsList(Map<String, Object> paramMap);
  
  List<Dic> getCouponsList(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\DicMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */