# dropwizard-myblog
A blog backends using dropwizard

## Prepare
1. Maven 3.1+
2. Java 1.8+
3. Dropwizard 0.9+
4. PostgreSQL 9.3.5

## Develop env setup
### Idea
1. `clone` and import project.
2. Run `mvn clean install`.
3. Create user `admin` width password `admin` in PostgreSQL. (Or update username and password in blog-debug.yml)
4. Create database `myblog` in PostgreSQL in public schema. (Or update db name in blog-debug.yml to match this name)
5. In `Edit configruations`, add a `Application`, type in `com.zs.blog.BlogApplication` as __Main class__ and `db migrate blog-debug.yml` as __Program arguments__.
6. In `Edit configruations`, add a `Application`, type in `com.zs.blog.BlogApplication` as __Main class__ and `server blog-debug.yml` as __Program arguments__.

### Eclipse
TBD.