<template>
  <!--404页面-->
<div>
  <el-link :underline="false"><h1>404,你的页面走丢了</h1></el-link>
  <hr>
  <img :src='"../assets/404"+this.pic_Count+".jpg"'>
</div>
</template>

<script>
export default {
  name: "NotFound",
  data(){
    return {
      pic_Count:0,
    }
  },
  methods: {
    getimgCountRound(){
      this.pic_Count=Math.floor(Math.random()*8)+1;
    }
  },created(){
    this.getimgCountRound();
  }
}
</script>

<style scoped>

</style>
