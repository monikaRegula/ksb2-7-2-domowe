package pl.regula.ksb272domowe.dao;

import pl.regula.ksb272domowe.model.NewsModel;

import java.util.List;

public interface NewsDao {

    int saveNews(long id, String title, String summary, String image);

    List<NewsModel> findAllNews();

    int updateNews(NewsModel newsModel, long id);
}
