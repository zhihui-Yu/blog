<template>
  <el-container>
    <el-container>
      <el-aside width="350px">
        <div>
          <Avatar :name="name" :size="50" :imageUrl="imageUrl"/>
        </div>
        <div>
          <Directory />
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

export default {
  components: { Avatar, Directory, Content },
  name: 'HelloWorld',
  data () {
    return {
      msg: '<h1>标题一</h1>',
      imageUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      name: 'test'
    }
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
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
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
</style>
