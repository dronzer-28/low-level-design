package splitwise.src;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User
 */
public class User {

    String userId;
    String name;
    String email;
    String mobNo;
    Map<String,Double> balances = new ConcurrentHashMap<String, Double>();
    
    public User(String userId, String name, String email, String mobNo) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobNo = mobNo;
    }

    public Map<String, Double> getBalances() {
        return balances;
    }

    public synchronized void addTransaction(String userId, Double amt){
        if(balances.containsKey(userId)){
            balances.put(userId, balances.get(userId)+amt);
        } else{
            balances.put(userId, amt);
        }
    }

    public String getUserId(){
        return userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobNo() {
        return mobNo;
    }
    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

}