FROM 192.168.3.187:5000/centos/jdk8:latest
ADD /var/jenkins_home/workspace/spring-boot-admin/target/spring-boot-admin-1.0.0.jar  /workspace/spring-boot-admin-1.0.0.jar
ENTRYPOINT [ "sh", "-c", "java -Xmx2g -Xms2g -Djava.security.egd=file:/dev/./urandom -jar /workspace/spring-boot-admin-1.0.0.jar --spring.profiles.active=test"]