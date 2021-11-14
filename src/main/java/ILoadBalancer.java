public interface ILoadBalancer {

    String getServerIP();
    boolean registerServers(String IP);
}
