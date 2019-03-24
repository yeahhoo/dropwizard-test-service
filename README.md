# Potato

How to start the Potato application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/potato-service-1.0-SNAPSHOT.jar server config.yml`

In case of any errors in log try to remove JaxB dependencies from Maven POM.
It seems that in java 9 JaxB stopped being shipped with JRE by default so it should be included directly now.
Perhaps this problem may appear if you are on java 8 since there might be a conflict (but I guess everything should be fine).

3. If you want to run it in debug mode use the command as follows:

```bash
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/potato-service-1.0-SNAPSHOT.jar server config.yml
```

API Usage (curl on Windows)
---

1. Extract all data
```bash
curl -X GET http://localhost:8080/potatobags/list/{numberOfBags}
```

2. Extract default number of entries
```bash
curl -X GET http://localhost:8080/potatobags/list/
```

3. Init couple of test bags to check
```bash
curl -X POST http://localhost:8080/potatobags/init
```

4. Store new bag (must be no id).
```bash
curl -X POST -H "content-type: application/json" -d "{\"capacity\":14,\"supplier\":\"Owel\",\"date\":\"2019-03-23T19:59:41.394Z\",\"price\":5}" http://localhost:8080/potatobags
```

5. Edit a bag (must be id).
```bash
curl -X PUT -H "content-type: application/json" -d "{\"id\":\"0\",\"capacity\":14,\"supplier\":\"Owel\",\"date\":\"2019-03-23T19:59:41.394Z\",\"price\":5}" http://localhost:8080/potatobags
```

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
