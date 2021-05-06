<template>
  <!--登录-->
  <div>
    <img src="../assets/logo.png">
    <br>
    <h1>Please sign in</h1>
    <el-input style="width:250px" clearable maxlength="10" show-word-limit placeholder='Username' type="text"
              v-model="username">
    </el-input>
    <br><br>
    <el-input style="width:250px" show-password clearable maxlength="10" show-word-limit placeholder='Password'
              type="password" v-model="password">
    </el-input>
    <br><br>
    <div class="checkbox mb-3">
      <label>
         @Mr.Liu
      </label>
    </div>
    <br>
    <el-button type="primary" round v-on:click="Login_Liu(username,password)">Sign in</el-button>
    <el-button type="danger" round v-on:click="Sing_Up()">Sign up</el-button>
  </div>

</template>
<script>

export default {
  name: 'Login',
  data() {
    return {
      password: "",
      username: "",
    }
  },
  methods: {
    Login_Liu: function (username, password) {
      this.$store.state.user.name=username;
      this.$store.state.user.password=password;
      let _this = this;
      if (username === "" || password === "") {
        _this.$notify.error({
          title: '错误',
          message: '账号或密码不能为空'
        });
      } else {
        this.axios.get(_this.$store.state.baseURL + '/static/ulogin?' + 'username=' + username + '&password=' + password).then(res => {
          if (res.data.data !== "error") {
            _this.userToken = res.data.errcode;
            /**
             * 将用户token保存到vuex中
             * */
            _this.$store.state.Authorization = _this.userToken;
            sessionStorage.setItem('Authorization', _this.$store.state.Authorization);
            _this.$notify.success({
              title: 'success',
              message: '登录成功'
            });
            let son=_this.$store.state.SelectedActive;
            /**
             * 进入mian页面要考虑进入的是哪个子页面
             * */
            let sonPath="/main";
            switch(son){
              case 1:sonPath="user/content";break;//博文
              case 2:sonPath="user/list";break;//用户列表
              case 3:sonPath="user/profile";break;//个人中心
              case 4:sonPath="user/MessageList";break;//消息中心
              case 5:sonPath="user/Opro";break;//他人主页
              default:
                break;
            }

            this.$router.push({path: sonPath});
          } else {
            _this.$notify.error({
              title: '错误',
              message: res.data.errmsg
            });
          }
        }).catch(error => {
          _this.$notify.error({
            title: '错误',
            message: '账号或密码不能为空'
          });
        });
      }
    },
    Sing_Up: function () {
      this.$router.push({name: 'Signup'})
    },

  },
  created() {
    if (this.$store.state.user.username !== "" && this.$store.state.user.password !== "") {
      this.username = this.$store.state.user.name;
      this.password = this.$store.state.user.password;
    }
  }

}
</script>

<style scoped>
</style>
