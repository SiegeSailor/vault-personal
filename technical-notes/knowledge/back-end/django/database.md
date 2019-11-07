# Database

## Config

Use `dig` and `trace` **classtest.lejourneyapp.com** to find out the host for databases. **52.77.174.67** is the Test database.

Then go to `/etc/mysql/mysql.conf.d` to find the port. Comment _bind-address = 127.0.0.1_ if you want to connect to the database from external. Then set `ALLOWED_HOSTS`  from `[]` to `['*']`.

