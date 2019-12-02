package com.bestvike.elastic.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration //配置注解
public class Myconfig {

	@Bean
	public TransportClient client() throws UnknownHostException {
		InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("192.168.237.130"), 9300); //这里为es的tcp端口9300，而不是http端口9200

		// my-application为默认的 ,可以在es文件夹下的config->elasticsearch.yml内修改
		Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();


		TransportClient client = TransportClient.builder().settings(settings).build();
		client.addTransportAddresses(node);

		return client;

	}

}