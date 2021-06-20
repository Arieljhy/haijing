package cn.ssms.service;

import cn.ssms.dao.TResourceSyncMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tResourceSyncService")
@Transactional
public class TResourceSyncServiceImpl implements TResourceSyncService {
  @Autowired
  private TResourceSyncMapper tResourceSyncMapper;
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\TResourceSyncServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */