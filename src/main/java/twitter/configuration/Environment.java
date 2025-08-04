package twitter.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*@Component*/
public class Environment {

    private final Map<String, Object> env;
    private String applicationProfile;

    /*@Injection*/
    /*public Environment() {
        this.env = new HashMap<>();
        init();
    }*/

    public Environment(Map<String, Object> env) {
        this.env = env;
    }

    public String getApplicationProfile() {
        return applicationProfile;
    }

    public void setApplicationProfile(String applicationProfile) {
        this.applicationProfile = applicationProfile;
    }

    public void addProperties(Map<String, Object> newProperties) {
        this.env.putAll(newProperties);
    }

    /*private void init() {
        try (
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            reader.lines().forEach(line -> {
                if(Objects.nonNull(line) && !line.isBlank()){
                    String key = line.substring(0, line.indexOf("="));
                    Object value = line.substring(line.indexOf("=") + 1);
                    this.env.put(key, value);
                }
            });
        }catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }*/

    public Object get(String key) {
        return env.get(key);
    }
}
