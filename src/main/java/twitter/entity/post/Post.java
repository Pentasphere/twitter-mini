package twitter.entity.post;

import jakarta.persistence.*;
import twitter.entity.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "twitter_user_id", referencedColumnName = "id")
    /*private Integer authorId;*/
    private User author;

    @Column(name = "topic")
    private String topic;

    @Column(name = "text")
    private String text;

    @Column(name = "tags")
    private String tagsAsString;

    @ManyToMany(mappedBy = "postsILike", fetch = FetchType.EAGER)
    private List<User> usersWhoLiked;

    @Transient
    private String[] tags;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.tagsAsString = String.join(",", this.tags);
    }

    @PreUpdate
    protected void onUpdate() {
        this.tagsAsString = String.join(",", this.tags);
    }

    @PostLoad
    protected void onLoad() {
        if (this.tagsAsString != null) {
            this.tags = this.tagsAsString.split(",");
        }else {
            this.tags = new String[0];
        }
        /*Integer size = this.usersWhoLiked.size();*/
    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /*public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }*/

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<User> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(List<User> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    /*public boolean hasTag(String tag) {
        for (String t : tags) {
            if (t.equals(tag)) {
                return true;
            }
        }
        return false;
    }*/

    /*public String toFileString(){
        String tagsAsString = String.join(",", this.tags);
        DateTimeFormatter fDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String creationDateAsString = this.creationDate.format(fDateTime);
        return String.format("{%d}{%d}{%s}{%s}{%s}{%s}", this.id, *//*this.authorId*//* this.author.getId(), this.topic, this.text, tagsAsString, creationDateAsString);
    }*/
}
