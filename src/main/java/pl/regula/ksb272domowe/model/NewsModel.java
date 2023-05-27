package pl.regula.ksb272domowe.model;

public class NewsModel {

    private long id;
    private String title;
    private String summary;
    private String image;

    public NewsModel(long id, String title, String summary, String image) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.image = image;
    }

    public NewsModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
