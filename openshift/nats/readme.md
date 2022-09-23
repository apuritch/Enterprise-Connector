# Installation notes for NATS

## Default installation commands

`helm repo add nats https://nats-io.github.io/k8s/helm/charts/`

`helm install my-nats nats/nats`

## Install using configuration options in values.yaml

`helm  install -f values.yaml  my-nats nats/nats`

## uninstall

`helm uninstall my-nats nats/nats`

## Post Installation Notes

NATS Box has been deployed into your cluster, you can
now use the NATS tools within the container as follows:

  `kubectl exec -n b07b69-dev -it deployment/my-nats-box -- /bin/sh -l`

  nats-box:~# `nats sub test &`

  nats-box:~# `nats pub test hi`

  nats-box:~# `nc my nats 4222`

Thanks for using NATS!





