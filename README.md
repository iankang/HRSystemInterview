# HRSystemInterview
spring boot api for multiple choice questions assessments.

## setup
for end to end setup:

```sh
cd HRSystemInterview
sudo docker-compose up -d .
```
for db setup only:
```sh
cd HRSystemInterview
sudo docker-compose up -d dbPostgresql
```
The application is exposed on port `8089`

To access the swagger-ui documentation, navigate to `http://localhost:8089/swagger-ui/index.html`
