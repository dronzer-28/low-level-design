package splitwise.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AllUserBalances
 */
public class AllUserBalances implements Balances{
    Map<String, User> users = appConfig.getAllUsers();

    @Override
    public List<String> getBalances() {
        List<String> balances = new ArrayList<String>();
        Map<String, Boolean> visited = new HashMap<>();
        for(String userId: users.keySet()){
            visited.put(userId, true);
            User user = appConfig.getUserById(userId);
            Map<String, Double> userBalances = user.getBalances();
            for(String friendId: userBalances.keySet()){
                User userFriend = appConfig.getUserById(friendId);
                if(visited.containsKey(friendId)) 
                    continue;
                if(userBalances.get(friendId)>0.00){
                    balances.add(String.format("%s owes %s: %.2f", userFriend.getName(), user.getName(), userBalances.get(friendId)));
                }
                else{
                    balances.add(String.format("%s owes %s: %.2f", user.getName(), userFriend.getName(), (-1.00f)*userBalances.get(friendId)));
                }
            }
        }
        return balances;
    }
}