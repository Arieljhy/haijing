package cn.ssms.dao;

import cn.ssms.model.Bbs;
import java.util.List;
import java.util.Map;

public interface BbsMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(Bbs paramBbs);
  
  int insertSelective(Bbs paramBbs);
  
  Bbs selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(Bbs paramBbs);
  
  int updateById(Bbs paramBbs);
  
  int updateByPrimaryKey(Bbs paramBbs);
  
  List<Bbs> getBbsList(Map<String, Object> paramMap);
  
  Integer getBbsListTotal(Map<String, Object> paramMap);
  
  List<Bbs> selectReply(Map<String, Object> paramMap);
  
  Integer selectReplyCount(Map<String, Object> paramMap);
  
  List<Bbs> getTbbsByClassFiyId(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\BbsMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */