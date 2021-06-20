package cn.ssms.service;

import cn.ssms.model.Advert;
import cn.ssms.model.Classfiy;
import java.util.List;
import java.util.Map;

public interface AdvertService {
  List<Classfiy> removeClassfiy(String paramString);
  
  Classfiy updClassfiy(Map<String, Object> paramMap);
  
  Classfiy addClassfiy(Map<String, Object> paramMap);
  
  List<Classfiy> getList(Map<String, Object> paramMap);
  
  Classfiy getClassfiyById(String paramString);
  
  String getAdvertList(Map<String, Object> paramMap) throws Exception;
  
  Advert addAdvert(Advert paramAdvert);
  
  Advert updAdvert(Advert paramAdvert);
  
  List<Advert> removeAdvert(String paramString);
  
  Advert getAdvertById(String paramString);
  
  String classfiyList(Map<String, Object> paramMap) throws Exception;
  
  List<Classfiy> isReName(Map<String, Object> paramMap);
  
  List<Classfiy> isReSequene(Map<String, Object> paramMap);
}


/* Location:              D:\教育文化娱乐系统20200916\教育文化娱乐系统后台安装手册\安装软件\entertainment\WEB-INF\classes\!\cn\ssms\service\AdvertService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */