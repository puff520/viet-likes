package com.likes.common.config;

import com.likes.common.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.redis.host}")
	private String nodes;

	@Value("${spring.redis.port}")
	private String port;
	@Value("${spring.redis.password}")
	private String password;

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		SingleServerConfig dd = config.useSingleServer();
		dd.setAddress("redis://" + nodes + ":" + port);
		/*dd.set
		ClusterServersConfig serverConfig = config.useClusterServers();
		serverConfig.setScanInterval(2000); // 集群状态扫描间隔时间，单位是毫秒
		if (StringUtils.isNotEmpty(nodes)) {
			String str[] = nodes.split(",");
			for (int i = 0; i < str.length; i++) {
				logger.info("redis addr : [{}]", str[i]);
				if (StringUtils.isNotEmpty(str[i])) {
					serverConfig.addNodeAddress("redis://" + str[i].trim());//// use "rediss://" for SSL connection
				}
			}
		} else {
			logger.error("REDIS集群地址为空,系统退出");
			System.exit(0);
		}*/

		// 设置密码
		if (StringUtils.isNotEmpty(password)) {
			dd.setPassword(password);
		}
		return Redisson.create(config);
	}


}
