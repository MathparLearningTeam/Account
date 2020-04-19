FROM tomcat:9-jdk11-openjdk-slim

RUN rm -r webapps/* || true
ADD AccountModule.war /usr/local/tomcat/webapps/ROOT.war