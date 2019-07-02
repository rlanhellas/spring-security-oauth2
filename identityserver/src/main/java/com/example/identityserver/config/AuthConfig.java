package com.example.identityserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * Usado para o Password Credentials Flow para validar as credenciais do usuário informado.
     * Client-Credentials não precisa pois não informa credenciais de usuário, já o Authorization e Implicit Flow fazem a validação das credenciais
     * no login do usuário, por isso também não precisam do AuthenticationManager.
     */
    private final AuthenticationManager authenticationManager;

    public AuthConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientid")
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("authorization_code", "client_credentials", "password", "implicit")
                .scopes("transfer-money", "view-current-value")
                .redirectUris("http://localhost:8080/oauth/login/client-app"); //Essencial quando usamos Authorization Code Flow
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
        * Permite o acesso ao endpoint oauth/check_token sem autenticação
        * */
        security.checkTokenAccess("permitAll()")

                /*
                 * Possibilita que as credenciais (clientid e secret) sejam passados no body da requisição
                 * e não no header
                 * */
                .allowFormAuthenticationForClients();
    }

    /**
     * Precisamos para uso do Password Flow
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).
                tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * A mudança do TokenStore e JwtAccessTokenConverter para JWT permite que o AuthorizationServer consiga
     * retornar um token no formato JWT, sem isso ele retornaria um token em formato "plain-text".
     */
    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123");
        return jwtAccessTokenConverter;
    }
}
