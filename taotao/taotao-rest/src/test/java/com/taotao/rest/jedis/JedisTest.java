package com.taotao.rest.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	@Test
	public void testJedisSingle(){
		//创建一个jedis对象
		Jedis jedis = new Jedis("192.168.182.131", 6379);
		//调用jedis对象的方法，方法名和redis的命令一致
		jedis.set("key1", "jedis test");
		String str = jedis.get("key1");
		System.out.println(str);
		//关闭jedis
		jedis.close();
	}
	
	/**
	 * redis连接池
	 */
	@Test
	public void testJedisPool(){
		//创建jedis链接池
		JedisPool pool = new JedisPool("192.168.182.131",6379);
		
		//从连接池中获得Jedis对象
		Jedis jedis = pool.getResource();
		String str = jedis.get("key1");
		System.out.println(str);
		//关闭jedis
		jedis.close();
	}
	
	@Test
	public void testJedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.182.129", 7001));
		nodes.add(new HostAndPort("192.168.182.129", 7002));
		nodes.add(new HostAndPort("192.168.182.129", 7003));
		nodes.add(new HostAndPort("192.168.182.129", 7004));
		nodes.add(new HostAndPort("192.168.182.129", 7005));
		nodes.add(new HostAndPort("192.168.182.129", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		//cluster.set("key2", "1000");
		cluster.set("name", "zhangsan");
		String str= cluster.get("key1");
		System.out.println(str);
		cluster.close();
	}
	
	/**
	 * 测试单机版
	 */
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool)applicationContext.getBean("jedisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster)applicationContext.getBean("jedisClient");
		String string = cluster.get("key1");
		System.out.println(string);
	}
}
