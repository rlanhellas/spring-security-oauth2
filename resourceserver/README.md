## Acessando recurso protegido
- Capture o acesstoken do IdP
- Faça a chamada ao endpoint desejado: curl -H "Authorization: Bearer TOKEN" http://localhost:9090/needscope

## Checando token manualmente
curl localhost:8080/oauth/check_token/?token=TOKEN
