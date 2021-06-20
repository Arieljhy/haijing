package cn.ssms.dao;

import cn.ssms.model.Forum;

public interface ForumMapper {
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(Forum paramForum);
  
  int insertSelective(Forum paramForum);
  
  Forum selectByPrimaryKey(Integer paramInteger);
  
  int updateByPrimaryKeySelective(Forum paramForum);
  
  int updateByPrimaryKey(Forum paramForum);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\ForumMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */