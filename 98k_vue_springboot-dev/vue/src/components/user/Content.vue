<template>
  <!--博文-->
  <div>
    <h1>博文</h1>
    <!--搜索-->
    <div style="margin-top: 15px;">
      <el-input placeholder="请输入内容" v-model="input3" class="input-with-select" style="width:600px;float:left" @keyup.enter.native="search(select)">
        <el-select v-model="select" slot="prepend" placeholder="请选择" style="width:120px" >
          <el-option label="标题" value='0' ></el-option>
          <el-option label="作者" value='1' ></el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search" @click="search(select)"></el-button>
      </el-input>
    </div>
    <br><br><br>
    <!--获取全部信息-->
    <el-row >
    <div v-for="(item) in Texts" >
      <div v-if='baseURL+"/picture/"+item.picture' >
     <TextCard :author='item.author'  :path="baseURL+'/picture/'+item.picture" :tid='item.tid' :title='item.path.substring(0,item.path.lastIndexOf("."))' >
      </TextCard>
      </div>
      </div>
    </el-row>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-sizes="[4, 8, 16, 32]"
        :page-size="4"
        :current-page="pageIndex-0"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import TextCard from './Content/Text.vue'

export default {
  name: "Content",
  components: {TextCard},
  data() {
    return {
      total:0,
      pageIndex:1,
      pageSize:4,
      select: '',//查询的方式(0:标题;1：作者)
      input3:'',
      Texts:this.$store.state.TextWords,
      baseURL:this.$store.state.baseURL,
    }
  },
  methods: {
    handleSizeChange(val) {
      //切换每页条数
      this.pageSize=val;
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/text/findAll/1/'+_this.pageSize).then(function (resp){
        _this.Texts = resp.data.content;
        //console.log(resp.data);
      }).catch(
        function (error){
          // 请求失败处理
          _this.$notify.error({
            title: '错误',
            message: '与服务器连接失败'
          });
        })
    },
    handleCurrentChange(val) {
      //页面切换
      this.pageIndex=val;
      //保存当前浏览的页面
      this.$store.state.pageIndex=val;
      //获取服务器内容
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/text/findAll/'+val+'/'+_this.pageSize).then(function (resp){
        _this.Texts = resp.data.content;
        //console.log(resp.data);
      }).catch(
        function (error){
          // 请求失败处理
          _this.$notify.error({
            title: '错误',
            message: '与服务器连接失败'
          });
        })
      //console.log(`当前页: ${val}`);
    },
    //搜索（标题，作者）
    search(val){
      const _this = this;

      switch (val){
        case '0':
          //console.log("按标题查询");
          this.$axios.get(this.$store.state.baseURL+'/text/selectByPath?path='+_this.input3+".html").then(function (resp){
            _this.Texts = resp.data;

          }).catch(
            function (error){
              // 请求失败处理
              _this.$notify.error({title: '错误', message: error});
            })
          break;
        case '1':
          //console.log("按作者查询");
          this.$axios.get(this.$store.state.baseURL+'/text/selectByAuthor?author='+_this.input3).then(function (resp){
            _this.Texts = resp.data;

          }).catch(
            function (error){
              // 请求失败处理
              _this.$notify.error({title: '错误', message: error});
            })
          break;
        default:
          console.log("查询模式选择出错了");

          break;
      }
    },
    LoadInformation(){
      const _this = this;
      this.$axios.get(this.$store.state.baseURL+'/text/findAll/'+_this.pageIndex+'/'+_this.pageSize).then(function (resp){
        _this.$store.state.TextWords = resp.data.content;
        _this.total=resp.data.totalElements;
        //console.log(resp.data.content);
      }).catch(
        function (error){
          // 请求失败处理
          //console.log(error)
          _this.$notify.error({
            title: '错误',
            message: '与服务器连接失败'
          });
        })
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
.input-with-select  {
  background-color: #fff;
}

</style>
