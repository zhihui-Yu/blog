<template>
  <el-row>
    <el-col :span="12" class="avatar">
      <div class="sub-title">{{ name }}</div>
      <div class="">
        <div class="">
          <el-avatar :size="size" :src="imageUrl"></el-avatar>
        </div>
      </div>
      <span> github: </span>

    </el-col>
  </el-row>
</template>
<script>
export default {
  props: {
    name: String,
    imageUrl: Object,
    size: Number
  }
}
</script>

<style scoped>
.avatar {
  margin-left: 5%;
  text-align: left;
  height: 100%;
  width: 100%;
}
</style>
