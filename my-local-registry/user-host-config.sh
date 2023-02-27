#!/bin/bash
sudo mkdir -p /etc/docker/certs.d/my.local.registry/
sudo cp ./certs/domain.crt /etc/docker/certs.d/my.local.registry/ca.crt
