package com.my.qs.esdemo.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class elasticConfig {

	@Value("${es.host}")
	private String host;

	@Value("${es.http.port}")
	private int port;

	@Value("${es.username}")
	private String username;

	@Value("${es.password}")
	private String password;

	@Bean
	public RestHighLevelClient restHighLevelClient(){
		BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
		basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		return new RestHighLevelClient(
				RestClient.builder(new HttpHost(host, port))
						.setHttpClientConfigCallback(
								new RestClientBuilder.HttpClientConfigCallback() {
									@Override
									public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
										return httpAsyncClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
									}
								}
						)
		);
	}
}
