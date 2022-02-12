<template>
  <div>
    name: {{ info.name }} <br/>
    avatar link : {{ info.avatar_link }}
    github link : {{ info.github_link }}
  </div>
</template>

<script>
export default {
  name: "AboutMe",
  props: {"info": Object}
}
</script>

<style scoped>

</style>