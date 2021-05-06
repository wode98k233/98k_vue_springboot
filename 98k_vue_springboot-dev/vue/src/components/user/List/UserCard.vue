<template>
  <div>
    <el-col :span="8" v-for="(o, index) in 1" :key="o" :offset="index > 0 ? 2 : 0">
      <el-card :body-style="{ padding: '0px' }" el-card shadow="hover">
        <img style="width: 100%;height:100%" :src="path">
        <div style="padding: 14px;">
          <span>用户:{{ username }}</span>

          <br>
          <el-switch
            v-model='Isgz'
            active-text="已关注"
            inactive-text="未关注"
            @click.native="Fans()">
          </el-switch>

        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import staticPicture from "../../../assets/userlogo.jpg";

export default {
  name: "UserCard",
  props: {
    id:{
      type:Number,
      default:null
    },
    username: {
      type: String,
      default: 'wode98k'
    },
    Isgz: {
      type: Boolean,
      default: false
    }
    , path: {
      type: String,
      default: staticPicture
    }
  },
  methods: {
    Fans() {
      //console.log(this.id+'\n'+this.username+'\n'+(this.Isgz==false?0:1))
      //添加uu表vip==1是关注，0是取关.
      const _this=this;
      this.$axios.post(this.$store.state.baseURL+'/uu/insert',{uid:_this.$store.state.user.id
      ,vipuid:_this.id,
      name:_this.$store.state.user.name,
      vipname:_this.username,
      vip:(_this.Isgz==false?0:1)}).then(function (resp){
        //console.log(resp.data)
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
    },
  },
  data() {
    return {};

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
