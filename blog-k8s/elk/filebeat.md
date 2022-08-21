wget https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-7.7.1-x86_64.rpm
rpm -ivh filebeat-7.7.1-x86_64.rpm

vim /etc/filebeat/filebeat.yml #修改原有的yml内容
```
#=========================== Filebeat inputs =============================
filebeat.inputs:
- type: log
  enabled: true
  paths:
    #- /var/log/*.log
    - /var/log/messages
    #- c:\programdata\elasticsearch\logs\*
#-------------------------- Elasticsearch output ------------------------------
#output.elasticsearch:
  # Array of hosts to connect to.
  #hosts: ["192.168.200.94:9200"]
#----------------------------- Logstash output --------------------------------
output.logstash:
  # The Logstash hosts
  hosts: ["宿主机地址:5044"]
```

systemctl restart filebeat.service