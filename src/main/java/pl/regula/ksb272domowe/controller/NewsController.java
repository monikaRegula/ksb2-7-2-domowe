package pl.regula.ksb272domowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.regula.ksb272domowe.model.News;
import pl.regula.ksb272domowe.model.NewsModel;
import pl.regula.ksb272domowe.service.NewsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "http://localhost:4200")
public class NewsController {

    private static final String NEWS_API = "https://api.spaceflightnewsapi.net/v4/articles";

    private RestTemplate restTemplate;
    private NewsService service;

    @Autowired
    public NewsController(NewsService service) {
        this.restTemplate = new RestTemplate();
        this.service = service;
        fetchNews();
    }

    public void fetchNews() {
        News news = restTemplate.getForObject(NEWS_API, News.class);
        List<NewsModel> newsModelList = news.getResults()
                .stream()
                .map(r -> new NewsModel(r.getId(), r.getTitle(), r.getSummary(), r.getImageUrl())).collect(Collectors.toList());
        service.fetchCurrentNews(newsModelList);
    }

    @GetMapping()
    public ResponseEntity<List<NewsModel>> getNews() {
        List<NewsModel> news = service.findAllNews();
        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity updateNews(@RequestBody NewsModel newsModel, @PathVariable long id) {
        int news = service.updateNews(newsModel, id);
        return new ResponseEntity(news, HttpStatus.OK);
    }

}
