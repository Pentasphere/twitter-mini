package twitter.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import twitter.configuration.Component;
import twitter.configuration.Injection;
import twitter.dao.PostDAO;
import twitter.entity.post.Post;
import twitter.exception.TwitterCommandException;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DBPostDAO implements PostDAO {

    /*private final String dbUrl = "jdbc:postgresql://localhost:5432/twitter_mini_db";
    private final String dbUser = "postgres";
    private final String dbPassword = "postgres";*/

    /*private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;*/

    /*@Value(key = "database.url")
    private String dbUrl;

    @Value(key = "database.user")
    private String dbUser;

    @Value(key = "database.password")
    private String dbPassword;*/

    /*private final UserService userService;*/
    /*private final Map<String, Object> databaseProperties;*/
    /*private final Environment environment;*/
    private final EntityManagerFactory entityManagerFactory;

    @Injection
    public DBPostDAO(
            /*UserService userService,*/
            /*Map<String, Object> databaseProperties*/
            /*Environment environment*/
            EntityManagerFactory entityManagerFactory
    ) {
        /*this.userService = userService;*/
        /*this.databaseProperties = databaseProperties;
        this.dbUrl = (String) this.databaseProperties.get("dbUrl");
        this.dbUser = (String) this.databaseProperties.get("dbUser");
        this.dbPassword = (String) this.databaseProperties.get("dbPassword");*/
        /*this.environment = environment;
        this.dbUrl = this.environment.get("database.url");
        this.dbUser = this.environment.get("database.user");
        this.dbPassword = this.environment.get("database.password");*/
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Post saveNewPost(Post post) {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(post);
                entityManager.getTransaction().commit();
                return post;
            } catch (Exception ex) {
                entityManager.getTransaction().rollback();
                throw new TwitterCommandException(ex.getMessage());
            }
        }

        /*try (
                Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String tags = String.join(",", post.getTags());
            String query = String.format("insert into post (twitter_user_id, topic, text, tags) values (%d, '%s','%s','%s');", post.getAuthor().getId(), post.getTopic(), post.getText(), tags);
            statement.execute(query);

            String query2 = "select id, created_at from post where twitter_user_id =  " + post.getAuthor().getId() + " order by created_at desc limit 1;";
            ResultSet resultSet = statement.executeQuery(query2);
            while (resultSet.next()) {
                post.setId(resultSet.getInt("id"));
                post.setCreationDate(resultSet.getTimestamp("created_at").toLocalDateTime());
            }

            return post;
        } catch (SQLException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public List<Post> getAllPosts() {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {
            return entityManager.createQuery("select p from Post p", Post.class).getResultList();
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = "select * from post order by created_at desc;";
            ResultSet resultSet = statement.executeQuery(query);

            List<Post> posts = new LinkedList<>();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCreationDate(resultSet.getTimestamp("created_at").toLocalDateTime());
                post.setTopic(resultSet.getString("topic"));
                post.setText(resultSet.getString("text"));
                post.setTags(resultSet.getString("tags").split(","));
                post.setAuthor(this.userService.getUserById(resultSet.getInt("twitter_user_id")));

                posts.add(post);
            }

            return posts;
        } catch (SQLException | UserNotFoundException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public List<Post> getAllPostsByUser(int userId) {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {
            return entityManager
                    .createQuery("select p from Post p where p.author.id = :userId", Post.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = "select * from post where post.twitter_uesr_id = " + userId + " order by created_at desc;";
            ResultSet resultSet = statement.executeQuery(query);

            List<Post> posts = new LinkedList<>();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCreationDate(resultSet.getTimestamp("created_at").toLocalDateTime());
                post.setTopic(resultSet.getString("topic"));
                post.setText(resultSet.getString("text"));
                post.setTags(resultSet.getString("tags").split(","));
                post.setAuthor(this.userService.getUserById(resultSet.getInt("twitter_user_id")));

                posts.add(post);
            }

            return posts;
        } catch (SQLException | UserNotFoundException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public List<Post> getAllPostsByTag(String tag) {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
            Root<Post> root = criteriaQuery.from(Post.class);

            Predicate predicate = builder.like(root.get("tagsAsString"), "%" + tag + "%");
            Predicate predicate1 = builder.greaterThanOrEqualTo(root.get("creationDate"), LocalDateTime.now().minusDays(7));
            Predicate finalPredicate = builder.and(predicate, predicate1);

            criteriaQuery.select(root).where(finalPredicate);

            return entityManager.createQuery(criteriaQuery).getResultList();


            /*return entityManager
                    .createQuery("select p from Post p where p.tagsAsString like :tag", Post.class)
                    .setParameter("tag", "%" + tag + "%")
                    .getResultList();*/
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = "select * from post where post.tags like '%" + tag + "%' order by created_at desc;";
            ResultSet resultSet = statement.executeQuery(query);

            List<Post> posts = new LinkedList<>();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCreationDate(resultSet.getTimestamp("created_at").toLocalDateTime());
                post.setTopic(resultSet.getString("topic"));
                post.setText(resultSet.getString("text"));
                post.setTags(resultSet.getString("tags").split(","));
                post.setAuthor(this.userService.getUserById(resultSet.getInt("twitter_user_id")));

                posts.add(post);
            }

            return posts;
        } catch (SQLException | UserNotFoundException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public List<Post> getAllPostsByUserIdIn(int[] userIds) {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {
            return entityManager
                    .createQuery("select p from Post p where p.author.id in (:ids) order by p.creationDate desc", Post.class)
                    .setParameter("ids", Arrays.stream(userIds).boxed().toList())
                    .getResultList();
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = "select * from post where post.twitter_uesr_id in (" + Arrays.stream(userIds).boxed().toList() + ") order by created_at desc;";
            ResultSet resultSet = statement.executeQuery(query);

            List<Post> posts = new LinkedList<>();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCreationDate(resultSet.getTimestamp("created_at").toLocalDateTime());
                post.setTopic(resultSet.getString("topic"));
                post.setText(resultSet.getString("text"));
                post.setTags(resultSet.getString("tags").split(","));
                post.setAuthor(this.userService.getUserById(resultSet.getInt("twitter_user_id")));

                posts.add(post);
            }

            return posts;
        } catch (SQLException | UserNotFoundException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }
}
