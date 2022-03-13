<template>
  <div>
    <el-input class="filter-input" placeholder="输入关键字进行过滤" v-model="filterText"></el-input>
    <div style="margin-top: 10%;"></div>
    <el-tree
      class="filter-tree"
      :data="data"
      :props="defaultProps"
      default-expand-all
      :filter-node-method="filterNode"
      @node-click="nodeClick"
      ref="tree"
    >
    </el-tree>
  </div>
</template>
<script>
import {getBlog} from '../api/api'

export default {
  name: 'directory',
  props: {
    data: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    },
    data: function (newData, oldDate) {
      console.log(newData)
      this.data = newData
    }
  },

  mounted () {
    console.log(this.data)
  },
  methods: {
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },

    nodeClick (value, node, nodeSelf) {
      // value -> 传递给 data 属性的数组中该节点所对应的对象
      // node -> 节点对应的 Node
      // nodeSelf -> 节点组件本身
      console.log(value.children)
      if (value.children === null || value.children.length === 0) {
        getBlog(value.id).then(res => {
          console.log(res.data)
          this.$emit('blogContent', res.data.data)
        })
      }
    }
  },

  data () {
    return {
      filterText: '',
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  }
}
</script>

<style>
.filter-input > input{
  border: none;
  border-bottom: 1px lightskyblue solid;
  border-radius: 0;
}
</style>
