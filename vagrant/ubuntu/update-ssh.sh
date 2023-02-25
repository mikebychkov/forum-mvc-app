#!/bin/bash

sed -i -e 's/PasswordAuthentication no/PasswordAuthentication yes/' /etc/ssh/sshd_config

systemctl reload sshd
