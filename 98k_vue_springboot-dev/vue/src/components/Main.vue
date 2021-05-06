<template>
  <!--首页-->

  <div id="app">
    <el-container style=" border: 1px solid #eee">
      <el-aside width="auto" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>导航栏</template>
            <template slot="title"></template>
            <el-menu-item index="2-1" @click="RouteTo(1)">
              首页
            </el-menu-item>
            <el-menu-item index="2-2" @click="RouteTo(2)">
              用户列表
            </el-menu-item>
            <el-menu-item index="2-3" @click="RouteTo(4)">博文</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: right; font-size: 12px"  >
          <el-link :underline="false" type="primary" @click="drawer = true">音乐</el-link>
          <el-link :underline="false" type="primary" @click="PlayMusic">{{ MusicText }}</el-link>
          <!--name地址，params传递参数-->
          <el-dropdown>
            <i class="el-icon-user-solid" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="RouteTo(3)">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="RouteTo(5)">消息中心</el-dropdown-item>
              <el-dropdown-item @click.native="table=true">收藏夹</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
        <el-main width="auto">
          <router-view></router-view>
          <el-drawer
            title="BGM"
            direction="btt"
            :visible.sync="drawer"
            :with-header="true">
            <audio :src="musicPath" controls="controls" id="music-audio">
              您的浏览器不支持 audio 标签
            </audio>
          </el-drawer>


        </el-main>


      </el-container>
    </el-container>
    <!--抽屉，收藏夹博文-->
    <el-drawer
      title="收藏夹"
      :visible.sync="table"
      direction="rtl"
      size="50%">

      <el-table :data="CollectionTexts" style="width: 100%" max-height="600">

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
import MusicPath from "../assets/周杰伦-夜曲.mp3"
import TextMessage from "./user/Content/TextMessage";


export default {
  name: 'Main',
  data() {
    return {
      //抽屉
      drawer: false,
      musicPath: MusicPath,
      Isplay: false,
      MusicText: '关',



      //收藏夹
      table: false,
      CollectionTexts: '',
    }
  },
  methods: {
    Rooto(item) {
      this.$store.state.SelectedId=item;
      if (window.location.chref === this.$store.state.windowURL+'user/Content/TextMessage') {
        location. reload();
      } else {
        this.$router.push({path: '/user/Content/TextMessage'});
      }
    },
    //获取收藏夹内容
    GetCollection() {
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/ut/findByUidandCollection?uid=' + this.$store.state.user.id +
        '&collection=' + 1).then(function (resp) {

        _this.CollectionTexts = resp.data;
      }).catch(function (error) {
        _this.$notify.error({title: '错误', message: error});
      })
    },
    //播放音乐
    PlayMusic() {
      let music = document.getElementById("music-audio");
      if (music != null) {
        if (this.Isplay) {
          music.pause();
          this.MusicText = '关';
        } else {
          music.play();
          this.MusicText = '开';
        }
        this.Isplay = !this.Isplay;
      } else {
        this.$notify.error({
          title: '错误',
          message: '请点击音乐，导入资源'
        })
      }
    },
    handleClose(key, keyPath) {
      //console.log(key, keyPath);
    },
    handleSizeChange(val) {
    },
    handleCurrentChange(val) {
    },
    //导航栏
    RouteTo(n) {
      switch (n) {
        case 1:
          //console.log("首页")
          this.$store.state.SelectedActive=0;
          if(window.location.href!==this.$store.state.windowURL+"main") {
            this.$router.push({path:'/main'});
          }else
            location. reload();
          break;//首页
        case 2:
          this.$store.state.SelectedActive=2;
          if(window.location.href!==this.$store.state.windowURL+'user/list') {
            this.$router.push({path: '/user/list'});
          }else
            location. reload();
          break;//用户列表（动画演示）
        case 3:
          this.$store.state.SelectedActive=3;
          if(window.location.href!==this.$store.state.windowURL+'user/profile'){
          this.$router.push({name: 'UserProfile'});}else
            location. reload();
          break;//（个人信息）
        case 4:
          this.$store.state.SelectedActive=1;
          if(window.location.href!==this.$store.state.windowURL+'user/Content'){
          this.$router.push({path: '/user/Content'});}else
            location. reload();

          break;//博文
        case 5:
          this.$store.state.SelectedActive=4;
          if(window.location.href!==this.$store.state.windowURL+'user/MessageList'){
          this.$router.push({path: '/user/MessageList'});}else
            location. reload();
          break;//消息中心
      }
    },
    LoadUser() {
      const _this = this;
      this.axios.get(_this.$store.state.baseURL + '/user/selectUserByname?' + 'name=' + _this.$store.state.user.name).then(res => {
        if(res.data!="no") {
          _this.$store.state.user = res.data;
        }
        //console.log(res.data)
      }).catch(error => {
        _this.$notify.error({
          title: '失败',
          message: '服务器正在忙碌中'
        });
      });
    },
  LoadInformation(){
    //加载信息
    this.GetCollection();
    this.LoadUser();
    sessionStorage.setItem('store',JSON.stringify(this.$store.state));
  }
  },
  mounted() {
  },
  computed: {},
  beforeRouteLeave: (to, from, next) => {
    next();
  },
  created() {
    this.$nextTick(()=>{
      this.LoadInformation();
    })
  }


}


</script>

<style scoped>
.el-header {
  background-color:#545c64;
  text-color:#fff;
  active-text-color:#ffd04b;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.a {
  text-underline: none;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
