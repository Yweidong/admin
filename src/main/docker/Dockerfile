FROM java:8
VOLUME /tmp
VOLUME /log
MAINTAINER wei_tung
ADD admin-1.0.jar /admin.jar
RUN bash -c 'echo "Asia/Shanghai" > /etc/timezone'
EXPOSE 8588
ENV JAVA_OPTS="\
-server \
-Xmx256m \
-Xms256m"
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd-file:/dev/.urandom -jar /admin.jar
