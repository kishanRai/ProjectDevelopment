# Blog API in Spring Boot

## Development

### Database Setup

#### MySQL

To log in to MySQL , run below command

```bash
mysql -u root -p
# Then enter the password    
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
   
  

