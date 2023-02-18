# Blog API in Spring Boot

## Development

### Database Setup

#### MySQL

To log in to MySQL , run below command

```bash
# if root has no password
mysql -u root

#if root has password    
mysql -u root -p
# Then enter the password    
```

Create Database, User and Password

```mysql
CREATE DATABASE blog;
CREATE USER 'blog_user'@'localhost' IDENTIFIED BY 'blog_password';
GRANT ALL PRIVILEGES ON blog.* TO 'blog_user'@'localhost';
```

Check it works by logging into the database

```bash
mysql blog -u blog_user -p
```


#### PostgresSQL

To log in to Postgres, run the following commands :-

##### For MacOS

```bash
psql postgres 
```

##### For Linus

```bash
sudo -u postgres psql postgres
```

1. Create Database , User and Password

```postgresql
CREATE DATABASE blog;
CREATE USER blog_user with ENCRYPTED PASSWORD 'blog_password';
GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user;
```   
   
  

