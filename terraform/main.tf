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

resource "kubernetes_namespace" "minikube-namespace" {
  metadata {
    name = "shop-production"
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
}

resource "helm_release" "grafana" {
  chart = "./grafana"
  name  = "grafana"
}