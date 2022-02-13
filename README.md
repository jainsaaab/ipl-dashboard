# IPL Dashboard
Full stack project using **React** framework for front-end and **spring-boot** for back-end.
- spring batch is used to load the data from csv dataset to database.
- Used Spring data JPA to access database.
- ~~Used JavaMail API to send error emails.~~
  - created new service [email-sender](https://github.com/jainsaaab/email-sender) for sending mails. 
- React, react-hooks, react-router is used in front-end.
- hsqldb is used as in-memory database

---

Front-end code is located at src/frontend/
to build the front-end code cd into ./src/frontend/ and run following command
```bash
npm run build
```
this command will create a production build of front-end code and copy it to src/main/resources/public

Run following command to package the whole application
```bash
./mvnw clean package
```

Run this command to build docker image
```bash
docker build -t jainsaab/ipl-dashboard .
```

Run this command to run the docker container
```bash
sudo docker run \
--env IPLDASHBOARD_ERROR_EMAIL_SENDTO=$IPLDASHBOARD_ERROR_EMAIL_SENDTO \
-p 8080:8080 \
jainsaab/ipl-dashboard
```

Now you can access sites home page at `localhost:8080`

---

Please edit `application.properties` file to configure who to send error email notification.

---

### TODO

- [x] Containerize the service.
- [x] Create a seperate service for sending e-mails.
  - [ ] Implement circuit-breaker for send-email api call
- [ ] Deploy the code in AWS.

---

I built this project by following code with me series from Koushik Kothagal (java brains).
Thank you so much Koushik for your help.