<template>
  <div>
    <ul>
      <li v-for="item in blogList" :key="item.id">
        {{ item.name }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "BlogList",
  props: {
    blogList: Array
  }

}
</script>

<style scoped>

</style>