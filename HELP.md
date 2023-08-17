# Techs used
- CRUD Operations
- Batch Updates
- RowMapper(Externalized in a util package for reuse purposes)
- SimpleJdbcTemplate
- JdbcTemplate
- Unit Test Rest Controllers
- NamedParameterJdbcTemplate
- Exceptions and Transactions ==> Gracefully catch exceptions / Rollback when an exception is thrown
  - @EnableTransactionManagement and @Transactional

--------

- If you need to retrieve the object you've just create, there are two approaches:
  - Use of PrepareStatement and Keyholder to get the key back => Complex, can be an overkill if you don't need it:

  - There is a simpler approach : Use SimpleJdbcInsert.


  