## Client Credentials
1) Solicitar AccessToken: curl localhost:8080/oauth/token -dgrant_type=client_credentials -dscope=transfer-money -dclient_id=clientid -dclient_secret=secret

## Authorization Code
1) Acessar via Navegador http://localhost:8080/oauth/authorize?grant_type=authorization_code&response_type=code&client_id=clientid&state=1234
2) Copiar Code retornado
3) Solicitar AcessToken: curl localhost:8080/oauth/token -dgrant_type=authorization_code -dcode=ZiI2OK -dclient_id=clientid -dclient_secret=secret

## Password
1) Solicitar AccessToken: curl localhost:8080/oauth/token -dgrant_type=password -dscope=transfer-money -dusername=user -dpassword=pass -dclient_id=clientid -dclient_secret=secret

## Implicit
1) Solicitar o access token: http://localhost:8080/oauth/authorize?grant_type=implicit&response_type=token&client_id=clientid&state=1234&redirect_uri=http://localhost:8080/oauth/login/client-app&scope=transfer-money




