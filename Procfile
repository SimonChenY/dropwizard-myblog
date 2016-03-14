worker: java -jar target/dropwizard-blog-1.0.0-SNAPSHOT.jar db migrate blog.yml
web: java -Ddw.server.adminConnectors[0].port=$PORT -jar target/dropwizard-blog-1.0.0-SNAPSHOT.jar server blog.yml