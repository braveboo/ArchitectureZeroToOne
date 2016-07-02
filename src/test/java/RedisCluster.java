import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lb on 2016/6/30.
 */
public class RedisCluster {

    public static  void main(String[] args){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

        //Jedis Cluster will attempt to discover cluster nodes automatically

        jedisClusterNodes.add(new HostAndPort("192.168.1.119",7001));

        jedisClusterNodes.add(new HostAndPort("192.168.1.119",7002));

        jedisClusterNodes.add(new HostAndPort("192.168.1.119",7003));

        jedisClusterNodes.add(new HostAndPort("192.168.1.119",7004));

        jedisClusterNodes.add(new HostAndPort("192.168.1.119",7005));

        jedisClusterNodes.add(new HostAndPort("192.168.1.119",7006));

        JedisCluster jc = new JedisCluster(jedisClusterNodes);

        jc.set("foo","bar");

        jc.get("foo");
    }
}
