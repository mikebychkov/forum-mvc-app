#!/bin/bash

# FOR DOCKER USERS
sudo mkdir -p /etc/docker/certs.d/my.local.registry/
sudo cp ./certs/domain.crt /etc/docker/certs.d/my.local.registry/ca.crt

# FOR K8S NODES WITH CONTAINERD
sudo cp ./certs/domain.crt /etc/ssl/certs/domain.crt
