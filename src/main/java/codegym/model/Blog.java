package codegym.model;


import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String title;
    private Timestamp createDate;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable=false)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Blog() {
    }

    public Blog(String title,String author,String content, Timestamp createDate,Category category){
        this.title=title;
        this.author=author;
        this.content=content;
        this.createDate=createDate;
        this.category=category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
