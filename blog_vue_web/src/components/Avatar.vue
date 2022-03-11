<template>
  <el-row class="demo-avatar demo-basic">
    <el-col :span="12">
      <div class="sub-title">{{name}}</div>
      <div class="demo-basic--circle">
        <div class="block">
          <el-avatar :size="size" :src="imageUrl"></el-avatar>
        </div>
      </div>
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
