package twitter.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import twitter.configuration.*;
import twitter.configuration.Component;
import twitter.dao.UserDAO;
import twitter.entity.user.Organization;
import twitter.entity.user.Person;
import twitter.entity.user.User;
import twitter.entity.user.UserType;
import twitter.exception.TwitterCommandException;
import twitter.exception.UnknownUserTypeException;
import twitter.exception.UserNotFoundException;

import javax.print.DocFlavor;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
/*@Profile(active = {"prod", "default"})*/
public class DBUserDAO implements UserDAO {

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

    private final EntityManagerFactory entityManagerFactory;

    /*private final Map<String, Object> databaseProperties;*/
    /*private final Environment environment;*/

    @Injection
    public DBUserDAO(
            /*Map<String, Object> databaseProperties*/
            /*Environment environment*/
            EntityManagerFactory entityManagerFactory) {
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
    public User saveNewUser(User user) {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(user);
                /*System.out.println(user.getId());
                System.out.println(user.getLogin());*/
                entityManager.getTransaction().commit();
                return user;
            } catch (Exception ex) {
                entityManager.getTransaction().rollback();
                throw new TwitterCommandException(ex.getMessage());
            }
        }

        /*try (
                Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = String.format("insert into twitter_user (login, password, type) values ('%s','%s','%s');", user.getLogin(), user.getPassword(), user.getUserType());
            statement.execute(query);

            String query2 = String.format("select id, registered_at from twitter_user where login = '%s';", user.getLogin());
            ResultSet resultSet = statement.executeQuery(query2);

            Integer id = null;
            LocalDateTime registrationDate = null;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                registrationDate = resultSet.getTimestamp("registered_at").toLocalDateTime();
            }
            resultSet.close();
            user.setId(id);
            user.setRegistrationDate(registrationDate);

            String query3 = "";
            if (UserType.PERSON.equals(user.getUserType())) {
                Person person = (Person) user;
                query3 = String.format("insert into person_info (twitter_user_id, first_name, last_name, birth_date) values (%d, '%s', '%s', '%s');", id, person.getName(), person.getSurname(), person.getBirthDate());
            } else {
                Organization organization = (Organization) user;
                query3 = String.format("insert into organization_info (twitter_user_id, title, occupation, date_of_foundation) values (%d, '%s', '%s', '%s');", id, organization.getTitle(), organization.getOccupation(), organization.getDateOfFoundation());
            }
            statement.execute(query3);

            return user;
        } catch (SQLException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            return entityManager
                    .createQuery("select u from User u where u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex){
            throw new UserNotFoundException("Пользователь с ID: " + id + " не найден");
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = String.format("""
                    select
                        tu.id as id,
                        tu.login as login,
                        tu.type as type,
                        tu.password as password,
                        tu.registered_at as registered_at,
                        p.first_name as first_name,
                        p.last_name as last_name,
                        p.birth_date as birth_date,
                        o.title as title,
                        o.occupation as occupation,
                        o.date_of_foundation as date_of_foundation
                    from twitter_user tu
                        left join public.person_info p on tu.id = p.twitter_user_id
                        left join public.organization_info o on tu.id = o.twitter_user_id
                    where tu.id = %d;
                    """, id);
            ResultSet resultSet = statement.executeQuery(query);

            User user = null;
            while (resultSet.next()) {
                UserType userType = UserType.valueOf(resultSet.getString("type"));
                if (UserType.PERSON.equals(userType)) {
                    user = new Person();
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    user.setUserType(userType);
                    ((Person) user).setName(resultSet.getString("first_name"));
                    ((Person) user).setSurname(resultSet.getString("last_name"));
                    ((Person) user).setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                } else {
                    user = new Organization();
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    user.setUserType(userType);
                    ((Organization) user).setTitle(resultSet.getString("title"));
                    ((Organization) user).setOccupation(resultSet.getString("occupation"));
                    ((Organization) user).setDateOfFoundation(resultSet.getDate("date_of_foundation").toLocalDate());
                }
            }
            if (Objects.isNull(user)) {
                throw new UserNotFoundException("Пользователь с ID: " + id + " не найден");
            }
            resultSet.close();
            return user;

        } catch (SQLException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            return entityManager
                    .createQuery("select u from User u where u.login = :lgn", User.class)
                    .setParameter("lgn", login)
                    .getSingleResult();
        } catch (NoResultException ex){
            throw new UserNotFoundException("Пользователь с логином: " + login + " не найден");
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            String query = String.format("""
                    select
                        tu.id as id,
                        tu.login as login,
                        tu.type as type,
                        tu.password as password,
                        tu.registered_at as registered_at,
                        p.first_name as first_name,
                        p.last_name as last_name,
                        p.birth_date as birth_date,
                        o.title as title,
                        o.occupation as occupation,
                        o.date_of_foundation as date_of_foundation
                    from twitter_user tu
                        left join public.person_info p on tu.id = p.twitter_user_id
                        left join public.organization_info o on tu.id = o.twitter_user_id
                    where tu.login = '%s';
                    """, login);
            ResultSet resultSet = statement.executeQuery(query);

            User user = null;
            while (resultSet.next()) {
                UserType userType = UserType.valueOf(resultSet.getString("type"));
                if (UserType.PERSON.equals(userType)) {
                    user = new Person();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    user.setUserType(userType);
                    ((Person) user).setName(resultSet.getString("first_name"));
                    ((Person) user).setSurname(resultSet.getString("last_name"));
                    ((Person) user).setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                } else {
                    user = new Organization();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    user.setUserType(userType);
                    ((Organization) user).setTitle(resultSet.getString("title"));
                    ((Organization) user).setOccupation(resultSet.getString("occupation"));
                    ((Organization) user).setDateOfFoundation(resultSet.getDate("date_of_foundation").toLocalDate());
                }
            }
            if (Objects.isNull(user)) {
                throw new UserNotFoundException("Пользователь с логином: " + login + " не найден");
            }
            resultSet.close();
            return user;

        } catch (SQLException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public List<User> getAllUsers() {
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            return entityManager.createQuery("select u from User u", User.class).getResultList();
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {

        }*/

            /*String query = """
                    select
                        tu.id as id,
                        tu.login as login,
                        tu.type as type,
                        tu.password as password,
                        tu.registered_at as registered_at,
                        p.first_name as first_name,
                        p.last_name as last_name,
                        p.birth_date as birth_date,
                        o.title as title,
                        o.occupation as occupation,
                        o.date_of_foundation as date_of_foundation
                    from twitter_user tu
                        left join public.person_info p on tu.id = p.twitter_user_id
                        left join public.organization_info o on tu.id = o.twitter_user_id
                    """;
            ResultSet resultSet = statement.executeQuery(query);

            List<User> users = new LinkedList<>();
            while (resultSet.next()) {
                UserType userType = UserType.valueOf(resultSet.getString("type"));
                if (UserType.PERSON.equals(userType)) {
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setLogin(resultSet.getString("login"));
                    person.setPassword(resultSet.getString("password"));
                    person.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    person.setUserType(userType);
                    person.setName(resultSet.getString("first_name"));
                    person.setSurname(resultSet.getString("last_name"));
                    person.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                    users.add(person);
                } else {
                    Organization organization = new Organization();
                    organization.setId(resultSet.getInt("id"));
                    organization.setLogin(resultSet.getString("login"));
                    organization.setPassword(resultSet.getString("password"));
                    organization.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    organization.setUserType(userType);
                    organization.setTitle(resultSet.getString("title"));
                    organization.setOccupation(resultSet.getString("occupation"));
                    organization.setDateOfFoundation(resultSet.getDate("date_of_foundation").toLocalDate());
                    users.add(organization);
                }
            }
            return users;
        } catch (SQLException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }

    @Override
    public List<User> getAllUsersByUserType(int userType) throws UnknownUserTypeException {
        UserType type = UserType.getUserType(userType);
        try (
                EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            return entityManager
                    .createQuery("select u from User u where u.userType = :uType", User.class)
                    .setParameter("uType", type)
                    .getResultList();
        } catch (Exception ex) {
            throw new TwitterCommandException(ex.getMessage());
        }

        /*try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement statement = connection.createStatement();
        ) {
            UserType type = UserType.getUserType(userType);

            String query = String.format("""
                    select
                        tu.id as id,
                        tu.login as login,
                        tu.type as type,
                        tu.password as password,
                        tu.registered_at as registered_at,
                        p.first_name as first_name,
                        p.last_name as last_name,
                        p.birth_date as birth_date,
                        o.title as title,
                        o.occupation as occupation,
                        o.date_of_foundation as date_of_foundation
                    from twitter_user tu
                        left join public.person_info p on tu.id = p.twitter_user_id
                        left join public.organization_info o on tu.id = o.twitter_user_id
                    where tu.type = '%s';
                    """, type);
            ResultSet resultSet = statement.executeQuery(query);

            List<User> users = new LinkedList<>();
            while (resultSet.next()) {
                if (UserType.PERSON.equals(type)) {
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setLogin(resultSet.getString("login"));
                    person.setPassword(resultSet.getString("password"));
                    person.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    person.setUserType(type);
                    person.setName(resultSet.getString("first_name"));
                    person.setSurname(resultSet.getString("last_name"));
                    person.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                    users.add(person);
                } else {
                    Organization organization = new Organization();
                    organization.setId(resultSet.getInt("id"));
                    organization.setLogin(resultSet.getString("login"));
                    organization.setPassword(resultSet.getString("password"));
                    organization.setRegistrationDate(resultSet.getTimestamp("registered_at").toLocalDateTime());
                    organization.setUserType(type);
                    organization.setTitle(resultSet.getString("title"));
                    organization.setOccupation(resultSet.getString("occupation"));
                    organization.setDateOfFoundation(resultSet.getDate("date_of_foundation").toLocalDate());
                    users.add(organization);
                }
            }
            return users;
        } catch (SQLException ex) {
            throw new TwitterCommandException(ex.getMessage());
        }*/
    }
}
