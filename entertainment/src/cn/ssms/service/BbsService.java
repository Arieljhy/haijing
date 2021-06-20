package cn.ssms.service;

import cn.ssms.model.Bbs;
import cn.ssms.model.Classfiy;
import cn.ssms.util.DataAccessException;
import java.util.List;
import java.util.Map;

public interface BbsService {
  String getBbsList(Map<String, Object> paramMap);
  
  String selectReply(Map<String, Object> paramMap) throws DataAccessException;
  
  String findBbsOne(Map<String, Object> paramMap);
  
  String updBbsDetail(Map<String, Object> paramMap) throws DataAccessException;
  
  String removeBbs(Map<String, Object> paramMap) throws DataAccessException;
  
  List<Classfiy> getList(Map<String, Object> paramMap);
  
  String classfiyList(Map<String, Object> paramMap) throws Exception;
  
  List<Classfiy> isReName(Map<String, Object> paramMap);
  
  List<Bbs> getTbbsByClassFiyId(Map<String, Object> paramMap);
  
  List<Classfiy> removeClassfiy(String paramString);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\BbsService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */