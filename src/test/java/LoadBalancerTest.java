import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerTest {

    private static int test_size=10;
    LoadBalancer lb = new LoadBalancer(test_size);

    @org.junit.jupiter.api.Test
    void checkLBStoringUniqueAddreses() {
        assertTrue(lb.registerServers("192.31.31.31"));
        assertFalse(lb.registerServers("192.31.31.31"));
    }

    @org.junit.jupiter.api.Test
    void checkThresholdForLB() {
        IntStream.range(0, 20).forEach(i ->
        {
            if (i < 10) {
                System.out.println(i);
                boolean status=lb.registerServers("192.31.31." + i);
                System.out.println(status + " and value " + i);
                assertTrue(status);
            } else
                assertFalse(lb.registerServers("192.31.31." + i));
        });

    }

    @Test
    void getServerIP() {

        lb.registerServers("192.31.31.31");
        lb.registerServers("192.31.31.32");
        lb.registerServers("192.31.31.33");
        lb.registerServers("192.31.31.34");

        String testIp = lb.getServerIP();
        assertFalse(testIp.isBlank());
        assertTrue(testIp.contains("192.31"));
    }


}