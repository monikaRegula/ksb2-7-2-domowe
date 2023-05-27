package pl.regula.ksb272domowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.regula.ksb272domowe.dao.NewsDao;
import pl.regula.ksb272domowe.model.NewsModel;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private NewsDao newsDao;

    @Autowired
    public NewsService(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public void fetchCurrentNews(List<NewsModel> models) {
        models.forEach(n -> newsDao.saveNews(n.getId(), n.getTitle(), n.getSummary(), n.getImage()));
    }

    public List<NewsModel> findAllNews(){
        List<NewsModel> allNews = newsDao.findAllNews();
        return allNews;
    }

    public int updateNews(NewsModel newsModel, long id){
        int i = newsDao.updateNews(newsModel, id);
        return i;
    }
}
