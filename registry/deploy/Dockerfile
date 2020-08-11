FROM registry.cn-shenzhen.aliyuncs.com/sunchs/store:java-system-v1.0

ADD registry/target/registry.jar /app.jar

ENTRYPOINT [ "sh", "-c", "java -Dfile.encoding=UTF8 -Duser.timezone=GMT+08 -jar /app.jar" ]