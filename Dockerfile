FROM sssllc/centos7.2-jdk1.8
ADD target/spring-boot-admin-1.0.0.jar  /workspace/spring-boot-admin-1.0.0.jar
#RUN sh -c 'touch /spring-boot-admin-1.0.0.jar'
ENTRYPOINT [ "sh", "-c", "java -Xmx2g -Xms2g -Djava.security.egd=file:/dev/./urandom -jar /workspace/spring-boot-admin-1.0.0.jar --spring.profiles.active=test"]