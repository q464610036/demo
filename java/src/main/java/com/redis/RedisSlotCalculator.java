package com.redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.JedisClusterCRC16;

public class RedisSlotCalculator {

    public static void main(String[] args) {
        String key = "myRedisKey";
        int slot = getSlot(key);
        System.out.println("The slot for key '" + key + "' is: " + slot);
        String redisNode = getRedisNodeSlot(slot);
        System.out.println("The Redis node for this slot is: " + redisNode);
    }

    public static int getSlot(String key) {
        // 计算hash值并取模
        int hash = JedisClusterCRC16.getCRC16(key);
        return (hash & 0x3FFF); // 16384 = 2^14
    }

    public static String getRedisNodeSlot(int slot) {
        // 这里可以根据实际集群情况返回对应节点
        // 这是一个简化例子
        if (slot < 8192) {
            return "Node 1 (0-8191)";
        } else {
            return "Node 2 (8192-16383)";
        }
    }
}
