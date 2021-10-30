- Build command "mvn clean install -DskipTests"
- Image Build Command "docker build -t tracing-service ."
- Start container Command "docker-compose up -d"

- Writes Activity Trace of API Calls into cassandra