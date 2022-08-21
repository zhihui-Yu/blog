- todo list
  - 菜单选项
  - 博客管理
  - 用户信息管理 （或者扫码登入）
  - 登入功能
  - web界面

- 菜单选项：
  - db -> mongo
  ```sql
  menus:{id, menu_name, sub_menus,created_time,updated_time}
  ```

docker run -it  -d -p 9200:9200 -p 9300:9300 --name es -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -e "discovery.type=single-node" -e "cluster.name=my-es" -e "http.port=9200" --restart=always -v /data/elk/es/data:/usr/share/elasticsearch/data -v /data/elk/es/logs:/usr/share/elasticsearch/logs elasticsearch:8.3.3
docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 --name kibana -p 5601:5601 -v /data/elk/kibana/kibana.yml:/usr/share/kibana/config/kibana.yml kibana:8.3.3


docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 -p 5044:5044 --name logstash -v /data/elk/logstash/logstash.yml:/usr/share/logstash/config/logstash.yml -v /data/elk/logstash/conf.d/:/data/docker/logstash/conf.d/ logstash
wget https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-7.7.1-x86_64.rpm
rpm -ivh filebeat-7.7.1-x86_64.rpm
server.name: kibana
server.host: "0"
elasticsearch.hosts: ['172.17.0.3']
xpack.monitoring.ui.container.elasticsearch.enable: true
docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 --name kibana -p 5601:5601 -v /data/elk/kibana/kibana.yml:/usr/share/kibana/config/kibana.yml kibana