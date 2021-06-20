package cn.ssms.dao;

import cn.ssms.model.RegionAuthor;
import java.util.List;
import java.util.Map;

public interface RegionAuthorMapper {
  List<RegionAuthor> getRegionAuthorByRoleId(Integer paramInteger);
  
  int addRegionAuthor(Map<String, Object> paramMap);
  
  int removeResionAuthorByRoleId(Integer paramInteger);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\RegionAuthorMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */