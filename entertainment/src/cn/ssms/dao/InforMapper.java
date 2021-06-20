package cn.ssms.dao;

import cn.ssms.model.Infor;
import java.util.List;
import java.util.Map;

public interface InforMapper {
  List<Map<String, Object>> getInforList(int paramInt);
  
  Map<String, Object> getInforById(int paramInt);
  
  List<Infor> getInforByClassFiyId(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\InforMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */