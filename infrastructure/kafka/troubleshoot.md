if you are getting cert issues with the confluent repo on mvn commands, the following bypasses ssl verification

mvn clean install -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true