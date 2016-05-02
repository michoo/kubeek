#! /bin/sh

sudo /etc/init.d/kubeek stop
sudo update-rc.d kubeek remove
sudo rm /etc/init.d/kubeek