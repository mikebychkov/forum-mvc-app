#!/bin/bash
./mvnw clean package \
&& scp -r ./* vagrant@192.168.56.41:~/my-local-automation/ \
&& ssh vagrant@192.168.56.41 "sudo systemctl restart as"