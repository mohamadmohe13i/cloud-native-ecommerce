## Repository management
This project use [Git Flow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).

## Test
tests can be done standalone without any dependency because it uses embedded `h2` and `in-memory` cache for tests.
<br>
tests have written with Junit and Mockk library.
<br>
use `make test` to run tests.

## Local setup
- use `make image` to create application Docker image using 
[Jib](https://cloud.google.com/blog/topics/developers-practitioners/comparing-containerization-methods-buildpacks-jib-and-dockerfile).
- use `docker compose up` to run images (`shop`, `PostgreSQL`, `redis`).