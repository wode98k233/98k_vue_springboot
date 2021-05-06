<template>
  <div>
    <h1>用户列表</h1>
    <span>请输入作者</span>
    <el-input placeholder="请输入内容" v-model="input3" class="input-with-select" style="width:600px;"
              @keyup.enter.native="search(input3)">
      <el-button slot="append" icon="el-icon-search" @click="search(input3)"></el-button>
    </el-input>
    <el-row>
      <div v-for="(item) in users" >
        <UserCard :id="item.id" :username="item.name" :path="baseURL+'/picture/'+item.pic" :Isgz="IsFans(item.id)"></UserCard>
      </div>

    </el-row>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage3"
        :page-size="4"
        layout="total, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import UserCard from "./List/UserCard";

export default {
  name: "UserList",
  components: {UserCard},
  data() {
    return {
      input3: "",
      users: this.$store.state.users,
      currentPage3: 1,
      total: null,
      pageSize: 4,
      //关注列表
      followUsers:[{
        uid:null,
        vipuid:null,
        name:null,
        vipname:null,
        vip:null,
      },
      ],
      baseURL:this.$store.state.baseURL,
    }
  },
  methods: {
    search(val) {
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/user/selectUserByname?name=' + val).then(function (resp) {
        _this.users = new Array();
        _this.users.push(resp.data);
        //console.log(_this.users);
      }).catch(
        function (error) {
          // 请求失败处理
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
      //console.log(`当前页: ${val}`);


    },
    handleCurrentChange(val) {
      //页面切换
      this.pageIndex = val;
      //获取服务器内容
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/user/findAll/' + val + '/' + _this.pageSize).then(function (resp) {
        _this.users = resp.data.content;
        //console.log(resp.data);
      }).catch(
        function (error) {
          // 请求失败处理
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
      //console.log(`当前页: ${val}`);
    },
    IsFans(val){
      for(let i=0;i<this.followUsers.length;++i){
        if(this.followUsers[i].vipuid==val){
          return true;
        }
      }
      return false;
    },
    LoadInformation(){
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/user/findAll/1/' + _this.pageSize).then(function (resp) {
        _this.users = resp.data.content;
        _this.total = resp.data.totalElements;
        //console.log(_this.users);
      }).catch(
        function (error) {
          // 请求失败处理
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
      const user = this.$store.state.user;
      this.$axios.get(this.$store.state.baseURL+'/uu/findByuid?uid='+user.id).then(function (resp){
        _this.followUsers=resp.data;
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
    }
  },
  created() {
    this.$nextTick(()=>{
      this.LoadInformation();
    })
  }
}
</script>

<style scoped>

</style>
