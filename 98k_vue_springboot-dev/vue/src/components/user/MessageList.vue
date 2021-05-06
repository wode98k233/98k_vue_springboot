<template>
<div>

  <div>
    <el-radio-group v-model="model">
      <el-radio-button label="1" >收件箱</el-radio-button>
      <el-radio-button label="2" >发件箱</el-radio-button>
    </el-radio-group>
  </div>
  <div v-if="model==='1'" :key="model">
    <div>
      <template>
        <el-table
          :data="tableData.filter(data => !search || data.fname.toLowerCase().includes(search.toLowerCase()))"
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="内容">
                  <span>{{ props.row.text }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="发送方"
            prop="fname">
          </el-table-column>
          <el-table-column
            label="发送时间"
            prop="time">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="mini"
                placeholder="输入发送方的关键字搜索"/>
            </template>
            <template slot-scope="scope">
              <el-button
                size="mini"
                icon="el-icon-s-promotion"
                type="primary"
                @click="Callback(scope.row)"
                ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>
  </div>
  <div v-if="model==='2'" :key="model">
    <h1>发消息</h1>
    <el-input style="width:450px" clearable maxlength="30" show-word-limit placeholder='请输入用户名字' type="text"
              v-model="sname">
    </el-input>
    <br><br>
    <el-input style="width:800px"  clearable maxlength="150" show-word-limit placeholder='请输入正文'
              type="textarea" v-model="text">
    </el-input>
    <el-button type="primary" @click="Submit()">发送</el-button>
  </div>
</div>
</template>

<script>
export default {
  name: "MessageList",
  data(){
    return{
      //收件箱，发消息
      model:'2',
      tableData: [{
        time: '2021-04-02 11:23:16',
        fid: 88888888,
        fname:'无连接',
        sid:10000000,
        sname:'无连接',
        text:'无内容'
      }],
      search:null,
      text:'',
      sname:this.$store.state.sname,
    }
  },
  methods:{
    Callback(val){
      //先跳转到发送页面，再导入初始信息，只需填写回复内容（text）即可
      const self=this;
      this.model='2';
      //console.log(val)
      this.sname=val.fname;
    },
    SubmitInformation(val){
      const self = this;
      this.$axios.post(self.$store.state.baseURL+"/message/insert",
        {
          time: '2021-04-02 11:23:16',
          mid:0,
          fid: self.$store.state.user.id,
          fname:self.$store.state.user.name,
          sid:val.id,
          sname:val.name,
          text:self.text,
        }).then(res=>{
        self.$notify.success({
          title: "(●'◡'●)",
          message: '成功发送'
        });
          self.$store.state.sname=val.name;
          //console.log(res.data);
      }).catch(err=>{
        self.$notify.warning({
          title: '错误',
          message: '没有该用户'
        });
      })
    },
    Submit(){
      //先获取有无这个用户，有则发送
      const self = this;
      this.$axios.get(self.$store.state.baseURL+"/user/selectUserByname?name="+self.sname).then(res=>{
        if(res.data.id>0){
          //有
          self.SubmitInformation(res.data);
        }else {
          self.$notify.warning({
            title: '失败',
            message: '没有该用户'
          });
        }
      }).catch(err=>{
        self.$notify.warning({
          title: '失败',
          message: '没有该用户'
        });
      })
      },
    GetMessage(){
      const self = this;
      this.$axios.get(self.$store.state.baseURL+"/message/select?sid="+self.$store.state.user.id).then(res=>{
       self.tableData=res.data;
      }).catch(err=>{
        console.log(err)
        self.$notify.error({
          title: '错误',
          message: '服务器在忙'
        });
      })
    }
    ,
    LoadInformation(){
      this.GetMessage();
    }
  },
  created(){
    this.$nextTick(()=>{
      this.LoadInformation();
    })
  }
}
</script>

<style scoped>

</style>
