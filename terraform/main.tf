provider "kubernetes" {
  config_path    = "~/.kube/config"
  config_context_cluster = "minikube"
}

provider "helm" {
  kubernetes {
    config_context_cluster = "minikube"
    config_path    = "~/.kube/config"
  }
}

resource "kubernetes_namespace" "shop-production" {
  metadata {
    name = "shop-production"
  }
}

resource "kubernetes_namespace" "postgres-production" {
  metadata {
    name = "postgres-production"
  }
}

resource "kubernetes_namespace" "redis-production" {
  metadata {
    name = "redis-production"
  }
}

resource "kubernetes_namespace" "monitoring-production" {
  metadata {
    name = "monitoring-production"
  }
}

resource "helm_release" "prometheus" {
  chart = "./prometheus"
  name  = "prometheus"
  namespace = "monitoring-production"
}
#
resource "helm_release" "grafana" {
  chart = "./grafana"
  name  = "grafana"
  namespace = "monitoring-production"
}

resource "helm_release" "postgres" {
  chart = "./postgresql"
  name  = "postgres"
  namespace = "postgres-production"
}

resource "helm_release" "redis" {
  chart = "./redis"
  name  = "redis"
  namespace = "redis-production"
}