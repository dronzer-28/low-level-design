package splitwise.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UserBalances
 */
public class UserBalances implements Balances{
    User user;

    public UserBalances(String userId){
        user = appConfig.getUserById(userId);
    }

    @Override
    public List<String> getBalances() {
        Map<String, Double> userBalances = user.getBalances();
        List<String> balances = new ArrayList<String>(userBalances.size());
        for(String friendId: userBalances.keySet()){
            User userFriend = appConfig.getUserById(friendId);
            if(userBalances.get(friendId)>0.00){
                balances.add(String.format("%s owes %s: %.2f", userFriend.getName(), user.getName(), userBalances.get(friendId)));
            }
            else{
                balances.add(String.format("%s owes %s: %.2f", user.getName(), userFriend.getName(), (-1.00f)*userBalances.get(friendId)));
            }
        }
        return balances;
    }
}
