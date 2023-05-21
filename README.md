## About the Project
I created this repository to build a cloud-native simple shop application 
 by using cutting-edge technologies which I mentioned below.
<br>

## Repository Management
This project use [Github](https://github.com) with a
[Git Flow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
approach.

## Test
tests can be done standalone without any dependency because it uses embedded `h2` and `in-memory` cache for tests.
<br>
tests have written with Junit and Mockk library.
<br>
use `make test` to run tests.

## Local Setup (Docker-Compose)
- use `make image` to create application Docker image using 
[Jib](https://cloud.google.com/blog/topics/developers-practitioners/comparing-containerization-methods-buildpacks-jib-and-dockerfile).
- use `docker compose up` to run images (`Shop`, `PostgreSQL`, `Redis`).

## Create K8S Cluster (Terraform)
- this project use IaC approach with [terraform](https://www.terraform.io/) tools
- first of all install [minikube](), [kubectl](), [helm](), [terraform]()
- start a minikube cluster with `minikube start` command
- change directory to /terraform
- create a k8s cluster by running `terraform apply --auto-approve` command
- now the cluster should contain below resources
  - a simple k8s cluster 
  - monitoring stack (prometheus, grafana, etc) on `monitoring-production` namespace
## Monitoring
- this project use [prometheus]() and [grafana]()
- how to access grafana and conenct it to prometheus
  - after creating cluster with terraform you can use port-forwarding on `grafana service` to access the grafana dashboard
  - decode (base64) user and password in `grafana secret`
  - after sign-in add a `datasource` and use `http://prometheus-server` for url part
  - you can add your dashboards now.