#!/bin/sh

if [ ! -n "$1" ]; then
    echo "Usage: start.sh ( xxx.jar ... )"
    exit 1
else
    JAR_NAME="$1"
fi

if [ -z $HOST_IP ]; then
    HOST_IP=`curl http://rancher-metadata/latest/self/host/agent_ip 2>/dev/null`
fi

if [ -z $PRIMARY_IP ]; then
    PRIMARY_IP=`curl http://rancher-metadata/latest/self/container/primary_ip 2>/dev/null`
fi

if [ -z $SERVICE_NAME ]; then
    SERVICE_NAME=`curl http://rancher-metadata/latest/self/service/name 2>/dev/null`
fi

if [ -z $ENV_NAME ]; then
    ENV_NAME=`curl http://rancher-metadata/latest/self/stack/environment_name 2>/dev/null`
fi

if [ -z $CONTAINER_NAME ]; then
    CONTAINER_NAME=`curl http://rancher-metadata/latest/self/container/name 2>/dev/null`
fi

if [ -z $CONTAINER_URL ]; then
    CONTAINER_URL="http://${CONTAINER_NAME}:8090"
fi

JAVA_CMD=`which java`

JAVA_OPTS="-server $JAVA_OPTS"
JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom $JAVA_OPTS"
JAVA_OPTS="-DHOST_IP=$HOST_IP $JAVA_OPTS"
JAVA_OPTS="-DPRIMARY_IP=$PRIMARY_IP $JAVA_OPTS"
JAVA_OPTS="-DENV_NAME=$ENV_NAME $JAVA_OPTS"
JAVA_OPTS="-DSERVICE_NAME=$SERVICE_NAME $JAVA_OPTS"
JAVA_OPTS="-Duser.timezone=GMT+08 $JAVA_OPTS"
JAVA_OPTS="-DCONTAINER_NAME=$CONTAINER_NAME $JAVA_OPTS"
JAVA_OPTS="-DCONTAINER_URL=$CONTAINER_URL $JAVA_OPTS"

SPRING_OPTS="$SPRING_OPTS"

if [ "test-env" = "$ENV_NAME" ]; then
    SPRING_OPTS="--spring.profiles.active=test $SPRING_OPTS"
    if [ ! -n "$HEAP_PARAM" ]; then
        JAVA_OPTS=" -Xmx128m  -Xms128m $JAVA_OPTS"
    else
        JAVA_OPTS=" $HEAP_PARAM $JAVA_OPTS"
    fi
elif [ "ktv-app" = "$ENV_NAME" ]; then
    SPRING_OPTS="--spring.profiles.active=prod $SPRING_OPTS"
    if [ ! -n "$HEAP_PARAM" ]; then
        JAVA_OPTS=" -Xmx256m  -Xms256m $JAVA_OPTS"
    else
        JAVA_OPTS=" $HEAP_PARAM $JAVA_OPTS"
    fi
fi

echo "    ENV_NAME : $ENV_NAME"
echo "SERVICE_NAME : $SERVICE_NAME"
echo "     HOST_IP : $HOST_IP"
echo "  PRIMARY_IP : $PRIMARY_IP"
echo "    JAR_NAME : $JAR_NAME"
echo "    JAVA_CMD : $JAVA_CMD"
echo "   JAVA_OPTS : $JAVA_OPTS"
echo " SPRING_OPTS : $SPRING_OPTS"

exec "$JAVA_CMD" ${JAVA_OPTS} -jar "$@" ${SPRING_OPTS}
