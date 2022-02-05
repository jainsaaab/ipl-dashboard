# IPL Dashboard
Full stack project using **React** framework for front-end and **spring-boot** for back-end.
- spring batch is used to load the data from csv dataset to database.
- Used Spring data JPA to access database.
- Used JavaMail API to send error emails.
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
--env IPLDASHBOARD_ERROR_EMAIL_USERNAME=$IPLDASHBOARD_ERROR_EMAIL_USERNAME \
--env IPLDASHBOARD_ERROR_EMAIL_PASSWORD=$IPLDASHBOARD_ERROR_EMAIL_PASSWORD \
--env IPLDASHBOARD_ERROR_EMAIL_SENDTO=$IPLDASHBOARD_ERROR_EMAIL_SENDTO \
-p 8080:8080 \
jainsaab/ipl-dashboard
```
---

To use error event e-mail functionality please define following **environment variables** (username has to be a gmail account).
  - IPLDASHBOARD_ERROR_EMAIL_USERNAME (gmail username from which emails will be sent)
  - IPLDASHBOARD_ERROR_EMAIL_PASSWORD
  - IPLDASHBOARD_ERROR_EMAIL_SENDTO (can be list of emails seperated by commas)

---

### TODO

- [x] Containerize the service.
- [ ] Deploy the code in AWS.
- [ ] Create a seperate service for sending e-mails.