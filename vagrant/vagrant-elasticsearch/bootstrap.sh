#!/usr/bin/env bash

# Change to script directory
sd=`dirname $0`
cd $sd

# Update apt-get
apt-get -y update

# Update Ubuntu
apt-get -y upgrade
apt-get -y dist-upgrade

# https apt
sudo apt-get -y install apt-transport-https

# Install Java
apt-get -y install openjdk-8-jre-headless

# Install Elasticsearch
# wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-8.3.2-amd64.deb
# wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-8.3.2-amd64.deb.sha512
# shasum -a 512 -c elasticsearch-8.3.2-amd64.deb.sha512 
# sudo dpkg -i elasticsearch-8.3.2-amd64.deb

# shasum -a 512 -c /data/elasticsearch-8.3.2-amd64.deb.sha512 
dpkg -i /data/elasticsearch-8.3.2-amd64.deb

# Elasticsearch networking config
# echo "
# network.bind_host: 0
# network.host: 0.0.0.0" >> /etc/elasticsearch/elasticsearch.yml
# sudo sed -i -e '$a\' /etc/elasticsearch/elasticsearch.yml
# sudo sed -i -e '$a\' /etc/elasticsearch/elasticsearch.yml
cp /etc/elasticsearch/elasticsearch.yml /etc/elasticsearch/elasticsearch.yml.original
cp /data/elasticsearch.yml /etc/elasticsearch/elasticsearch.yml

sudo chown -R elasticsearch:elasticsearch /usr/share/elasticsearch
sudo chown -R elasticsearch:elasticsearch /var/lib/elasticsearch
sudo chown -R elasticsearch:elasticsearch /var/log/elasticsearch


service elasticsearch restart

# Install Kibana
wget -qO - https://packages.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
echo "deb http://packages.elastic.co/kibana/4.6/debian stable main" | sudo tee -a /etc/apt/sources.list
sudo apt-get update && sudo apt-get install kibana
sudo update-rc.d kibana defaults 95 1

ufw allow 9200
ufw allow 5601

service ufw restart
service kibana restart