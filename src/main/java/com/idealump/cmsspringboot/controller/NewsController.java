package com.idealump.cmsspringboot.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.idealump.cmsspringboot.model.News;
import com.idealump.cmsspringboot.repository.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String login(HttpEntity<String> httpEntity) throws Exception {
        String jsonStr = httpEntity.getBody();
        return jsonStr;    
    }

    @RequestMapping(value="getLast10")
    public List<News> getLast10() throws Exception {
        return newsRepository.findTop10ById();
    }

    @RequestMapping(value="getMaxId")
    public int getMaxId() throws Exception {
        return newsRepository.maxId() + 1;
    }

    @RequestMapping(value="insertNews", method=RequestMethod.POST)
    public @ResponseBody String saveNews(@Valid @RequestBody News news) throws Exception {
        newsRepository.save(news);
        return "success";
    }

    @RequestMapping(value={"searchNews", "searchNews/{sText}"})
    public List<News> searchNews(@PathVariable Map<String, String> pathVariables) {
        if (pathVariables.containsKey("sText")) {
            return newsRepository.findByNewsIgnoreCaseContaining(pathVariables.get("sText"));
        } else {
            return newsRepository.findAll();
        }
    }

    @RequestMapping(value="editNews/{id}")
    public News editNews(@PathVariable int id) {
        return newsRepository.findById(id).get();
    }

    @RequestMapping(value="updateNews", method=RequestMethod.POST)
    public @ResponseBody String updateNews(@Valid @RequestBody News  news) throws Exception {
        News n1 = newsRepository.getOne(news.getId());
        n1.setPublish_from(news.getPublish_from());
        n1.setPublish_to(news.getPublish_to());;
        n1.setNews(news.getNews());
        n1.setStatus(news.getStatus());
        newsRepository.save(n1);
        return "success";
    }

    @RequestMapping(value="deleteNews/{id}")
    public String deleteNews(@PathVariable("id") int id) {
        newsRepository.deleteById(id);
        return "success";
    }
}
