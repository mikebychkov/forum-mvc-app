#!/bin/bash
cd /home/vagrant && ansible-playbook deploy.yml -i inventory 2>&1
