<template >
  <div>
    body:
    <span v-html="body"></span>
  </div>
</template>
<script>
export default {
  props: {body: String}
}
</script>
<style scoped>
.body {
    background: black;
    color: red;
}
</style>
