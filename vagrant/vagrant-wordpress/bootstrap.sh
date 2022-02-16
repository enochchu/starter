#!/usr/bin/env bash

# Change to script directory
sd=`dirname $0`
cd $sd

# Update apt-get
apt-get -y update

# Update Ubuntu
apt-get -y upgrade
apt-get -y dist-upgrade

# Install Apache
apt-get -y install apache2

# Install PHP5
apt-get -y install php php-cli libapache2-mod-php7.4 php7.4-fpm

# Restart Apache
systemctl restart apache2

# Install MySQL
MYSQL_ROOT_PASSWORD=secret

MYSQL_WP_USER=wordpress
MYSQL_WP_PASSWORD=s3cr3t
WP_DB_NAME=wordpress

debconf-set-selections <<< "mysql-server mysql-server/root_password password ${MYSQL_ROOT_PASSWORD}"
debconf-set-selections <<< "mysql-server mysql-server/root_password_again password ${MYSQL_ROOT_PASSWORD}"
apt-get -y install mysql-server

# Create WordPress DB User
mysql -uroot -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE ${WP_DB_NAME} character set utf8mb4"
mysql -uroot -p${MYSQL_ROOT_PASSWORD} -e "CREATE USER '${MYSQL_WP_USER}'@'localhost' IDENTIFIED BY '${MYSQL_WP_PASSWORD}'"

systemctl restart mysql

mysql -uroot -p${MYSQL_ROOT_PASSWORD} -e "GRANT ALL PRIVILEGES ON ${WP_DB_NAME}.*  TO '${MYSQL_WP_USER}'@'localhost'"

systemctl restart mysql