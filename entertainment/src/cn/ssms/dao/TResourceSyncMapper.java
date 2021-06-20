package cn.ssms.dao;

import cn.ssms.model.TResourceSync;

public interface TResourceSyncMapper {
  TResourceSync selectOneBySyncPath(String paramString);
  
  int insert(TResourceSync paramTResourceSync);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\TResourceSyncMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */