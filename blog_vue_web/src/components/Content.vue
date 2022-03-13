<template>
  <div class="md-content">
    <el-backtop target=".md-content">
      <i class="el-icon-caret-top"></i>
    </el-backtop>
    <VueMarkdown :source="body"/>
    <div class="end"></div>
  </div>
</template>
<script>
import VueMarkdown from 'vue-markdown'

export default {
  props: {
    body: String
  },
  components: {VueMarkdown}
}
</script>
<style scoped>
div {
  text-align: left;
}

hr {
  border: 0 !important;
  margin: 0 !important;
}

.md-content {
  height: 100vh;
  overflow-x: hidden;
  box-sizing: border-box;
  padding: 0 5% 8% 5%;
}
</style>
