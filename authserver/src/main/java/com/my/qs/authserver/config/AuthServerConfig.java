package com.my.qs.authserver.config;

import com.my.qs.authserver.OAuth2ApprovalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthorizationCodeServices authorizationCodeServices;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	private UserDetailsService userDetailsService;



	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//			.withClient("client_id")
//			.secret(passwordEncoder.encode("123456"))
//			.redirectUris("http://localhost:8080/callback")
//			.authorizedGrantTypes("authorization_code", "password")
//			.scopes("all", "abc");

		clients.withClientDetails(clientDetailsService);

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
//			.authorizationCodeServices(authorizationCodeServices)
			.tokenStore(tokenStore)
			.accessTokenConverter(jwtAccessTokenConverter())
			.userDetailsService(userDetailsService);
//			.userApprovalHandler(userApprovalHandler);

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients()
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("permitAll()");
	}


	@Bean
	public TokenStore tokenStore(){
//		return new JdbcTokenStore(dataSource);
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "jwtjwt".toCharArray());
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
		return converter;
	}


	@Bean
	public ClientDetailsService clientDetailsService(){
		return new JdbcClientDetailsService(dataSource);
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices(){
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	@Bean
	public OAuth2RequestFactory oAuth2RequestFactory(){
		return new DefaultOAuth2RequestFactory(clientDetailsService);
	}

	@Bean
	public UserApprovalHandler userApprovalHandler(){
		OAuth2ApprovalHandler oAuth2ApprovalHandler = new OAuth2ApprovalHandler();
		oAuth2ApprovalHandler.setClientDetailsService(clientDetailsService);
		oAuth2ApprovalHandler.setRequestFactory(oAuth2RequestFactory());
		oAuth2ApprovalHandler.setTokenStore(tokenStore);
		return oAuth2ApprovalHandler;
	}

	@Bean
	public UserDetailsService userDetailsService(){
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

				User user = new User(s, passwordEncoder.encode(s), AuthorityUtils.createAuthorityList("ADMIN"));
				return user;
			}
		};
	}
}
