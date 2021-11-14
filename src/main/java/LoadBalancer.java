import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LoadBalancer implements ILoadBalancer {
    private List<String> servers = new ArrayList<>();
    private static int Max_SIZE_SUPPORTED;
    Random ran = new Random();
    private volatile AtomicInteger sizeOfLB = new AtomicInteger(0);

    public LoadBalancer(int sizeofLB) {
        Max_SIZE_SUPPORTED = sizeofLB;
    }

    public String getServerIP() {
        int randomIndex = ran.nextInt(3);
        return servers.get(randomIndex);
    }

    public boolean registerServers(String IP) {
        if (sizeOfLB.get() < Max_SIZE_SUPPORTED && !servers.contains(IP)) {
            sizeOfLB.getAndIncrement();
            servers.add(IP);
            return true;
        }
        return false;
    }

}
