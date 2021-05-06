<template>

  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <div>
        <el-form-item label="个人头像" prop="pic">
          <el-image
            style="width: 100px; height: 100px"
            :src="this.$store.state.baseURL+'/picture/'+ruleForm.pic"
            :preview-src-list="srcList">
          </el-image>
        </el-form-item>
      </div>
      <el-form-item label="ID">
        <el-input v-model="ruleForm.id" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="账号名称" prop="name">
        <el-input maxlength="30" clearable show-word-limit v-model="ruleForm.name" :disabled="disabled"></el-input>
      </el-form-item>
      <el-form-item label="账号密码" prop="password1">
        <el-input type="password" maxlength="30" show-password clearable show-word-limit v-model="ruleForm.password1"
                  :disabled="disabled"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="password2">
        <el-input type="password" maxlength="30" show-password clearable show-word-limit v-model="ruleForm.password2"
                  :disabled="disabled"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="ruleForm.sex" placeholder="请选择性别" :disabled="disabled">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" required>
        <el-col :span="11">
          <el-form-item>
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.buildDate" style="width: 100%;"
                            :disabled="true"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item >
            <el-input  placeholder="选择时间" v-model="ruleForm.buildTime" style="width: 100%;"
                       :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email" clearable show-word-limit :disabled="disabled"></el-input>
      </el-form-item>

      <el-form-item label="手机号" prop="phone">
        <el-input v-model="ruleForm.phone" clearable show-word-limit :disabled="disabled"></el-input>
      </el-form-item>
      <el-form-item >
        <span>博文数: </span><el-link :underline="false" type="primary" @click="table3=true">{{TextCount}}</el-link>
        <span>关注数: </span><el-link :underline="false" type="primary" @click="table1=true">{{follow}}</el-link>
        <span>粉丝数: </span><el-link :underline="false" type="primary" @click="table2=true">{{fans}}</el-link>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="resetForm()">修改</el-button>
        <el-button @click="submitForm('ruleForm')">确认修改</el-button>
        <el-button type="primary" round @click="RootWrite">写新博文</el-button>
        <el-button type="info" round @click="LogOut">注销</el-button>
        <el-button v-if="ruleForm.jurisdiction===1" type="danger" round @click="table = true">删除博文</el-button>
      </el-form-item>
    </el-form>
    <!--抽屉-->
    <div>
    <!--抽屉，删除博文选项-->
    <el-drawer
      title="管理员专属删除博客功能!"
      :visible.sync="table"
      direction="rtl"
      size="50%">

      <el-table :data="Texts" style="width: 100%" max-height="600">

        <el-table-column fixed property="tid" label="tid" width="150"></el-table-column>
        <el-table-column property="path" label="标题" width="200"></el-table-column>
        <el-table-column property="author" label="作者"></el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="DeleteText(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
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
            <el-button type="text" size="small" @click="Pass(scope.row)">取关</el-button>
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
            <el-button type="text" size="small" @click="DeleteText(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfile",
  data() {
    return {
      srcList: [],
      ruleForm: {id: '',
        name: '',
        sex: '',
        password1: '',
        password2: '',
        buildTime: '',
        buildDate: '',
        bitme:null,
        email: '',
        phone: '',
        jurisdiction:'',
      pic:''},
      rules: {
        sex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请填写邮箱', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请填写手机号', trigger: ['blur','change'] }
        ],
        name: [
          { required: true, message: '请填写名称', trigger: ['blur','change']}
        ],
        password1: [
          { required: true,  trigger: 'blur' ,validator:this.SurePassword }
        ],password2: [
          { required: true,  trigger: 'blur',validator:this.ReSurePassword }
        ],
      },
      //禁用状态
      disabled: true,
      TextCount:null,
      table: false,
      table1: false,
      table2: false,
      table3: false,
      Texts:this.$store.state.TextWords,


      //关注数与粉丝数
      follow:0,
      fans:0,

      //自己的博客
      MyTexts:null,
      //关注与粉丝列表
      Follist:null,
      Fanlist:null,
    }
  },
  methods: {
    //密码规则
    SurePassword(rule, value, callback) {
      if (!value) {
        callback(new Error("请输入密码"))
      } else {
        callback();
      }
    }, ReSurePassword(rule, value, callback) {
      if (!value) {
        callback(new Error("请确认密码"))
      } else if (!(value === this.ruleForm.password1)) {
        callback(new Error("两次输入的密码不一致"))
      } else {
        callback();
      }
    },
    //提交修改
    submitForm(formName) {
      const self = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.axios.put(self.$store.state.baseURL + '/user/update' ,
            {
              id:self.ruleForm.id,
              name:self.ruleForm.name,
              password:self.ruleForm.password1,
              sex:self.ruleForm.sex,
              email:self.ruleForm.email,
              phone:self.ruleForm.phone,
              btime:self.ruleForm.btime,
              pic:self.ruleForm.pic,
              jurisdiction:self.ruleForm.jurisdiction
            }).then(res => {
              console.log(res.data)
            self.$notify.success({
              title: '成功',
              message: '成功修改'
            });
          }).catch(error => {
            self.$notify.error({
              title: '失败',
              message: '修改信息失败，请稍后再试'
            });
          });

        } else {
          self.$notify.warning({
            title: '警告',
            message: '请按照规则填写信息'
          });
          return false;
        }
      });
      this.disabled = true;
    },
    resetForm() {
      this.disabled = false;
    },
    LogOut() {//注销
      const _this = this;
      _this.$store.state.SelectedActive=0;
      this.$store.state.sname="";
      sessionStorage.removeItem("store")
      sessionStorage.removeItem("Authorization");
      this.$axios.get(this.$store.state.baseURL+'/static/logout').then(function (resp){
        console.log(resp)
      }).catch(function (error){
        _this.$notify.error({title: '错误', message: error});})
      this.$router.push({path: '/'});
    },
    RootWrite(){
      let routeUrl = this.$router.resolve({
        name: 'Write'
      });
      window.open(routeUrl.href, '_blank');
    },
    DeleteText(row) {
      //console.log(row.tid)
      //先删除数据库内消息，再删除文件
      const _this = this;
      this.$axios.delete(this.$store.state.baseURL+'/text/delete?tid='+row.tid).then(function (resp){
        _this.$notify.success({
          title: '成功',
          message: '成功删除'
        });
        _this.GetTexts();
        console.log(resp);
      }).catch(
        function (error){
          // 请求失败处理
          //console.log(error)
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
      //先删博文
      this.$axios.delete(this.$store.state.baseURL+'/file/deleteFileText?path='+row.path).then(function (resp){
        console.log(resp);
      }).catch(
        function (error){
          // 请求失败处理
          //console.log(error)
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
      //再删图片
      this.$axios.delete(this.$store.state.baseURL+'/file/deleteFilePic?path='+row.picture).then(function (resp){
        console.log(resp);
      }).catch(
        function (error){
          // 请求失败处理
          //console.log(error)
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })

    },
    //获取用户信息
    GetInfo(){
      //const info = JSON.parse(localStorage.getItem('user'))
      this.user=this.$store.state.user;
      let pictu=this.user.pic;
      this.srcList.push(this.$store.state.baseURL+'/picture/'+pictu);
      //console.log(this.srcList)
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/text/selectByAuthor?author='+_this.user.name).then(function (resp){
        _this.TextCount=resp.data.length;
        _this.MyTexts=resp.data;
        //console.log(resp.data.length);
      }).catch(
        function (error){
          // 请求失败处理
          //console.log(error)
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
      //获取博文
      if(this.user.jurisdiction==1) {
        this.GetTexts();
      }
    },
    GetTexts(){
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/text/findAll/').then(function (resp) {
        _this.Texts = resp.data;
        //console.log(resp.data)
      }).catch(
        function (error) {
          // 请求失败处理
          //console.log(error)
          _this.$notify.error({
            title: '错误',
            message: error
          });
        })
    },
    //获取关注列表与粉丝列表
    GetFans(){
      const user = this.$store.state.user;
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/uu/findByuid?uid='+user.id).then(function (resp){
        //console.log(resp.data);
        _this.follow=resp.data.length;
        _this.Follist=resp.data;
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
      this.$axios.get(this.$store.state.baseURL+'/uu/findByvipuid?vipuid='+user.id).then(function (resp){
        _this.fans=resp.data.length;
        _this.Fanlist=resp.data;
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
    },
    //跳转到文章
    Rooto(item) {
      //localStorage.setItem('tid', item) //  保存主键tid
      this.$store.state.SelectedId=item
      if (window.location.href === this.$store.state.windowURL+'/user/Content/TextMessage') {
        location. reload();
      } else {

        this.$router.push({path: '/user/Content/TextMessage'});
      }
    },
    //取关
    Pass(val){
      const _this=this;
      this.$axios.post(this.$store.state.baseURL+'/uu/insert',{uid:_this.user.id
        ,vipuid:val.vipuid,
        name:_this.user.name,vipname:val.vipname,vip:0}).then(function (resp){
        _this.$notify.success({title: '(*^_^*)', message: '已取关'});
      }).catch(function (error){_this.$notify.error({title: '错误', message: error});})
    },
    //访问fans用户个人主页
    Visit2(val){
      if (window.location.href === this.$store.state.windowURL+'/user/Opro') {
        location. reload();
      } else {
        this.$store.state.Opid=val.uid;
        this.$router.push({path: '/user/Opro'});
      }
    },
    Visit(val){
      if (window.location.href === this.$store.state.windowURL+'/user/Opro') {
        location. reload();
      } else {
        this.$store.state.Opid=val.vipuid;
        this.$router.push({path: '/user/Opro'});
      }
    },
    LoadUser() {
      const _this = this;
      this.axios.get(_this.$store.state.baseURL + '/user/selectUserByname?' + 'name=' + _this.$store.state.user.name).then(res => {
        _this.ruleForm.id=res.data.id;
        _this.ruleForm.pic=res.data.pic;
        _this.ruleForm.name=res.data.name;
        _this.ruleForm.sex=res.data.sex;
        _this.ruleForm.password1=res.data.password;
        _this.ruleForm.password2=res.data.password;
        _this.ruleForm.phone=res.data.phone;
        _this.ruleForm.email=res.data.email;
        _this.ruleForm.btime=res.data.btime;
        _this.ruleForm.jurisdiction=res.data.jurisdiction;
        _this.ruleForm.buildDate=res.data.btime.split(' ')[0];
        _this.ruleForm.buildTime=res.data.btime.split(' ')[1];
      }).catch(error => {
        _this.$notify.error({
          title: '失败',
          message: '服务器正在忙碌中'
        });
      });
    },
    LoadInformation(){
      this.GetInfo();
      this.GetFans();
      this.LoadUser();
    }
  },
  mounted() {

  }
  ,
  created(){
    this.$nextTick(()=>{
      this.LoadInformation();
    })
  }

}
</script>

<style scoped>

</style>
