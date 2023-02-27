rm certs/*

htpasswd -bBc certs/htpasswd.local.registry mike-dev SecureSecrets

openssl req \
  -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key \
  -addext "subjectAltName = DNS:my.local.registry" \
  -x509 -days 365 -out certs/domain.crt

openssl x509 -in certs/domain.crt -noout -text
