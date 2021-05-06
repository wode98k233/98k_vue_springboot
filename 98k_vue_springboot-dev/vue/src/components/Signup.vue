<template>
  <!--注册页面-->
<div>
      <h1>注册界面</h1>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <div>
      <el-form-item label="个人头像" prop="pic">
        <el-upload
          action=""
          list-type="picture-card"
          :on-change="Add"
          :auto-upload="false"
          :multiple="false"
          :limit="1">
          <i slot="default" class="el-icon-plus" ></i>
          <div slot="file" slot-scope="{file}" >
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
            >
            <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
          >
          <i class="el-icon-zoom-in"></i>
        </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
          >
        </span>
        </span>
          </div>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible" class="file" type="file" >
          <img width="100%" :src="dialogImageUrl" alt="" >
        </el-dialog>
      </el-form-item>

    </div>
    <el-form-item label="账号名称" prop="name">
      <el-input maxlength="30" clearable show-word-limit v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="账号密码" prop="password1">
      <el-input type="password" maxlength="30" show-password clearable show-word-limit v-model="ruleForm.password1"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="password2">
      <el-input type="password" maxlength="30" show-password clearable show-word-limit v-model="ruleForm.password2"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-select v-model="ruleForm.sex" placeholder="请选择性别">
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input  v-model="ruleForm.email" clearable show-word-limit maxlength="30"></el-input>
    </el-form-item>

    <el-form-item label="手机号" prop="phone">
      <el-input  v-model="ruleForm.phone" clearable show-word-limit maxlength="15"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>


</div>
</template>

<script>
export default {
  name: "Signup",
  data () {
    return {
      FL:[],
      fileList:[],
      show:true,
      ruleForm: {
        id:'',
        name: '',
        sex: '',
        password1: '',
        password2: '',
        buildTime:'',
        buildDate:'',
        email: '',
        phone:'',
        pic:'',
      },
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
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,

    };
  },
  methods: {
    //上传
    Add(file){
      this.fileList.push(file.name);
      this.FL.push(document.querySelector('input[type=file]').files[0]);
      this.show = false;
      //console.log(this.fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    findArrayIndex(val,vals){
      for(let i=0;i<vals.length;++i){
        if(vals[i]==val){
          return i;
        }
      }
      return 0;
    }
    ,
    SurePassword(rule,value,callback){
      if(!value){
        callback(new Error("请输入密码"))
      }else{
        callback();
      }
    },ReSurePassword(rule,value,callback){
      if(!value){
        callback(new Error("请确认密码"))
      }else if(!(value===this.ruleForm.password1)){
        callback(new Error("两次输入的密码不一致"))
      }else{
        callback();
      }
    },
    submitForm(formName){
      if(this.FL.length===0) {
        this.$notify.warning({
          title: '警告',
          message: '请选择头像'
        });
        return;
      }
      let formData = new window.FormData()
      formData.append('file', this.FL[0]) // 'file' 这个名字要和后台获取文件的名字一样;
      const self = this;
      this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios({
              url: self.$store.state.baseURL+'/file/upload',   //****: 你的ip地址
              data: formData,
              method: 'post',
              headers: {
                'Content-Type': 'multipart/form-data',
                // 'Access-Control-Allow-Origin': 'http://127.0.0.1:8080'
                //这里是为了解决跨域问题，但是博主并没有用这种方式解决。后面会给出解决方案
              }
            }).then((res) => {
              self.$axios.post(this.$store.state.baseURL+'/user/insert', {id:0,
                name:self.ruleForm.name,
                password:self.ruleForm.password1,
                sex:self.ruleForm.sex,
                email:self.ruleForm.email,
                phone:self.ruleForm.phone,
                btime:0,
                jurisdiction:0,
                pic:res.data})
                .then((res)=>{
                  //console.log(res.data)
                }).catch(
                error=>{// 请求失败处理
                  self.$notify.error({
                    title: '错误',
                    message: error
                  });
                });
            }).catch(
              function (error) {// 请求失败处理
                self.$notify.error({
                  title: '错误',
                  message: error
                });
              })
            self.$notify.success({
              title: 'ε=ε=ε=(~￣▽￣)~',
              message: '注册成功，即将返回登录页面'
            });
            this.$router.push({path:'/'});
          }
          else {
            return false;
          }
        })
    },
    resetForm(formName) {
      this.FL=[];
      this.dialogImageUrl='';
      location. reload();
      this.$refs[formName].resetFields();
    },
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 80px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 80px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-select .el-input {
  width: 130px;
}

.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
