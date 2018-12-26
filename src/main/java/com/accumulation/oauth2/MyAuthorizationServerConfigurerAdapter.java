package com.accumulation.oauth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import sun.security.util.Password;

import javax.sql.DataSource;

/**
 * @Description:
 * ClientDetailsServiceConfigurer：用来配置客户端详情信息，一般使用数据库来存储或读取应用配置的详情信息（client_id ，client_secret，redirect_uri 等配置信息）。
 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全与权限访问。
 * AuthorizationServerEndpointsConfigurer：用来配置授权以及令牌（Token）的访问端点和令牌服务（比如：配置令牌的签名与存储方式）
 * @author: HU
 * @date: 2018/9/21 9:59
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    //@Autowired*/
    //private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private DataSource dataSource;

    //使用oAuth2.0默认的clinet配置，
    @Bean
    public ClientDetailsService clientDetailsService() {

        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.withClientDetails(clientDetailsService());

        clients.inMemory()
                .withClient("client") // client_id
                .secret("secret") // client_secret
                .authorizedGrantTypes("password") // 该client允许的授权类型
                .scopes("app"); // 允许的授权范围
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*redis存储token*/
        /*endpoints.authenticationManager(authenticationManager).tokenStore(new MyRedisTokenStore(redisConnection));*/
        /*jwt方式*/
		/*endpoints.accessTokenConverter(jwtAccessTokenConverter());
		endpoints.authenticationManager(authenticationManager).tokenStore(new JwtTokenStore(jwtAccessTokenConverter()));*/
        /*jwt方式+redis存储token*/
        // endpoints.accessTokenConverter(jwtAccessTokenConverter());
        //endpoints.authenticationManager(authenticationManager).tokenStore(new MyRedisTokenStore(redisConnection));
        /*普通*/
     endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
