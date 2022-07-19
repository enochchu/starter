#/bin/bash

ELASTICSEARCH_VERSION=8.3.2

wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-${ELASTICSEARCH_VERSION}-amd64.deb 
mv elasticsearch-${ELASTICSEARCH_VERSION}-amd64.deb ./data/elasticsearch-${ELASTICSEARCH_VERSION}-amd64.deb