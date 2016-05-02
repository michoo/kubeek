#! /bin/sh

sudo /etc/init.d/kubeek stop
sudo chown root:root kubeek-service-rest-*.jar
sudo mv kubeek-service-rest-*.jar kubeek.jar
sudo ln -s /home/pi/Workspace/kubeek/kubeek.jar /etc/init.d/kubeek
sudo update-rc.d kubeek defaults
sudo /etc/init.d/kubeek start