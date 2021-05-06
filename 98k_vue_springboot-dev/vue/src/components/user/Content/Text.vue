<template>
<div>
    <el-col :span="8" v-for="(o, index) in 1" :key="o" :offset="index > 0 ? 2 : 0">
      <el-card :body-style="{ padding: '0px' }" el-card shadow="hover">
        <img style="width: 100%;height:100%" :src="path">
        <div style="padding: 14px;">
          <span>标题:{{title}}<br>作者:{{ author }}</span>
          <div class="bottom clearfix">

            <el-button type="text" class="button" @click="RouteTo()">浏览全文</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
</div>
</template>

<script>
import staticPicture from "../../../assets/a.jpg"
export default {
  name: "TextCard",
  props: {author:''
    , tid:''
    ,title:''
    ,path:{type:String,
      default:staticPicture}
  },
  data() {
    return {
      currentDate: new Date()
    };
  },
  methods: {
    RouteTo(){
      //保存点开博文的作者信息
      //localStorage.setItem('tid', this.tid) //  保存主键tid
      this.$store.state.SelectedId=this.tid;
      this.$router.push({path:'/user/Content/TextMessage'});
    },
  },
  created(){
  }
}
</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
