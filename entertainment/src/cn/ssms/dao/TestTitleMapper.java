package cn.ssms.dao;

import cn.ssms.model.Anser;
import cn.ssms.model.Result;
import cn.ssms.model.TestTitle;
import cn.ssms.model.UserAnser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TestTitleMapper {
  List<TestTitle> getTestTitleList(Map<String, Object> paramMap);
  
  int getTestTitleListCount(Map<String, Object> paramMap);
  
  int addTitle(TestTitle paramTestTitle);
  
  List<Anser> getAnserList(Integer paramInteger);
  
  int addUserAnser(UserAnser paramUserAnser);
  
  List<UserAnser> getUserAnserList(Map<String, Object> paramMap);
  
  int getUserAnserListTotal(Map<String, Object> paramMap);
  
  Integer getResultScore(Map<String, Object> paramMap);
  
  int addResult(Result paramResult);
  
  Result getResult(Map<String, Object> paramMap);
  
  UserAnser getTitleDetail(Map<String, Object> paramMap);
  
  List<Result> getReplyList(Map<String, Object> paramMap);
  
  int getReplyListTotal(Map<String, Object> paramMap);
  
  int addAnser(Anser paramAnser);
  
  int addAnserList(List<Anser> paramList);
  
  int updateTestTitle(TestTitle paramTestTitle);
  
  int updateBatchAnser(List<Anser> paramList);
  
  int getSequenceCount(@Param("mainId") Integer paramInteger);
  
  TestTitle getTestTitleDetail(Map<String, Object> paramMap);
  
  int removeTitle(@Param("mainId") String paramString1, @Param("id") String paramString2);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\dao\TestTitleMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */