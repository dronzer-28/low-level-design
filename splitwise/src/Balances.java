package splitwise.src;

import java.util.List;

/**
 * Balances
 */
public interface Balances {
    public static final AppConfig appConfig = AppConfig.getInstance();
    
    public List<String> getBalances();
}

// interface Balances:
//      + appConfig: AppConfig
//      + getBalances() -> List<String>
