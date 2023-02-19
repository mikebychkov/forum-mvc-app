docker-compose -f mongo-compose.yml down \
	&& docker-compose -f test-compose.yml up -d \
	&& docker ps
