package logging_framework;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogDispatcher {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void dispatch(LogRecord logRecord, List<Destination> destinations){
        if(executor.isShutdown()){
            return;
        }
        executor.submit(()->{
            for(Destination dest: destinations){
                dest.append(logRecord);
            }
        });
    }

    public void close(){
        executor.shutdown();
        
        try{
            if(executor.awaitTermination(2, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e){
            executor.shutdownNow();
        }
    }
}
