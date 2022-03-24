FROM nginx
COPY content /etc/nginx/html
RUN rm -rf /etc/nginx/sites-enabled/*
COPY nginx.conf /etc/nginx/sites-available/nginx.conf
COPY nginx.conf /etc/nginx/sites-enabled/nginx.conf