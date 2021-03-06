FROM 10.11.152.38:5000/centos/jdk8
VOLUME /tmp
ADD target/homestay-1.0.0.jar  /workspace/spring-boot-admin-1.0.0.jar
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone 
ENV JAVA_OPTS="dev"
ENTRYPOINT [ "sh", "-c", "java -Xmx2g -Xms2g -Djava.security.egd=file:/dev/./urandom -jar /workspace/homestay-1.0.0.jar --spring.profiles.active=dev"]