FROM rockylinux:9
MAINTAINER Ulrich Cech

ENV PAYARA_ARCHIVE payara6
ENV DOMAIN_NAME domain1
ENV INSTALL_DIR /opt
RUN yum -y update \
  && yum -y install unzip \
  && yum -y install java-17-openjdk \
  && yum -y install less \
  && yum -y install wget \
  && yum clean all
RUN useradd -b /opt -m -s /bin/sh -d ${INSTALL_DIR} serveradmin && echo serveradmin:serveradmin | chpasswd && chown -R serveradmin:serveradmin /opt
RUN curl -o ${INSTALL_DIR}/${PAYARA_ARCHIVE}.zip -L https://nexus.payara.fish/repository/payara-community/fish/payara/distributions/payara/6.2024.4/payara-6.2024.4.zip \
    && unzip ${INSTALL_DIR}/${PAYARA_ARCHIVE}.zip -d ${INSTALL_DIR} \
    && rm ${INSTALL_DIR}/${PAYARA_ARCHIVE}.zip \
    && mkdir -p ${INSTALL_DIR}/${PAYARA_ARCHIVE}/glassfish/domains/domain1/autodeploy/bundles \
    && chown -R serveradmin:serveradmin /opt \
    && chmod -R a+rw /opt
ENV TZ="UTC"
ENV PAYARA_HOME ${INSTALL_DIR}/${PAYARA_ARCHIVE}/glassfish
ENV DEPLOYMENT_DIR ${INSTALL_DIR}/deploy
RUN mkdir ${DEPLOYMENT_DIR}
WORKDIR ${PAYARA_HOME}/bin
ADD start.sh .
RUN chmod a+x start.sh && chown serveradmin:serveradmin start.sh
USER serveradmin
ENTRYPOINT ./start.sh
EXPOSE 8080

RUN ${PAYARA_HOME}/bin/asadmin start-domain ${DOMAIN_NAME} && \
    ${PAYARA_HOME}/bin/asadmin set-hazelcast-configuration --enabled=false --dynamic=true && \
    ${PAYARA_HOME}/bin/asadmin delete-jvm-options "-Dcom.sun.enterprise.security.httpsOutboundKeyAlias=s1as" && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options -Duser.language=de && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options -Duser.country=DE && \
    ${PAYARA_HOME}/bin/asadmin delete-jvm-options -Xmx512m && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options -Duser.timezone=UTC && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options '-XX\:+UseContainerSupport' && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options '-XX\:InitialRAMPercentage=70.0' && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options '-XX\:MaxRAMPercentage=90.0' && \
    ${PAYARA_HOME}/bin/asadmin create-jvm-options '-XX\:+ExitOnOutOfMemoryError' && \
    ${PAYARA_HOME}/bin/asadmin set configs.config.server-config.admin-service.das-config.dynamic-reload-enabled=false configs.config.server-config.admin-service.das-config.autodeploy-enabled=false && \
    ${PAYARA_HOME}/bin/asadmin stop-all-domains
