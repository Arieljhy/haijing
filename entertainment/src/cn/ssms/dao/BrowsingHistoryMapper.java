package cn.ssms.dao;

import cn.ssms.model.BrowsingHistory;

public interface BrowsingHistoryMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(BrowsingHistory paramBrowsingHistory);
  
  int insertSelective(BrowsingHistory paramBrowsingHistory);
  
  BrowsingHistory selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(BrowsingHistory paramBrowsingHistory);
  
  int updateByPrimaryKey(BrowsingHistory paramBrowsingHistory);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\BrowsingHistoryMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */