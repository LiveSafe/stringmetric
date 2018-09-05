#!/bin/bash
mkdir -p $HOME/.livesafe
cat <<EOF > $HOME/.livesafe/credentials.properties
realm = Artifactory Realm
host = livesafe.jfrog.io
user = $LIVESAFE_ARTIFACTORY_USERNAME
password = $LIVESAFE_ARTIFACTORY_PASSWORD
EOF
