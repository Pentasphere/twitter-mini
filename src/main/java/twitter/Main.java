package twitter;

import org.flywaydb.core.Flyway;
import twitter.configuration.ComponentFactory;
import twitter.configuration.Environment;
import twitter.configuration.EnvironmentBuilder;
import twitter.entity.user.Person;
import twitter.entity.user.User;
import twitter.entity.user.UserType;
import twitter.runner.ApplicationRunner;
import twitter.runner.impl.CommandLineApplicationRunner;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) /*throws Exception*/ {

        /*try{
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/twitter_mini_db", "postgres", "postgres");
            System.out.println("Connection established");
            Statement statement = connection.createStatement();

            String query1 = "insert into twitter_user (login, password, type) values ('vadim', '12345', 'PERSON');";
            statement.execute(query1);

            String query11 = "insert into twitter_user (login, password, type) values ('aidar', '12345', 'PERSON');";
            statement.execute(query11);

            String query2 = "select * from twitter_user";
            ResultSet result = statement.executeQuery(query2);

            List<User> users = new LinkedList<>();
            while(result.next()){
                User user = new Person();
                user.setId(result.getInt("id"));
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                user.setRegistrationDate(LocalDateTime.parse(result.getString("registered_at").replace(" ", "T")));
                user.setUserType(UserType.valueOf(result.getString("type")));
                users.add(user);
            }

            for (User user : users) {
                System.out.println(user.beautify());
            }

            result.close();
            statement.close();
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }*/

        /*System.out.println(Arrays.toString(args));*/
        final String profilePrefix = "application.profile=";

        String applicationProfile = "default";
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.startsWith(profilePrefix)) {
                    applicationProfile = arg.substring(profilePrefix.length());
                }
            }
        }

        Environment environment = EnvironmentBuilder
                .buildEnvironment()
                .withApplicationProfile(applicationProfile)
                .build();

        /*Environment environment = EnvironmentBuilder.buildEnvironment().build();*/

        /*ComponentFactory factory = new ComponentFactory(Main.class);*/
        ComponentFactory factory = new ComponentFactory(Main.class, environment);
        factory.configure();

        Flyway flyway = factory.getComponent(Flyway.class);
        flyway.migrate();

        /*Flyway flyway = Flyway
                .configure()
                .driver("org.postgresql.Driver")
                .dataSource("jdbc:postgresql://localhost:5432/twitter_mini_db", "postgres", "postgres")
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .outOfOrder(true)
                .load();*/
//        flyway.migrate();

        /*CommandLineListener listener = factory.getComponent(CommandLineListener.class);
        listener.listen();*/
        ApplicationRunner runner = factory.getComponent(ApplicationRunner.class);
        runner.run();

        /*ComponentFactory factory = new ComponentFactory(Main.class.getPackageName());
        factory.configure();
        CommandLineListener listener = factory.getComponent(CommandLineListener.class);
        listener.listen();*/

        /*CommandLineListener commandLineListener = CommandLineListener.getInstance();
        commandLineListener.listen();*/
    }
}