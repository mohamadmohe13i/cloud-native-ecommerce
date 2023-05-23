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
- first of all install [minikube](https://minikube.sigs.k8s.io/),
[kubectl](https://kubernetes.io/docs/tasks/tools/), [helm](https://helm.sh/), [terraform](https://www.terraform.io/)
- start a K8s cluster with `minikube start` command
- change directory to /terraform
- run `terraform init` command to install required providers
- create a k8s cluster by running `terraform apply --auto-approve` command
- now the cluster should contain below resources
  - a simple k8s cluster 
  - a postgres on `postgres-production` namespace
  - a stand alone redis on `redis-production` namespace
  - monitoring stack (prometheus, grafana, etc) on `monitoring-production` namespace


## Deploy `Shop` application to k8s
- create `shop` docker image using `make image` command
- add image to minikube via `minikube image load shop:<tag>` command (of course you can config `Jib` in `build.gradle.kts`
file to push the docker image
automatically to your image registry)
- cd to `/helm` and run `helm install shop ./shop` command (remember to change your namespace to `shop-production` before
running command the command)


## Monitoring
- this project use [prometheus](https://prometheus.io/) and [grafana](https://grafana.com/)
- how to access grafana and connect it to prometheus
  - after creating cluster with terraform you can use port-forwarding on `grafana service` to access the grafana dashboard
  - decode (base64) user and password in `grafana secret`
  - after sign-in add a `datasource` and use `http://prometheus-server` for url part
  - you can add your dashboards now.