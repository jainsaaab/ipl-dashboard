# IPL Dashboard
Full stack project using **React** framework for front-end and **spring-boot** for back-end.
- spring batch is used to load the data from csv dataset to database.
- Used Spring data JPA to access database.
- Used JavaMail API to send error emails.
- React, react-hooks, react-router is used in front-end.
- hsqldb is used as in-memory database

---

To use error event e-mail functionality please define following **environment variables** (username has to be a gmail account).
  - IPLDASHBOARD_ERROR_EMAIL_USERNAME (gmail username from which emails will be sent)
  - IPLDASHBOARD_ERROR_EMAIL_PASSWORD
  - IPLDASHBOARD_ERROR_EMAIL_SENDTO (can be list of emails seperated by commas)

---

### TODO

- [ ] Containerize the service.
- [ ] Deploy the code in AWS.
- [ ] Create a seperate service for sending mails.