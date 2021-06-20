package cn.ssms.dao;

import cn.ssms.model.Advert;
import java.util.List;
import java.util.Map;

public interface AdvertMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(Advert paramAdvert);
  
  int insertSelective(Advert paramAdvert);
  
  Advert selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(Advert paramAdvert);
  
  int updateByPrimaryKey(Advert paramAdvert);
  
  List<Advert> selectAdvertByParams(Map<String, Object> paramMap);
  
  Integer countAdvertByParams(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\AdvertMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */