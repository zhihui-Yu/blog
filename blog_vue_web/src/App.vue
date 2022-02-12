<template>
  <div id="app">
    <div>
      <AboutMe :info="myData"></AboutMe>
      <BlogList :blog-list='blogList'/>
    </div>
  </div>
</template>

<script>
import AboutMe from './components/AboutMe'
import BlogList from './components/BlogList'

export default {
  name: 'App',
  data() {
    return {
      blogList: [{"id": "1", "name": "java"}, {"id": "2", "name": "vue"}],
      myData: {"name": "simple", "github_link": "https://baidu.com", "avatar_link": ""}
    }
  },
  components: {
    BlogList, AboutMe
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
