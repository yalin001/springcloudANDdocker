package docker;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class Test1 {
	@Test
	public void test1() {
		Jedis j = new Jedis("192.168.74.138", 7000);
		j.set("key1", "value1");
		
		String v = j.get("key1");
		System.out.println(v);
		
		j.close();
	}
	
	@Test
	public void test2() {
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(500);
		cfg.setMaxIdle(20);
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.74.138", 7000));
		shards.add(new JedisShardInfo("192.168.74.138", 7001));
		shards.add(new JedisShardInfo("192.168.74.138", 7002));
		
		ShardedJedisPool pool = new ShardedJedisPool(cfg, shards);
		
		ShardedJedis j = pool.getResource();
		for (int i = 0; i < 100; i++) {
			j.set("key"+i, "value"+i);
		}
		
		pool.close();
	}
}