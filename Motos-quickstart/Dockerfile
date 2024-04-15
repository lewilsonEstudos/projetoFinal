
FROM registry.access.redhat.com/ubi8/openjdk-17:1.18


COPY  target/quarkus-app/lib/ /deployments/lib/
COPY  target/quarkus-app/*.jar /deployments/
COPY  target/quarkus-app/app/ /deployments/app/
COPY  target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]