package splitwise.src;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * AppConfig
 */
public class AppConfig {
    Map<String, User> users = new HashMap<String, User>();
    static AppConfig instance;

    private AppConfig(){

    }

    public static synchronized AppConfig getInstance(){
        if(instance == null){
            instance = new AppConfig();
        }
        return instance;
    }

    public boolean isUserValid(String userId){
        if(users.containsKey(userId))
            return true;
        return false;
    }

    public User getUserById(String userId){
        if(!isUserValid(userId)){
            throw new IllegalArgumentException(String.format("Can't get user with userId: %s", userId));
        }
        return users.get(userId);
    }
    public Map<String, User> getAllUsers(){
        return Collections.unmodifiableMap(users);
    }

    public void addUser(User user){
        if(isUserValid(user.getUserId())){
            throw new IllegalArgumentException("User already exists!");
        }
        users.put(user.getUserId(), user);
    }
}