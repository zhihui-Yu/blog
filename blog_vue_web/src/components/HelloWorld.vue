<template>
  <el-container>
    <el-container>
      <el-aside>
        <!--        <div class="aboutMe">-->
        <!--          <Avatar :name="name" :size="50" :imageUrl="imageUrl"/>-->
        <!--        </div>-->
        <div class="directory">
          <Directory :data="dirs"/>
        </div>
      </el-aside>
      <el-main>
        <div>
          <Content :body="msg"/>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Avatar from './Avatar'
import Directory from './Directory'
import Content from './Content'
import axios from 'axios'
import qs from 'qs'

export default {
  components: {Avatar, Directory, Content},
  name: 'HelloWorld',
  data () {
    return {
      msg: '<h1>标题一</h1>',
      imageUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      name: 'test',
      dirs: {}
    }
  },
  mounted () {
    console.log('mounted')

    // 如果要跨域的话，对axios进行一些设置
    const axiosInstance = axios.create({
      headers: {'Content-Type': 'application/json;charset=utf-8'}, // 设置传输内容的类型和编码
      withCredentials: true // 指定某个请求应该发送凭据
    })

    axiosInstance.get('https://localhost:80/api/directory').then((res) => {
      console.log(res)
      console.log(res.data.data)
      console.log(qs.parse(res.data.data))
      console.log(typeof (qs.parse(res.data.data)))
      console.log(typeof (qs.parse(res.data.data[0])))
      this.dirs = res.data.data
    }).catch(function (error) { // 请求失败处理
      console.log(error)
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 20px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 16px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.aboutMe {
  margin-top: 20%;
  border: lightskyblue 1px solid;
}

.directory {
  margin-top: 20%;

  border: lightskyblue 1px solid;
}
</style>
