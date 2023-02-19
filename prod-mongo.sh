docker-compose -f test-compose.yml down \
	&& docker-compose -f mongo-compose.yml up -d \
	&& docker ps

