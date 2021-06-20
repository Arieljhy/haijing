package cn.ssms.dao;

import cn.ssms.model.Live;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(Live paramLive);
  
  int insertSelective(Live paramLive);
  
  Live selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(Live paramLive);
  
  int updateByPrimaryKey(Live paramLive);
  
  List<Live> getLiveList(Map<String, Object> paramMap);
  
  Integer getLiveListTotal(Map<String, Object> paramMap);
  
  int updateStatusByIds(Map<String, Object> paramMap);
  
  int checkName(String paramString);
  
  int checkCode(String paramString);
  
  Live selectByCode(String paramString);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\LiveMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */