<template>
  <!--走马灯-->
  <div >
    <el-carousel :interval="4000" type="card" >
      <el-carousel-item v-for="item in picturelist" :key="item" @click.native="Rooto(Texts[item].tid)">
        <img  :src="baseURL+pic+Texts[item].picture"
              style="max-width: 100%; height-width: 100%">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import PictureStatic from "../../assets/a.jpg";

export default {
  name: "SortText",
  data () {
    return {
      //走马灯
      picturelist: [PictureStatic],
      baseURL:this.$store.state.baseURL,
      Texts:this.$store.state.TextWords,
      pic:"/picture/",
      reFresh:false,
    }
  },
  methods: {
    //跳转到走马灯指定文章
    Rooto(item) {
      if (window.location.href === this.$store.state.windowURL+'user/Content/TextMessage') {
        location. reload();
      } else {
        this.$store.state.SelectedId=item;
        this.$router.push({path: '/user/Content/TextMessage'});
      }
    },
    //走马灯内容随机选择推荐
    randomFiveDiffNum(n) {
      let num = [];
      for (let i = 0; i < 5; i++) {
        num[i] = Math.floor(Math.random() * n);
        for (let j = 0; j < i; j++) {
          if (num[i] == num[j]) {
            i--;
          }
        }
      }
      return num;
    },
    GetInformation(){
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/text/findAll/').then(function (resp) {
        _this.picturelist = _this.randomFiveDiffNum(resp.data.length)
        _this.Texts=resp.data;
      }).catch(
        function (error) {
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })

    }
  },created() {

    this.reFresh=false;
    this.$nextTick(()=>{
      this.GetInformation();
      this.reFresh = true
    })
  }
}
</script>

<style scoped>

</style>
