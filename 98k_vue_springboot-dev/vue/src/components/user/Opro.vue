<template>
  <div>
    <h1>其他人的个人主页</h1>
    <el-form :model="user"  ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <div>
        <el-form-item label="个人头像" prop="pic">
          <el-image
            style="width: 100px; height: 100px"
            :src="this.$store.state.baseURL+'/picture/'+user.pic"
            :preview-src-list="srcList">
          </el-image>
        </el-form-item>
      </div>
      <el-form-item label="ID">
        <el-input v-model="user.id" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="账号名称" >
        <el-input maxlength="30" clearable show-word-limit v-model="user.name" :disabled="disabled"></el-input>
      </el-form-item>

      <el-form-item label="性别" >
        <el-select v-model="user.sex" placeholder="请选择性别" :disabled="disabled">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" required>
        <el-col :span="11">
          <el-form-item>
            <el-date-picker type="date" placeholder="选择日期" v-model="user.btime.split(' ')[0]" style="width: 100%;"
                            :disabled="true"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item >
            <el-input  placeholder="选择时间" v-model="user.btime.split(' ')[1]" style="width: 100%;"
                       :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="邮箱" >
        <el-input v-model="user.email" clearable show-word-limit :disabled="disabled"></el-input>
      </el-form-item>

      <el-form-item label="手机号" >
        <el-input v-model="user.phone" clearable show-word-limit :disabled="disabled"></el-input>
      </el-form-item>
      <el-form-item >
        <span>博文数: </span><el-link :underline="false" type="primary" @click="table3=true">{{TextCount}}</el-link>
        <span>关注数: </span><el-link :underline="false" type="primary" @click="table1=true">{{follow}}</el-link>
        <span>粉丝数: </span><el-link :underline="false" type="primary" @click="table2=true">{{fans}}</el-link>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="Zhan()">点赞</el-button>
        <el-button @click="submitInformation()">发消息</el-button>
      </el-form-item>
    </el-form>
    <!--抽屉，关注与粉丝抽屉-->
    <el-drawer
      title="关注列表"
      :visible.sync="table1"
      direction="rtl"
      size="50%">

      <el-table :data="Follist" style="width: 100%" max-height="600">

        <el-table-column fixed property="vipuid" label="vipuid" width="150"></el-table-column>
        <el-table-column property="vipname" label="用户名" width="200"></el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="Visit(scope.row)">访问个人主页</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
    <el-drawer
      title="粉丝列表"
      :visible.sync="table2"
      direction="rtl"
      size="50%">

      <el-table :data="Fanlist" style="width: 100%" max-height="600">

        <el-table-column fixed property="uid" label="uid" width="150"></el-table-column>
        <el-table-column property="name" label="用户名" width="200"></el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="Visit2(scope.row)">访问个人主页</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
    <!--抽屉，自己博客-->
    <el-drawer
      title="博文管理"
      :visible.sync="table3"
      direction="rtl"
      size="50%">

      <el-table :data="MyTexts" style="width: 100%" max-height="600">

        <el-table-column fixed property="tid" label="tid" width="150"></el-table-column>
        <el-table-column property="path" label="标题" width="200"></el-table-column>
        <el-table-column property="author" label="作者"></el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="Rooto(scope.row.tid)">浏览</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>

  </div>
</template>

<script>
import MessageList from "./MessageList";

export default {
  name: "Opro",
  props: {

  },
  data() {
    return {
      //自己的博客
      MyTexts:null,
      //关注与粉丝列表
      Follist:null,
      Fanlist:null,
      srcList:[],

      //抽屉
      table1:false,
      table2:false,
      table3:false,
      disabled:true,

      //fans数
      fans:0,
      follow:0,

      //博文数
      TextCount:0,

      //基本属性
      user: this.$store.state.user,
      baseURL:this.$store.state.baseURL,
    }
  },
  methods: {
    //获取个人基本信息
    GetUMes(id){
      const _this = this;
      this.$axios.get(this.baseURL+'/user/selectUser?id='+id).then(function (resp){
       _this.user=resp.data;
       console.log(resp.data)
        //获取博文列表
        _this.$axios.get(_this.baseURL+'/text/selectByAuthor?author='+_this.user.name).then(function (resp){
          console.log(resp.data)
          _this.TextCount=resp.data.length;
          _this.MyTexts=resp.data;
        }).catch(
          function (error){
            _this.$notify.error({
              title: '错误',
              message: error+'获取个人基本信息(内)'
            });
          })


        _this.srcList.push(_this.baseURL+'/picture/'+resp.data.pic);
      }).catch(function (error){
          _this.$notify.error({
            title: '错误',
            message: error+'获取个人基本信息(外)'
          });
        })


    },
    //获取关注列表与粉丝列表
    GetFans(id){
      const _this = this;
      this.$axios.get(this.baseURL+'/uu/findByuid?uid='+id).then(function (resp){
        _this.follow=resp.data.length;
        _this.Follist=resp.data;
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
      this.$axios.get(this.baseURL+'/uu/findByvipuid?vipuid='+id).then(function (resp){
        _this.fans=resp.data.length;
        _this.Fanlist=resp.data;
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
    },
    //跳转到文章
    Rooto(item) {
      this.$store.state.SelectedId=item //  保存主键tid
      if (window.location.href === this.$store.state.windowURL+'user/Content/TextMessage') {
        location. reload();
      } else {
        this.$router.push({path: '/user/Content/TextMessage'});
      }
    },
    //访问fans用户个人主页
    Visit2(val){
      if (window.location.href === this.$store.state.windowURL+'user/Opro') {
        this.$store.state.Opid=val.uid;
        location. reload();
      } else {
        this.$store.state.Opid=val.uid;
        this.$router.push({path: '/user/Opro'});
      }
    },
    Visit(val){
      console.log(val)
      if (window.location.href === this.$store.state.windowURL+'user/Opro') {
        this.$store.state.Opid=val.vipuid;
        location. reload();
      } else {
        this.$store.state.Opid=val.vipuid;
        this.$router.push({path: '/user/Opro'});
      }
    },
    Zhan(){
      this.$message({
        message: '赞',
        center: true
      });
    }
    ,
    submitInformation(){
      if (window.location.href === this.$store.state.windowURL+'user/MessageList') {
        location. reload();
      } else {
        this.$store.state.sname=this.user.name;
        this.$router.push({path: '/user/MessageList'});
      }
    }
    ,
    LoadInformation(){

      this.GetUMes(this.$store.state.Opid)
      this.GetFans(this.$store.state.Opid)
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
