# 创建容器的私服信息
kubectl create secret docker-registry regcred \
--docker-server=<你的镜像仓库服务器> \
--docker-username=<你的用户名> \
--docker-password=<你的密码> \
--docker-email=<你的邮箱地址>

# 查看信息
kubectl get secret regcred --output=yaml
kubectl get secret regcred --output="jsonpath={.data.\.dockerconfigjson}" | base64 --decode

kubectl create secret docker-registry tx-registry \
--docker-server=ccr.ccs.tencentyun.com \
--docker-username=100022282625 \
--docker-password=Simple2021. 