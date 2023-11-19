<template>

    <Main/>

  <!-- <el-container>
    <el-aside>
      <div class="directory">
        <Directory @blogContent="setBlogContent" :data="dirs" />
      </div>
    </el-aside>
    <el-container>
      <el-main>
        <div class="markdown-body">
          <Content v-highlight :body="msg" />
        </div>
      </el-main>
    </el-container>
  </el-container> -->
</template>

<script>
import Directory from './Directory'
import Main from './Main.vue'
import Content from './Content'
import 'github-markdown-css/github-markdown.css'
import hljs from 'highlight.js'
import Vue from 'vue'

Vue.directive('highlight', function (el) {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach((block) => {
    hljs.highlightBlock(block)
  })
})

export default {
  components: { Directory, Content, Main },
  name: 'Index',
  data () {
    return {
      msg: '',
      dirs: []
    }
  },
  mounted () {
    // getDirectory().then((res) => {
    //   this.dirs = res.data.data
    // })
  },
  methods: {
    // setBlogContent (content) {
    //   this.msg = content
    // }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-aside {
  display: block;
  position: absolute;
  left: 0;
  top: 0%;
  bottom: 0;
  width: 20%;
}

.el-main {
  position: absolute;
  left: 20%;
  right: 0;
  top: 0%;
  bottom: 0;
  padding: 0 !important;
  overflow: hidden;
}

body > .el-container {
  margin-bottom: 40px;
}

.directory {
  padding: 8% 5% 0 5%;
  position: absolute;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}

/* 设置滚动条的样式 */
*::-webkit-scrollbar {
  height: 5px; /*设置横向滚动条的高度*/
  width: 5px; /*设置纵向向滚动条宽带*/
  background-color: white;
}
*::-webkit-scrollbar-track {
  display: none;
  border-radius: 10px;
}
/* 滚动条滑块样式 */
*::-webkit-scrollbar-thumb {
  background-color: #888a96;
  border-radius: 10px;
}
*::-webkit-scrollbar-track-piece {
  border-radius: 10px;
}
</style>
