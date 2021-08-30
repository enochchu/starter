FROM alpine

RUN apk add --update --no-cache apache2 php7-apache2

RUN echo "<?php phpinfo(); ?>" > /var/www/localhost/htdocs/index.php

EXPOSE 80

ENTRYPOINT ["/usr/sbin/httpd"]