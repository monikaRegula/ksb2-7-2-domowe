package pl.regula.ksb272domowe.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.regula.ksb272domowe.model.NewsModel;

import java.sql.Blob;
import java.util.*;

@Repository
public class NewsDaoImpl implements NewsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveNews(long id, String title, String summary, String image) {
        String sql = "INSERT INTO NEWSES VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, id, title, summary, image);
    }

    @Override
    public List<NewsModel> findAllNews() {
        String sql="SELECT * FROM NEWSES";
        List<NewsModel> news = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(elem -> news.add(new NewsModel(
                Long.parseLong(String.valueOf(elem.get("news_id"))),
                String.valueOf(elem.get("title")),
                String.valueOf(elem.get("summary")),
                String.valueOf(elem.get("image"))
        )));
        return news;
    }

    @Override
    public int updateNews(NewsModel model, long id) {
        String sql = "UPDATE NEWSES SET news_id = ?, title = ?, summary = ?, image =? WHERE news_id = ?";
        return jdbcTemplate.update(sql,model.getId(), model.getTitle(),model.getSummary(), model.getImage(), id);
    }
}
