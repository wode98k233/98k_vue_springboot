import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state:{
    /**
     * 高频访问数据
     * */
    sname:'',//消息对象名
    pageIndex:1,
    Opid:null,
    user: {
      btime: "2021-04-21T00:00:00.000+0000",
      email: "100",
      id: 1,
      password:'',
      jurisdiction: 1,
      name: "admin",
      phone: "01566548",
      pic: "../assets/4041.jpg",
      sex: "男",
    },
    SelectedId: '',
    users: [{
      id: '1',
      name: '没有该用户',
      sex: '男',
      jurisdiction:0,
      email:'',
      pic:'',
      btime:null,
      phone:'',
    }],
    TextWords: [{
      tid: '1',
      author: '没有该用户',
      name:'',
      path: '服务器无响应.txt',
      tag: '随笔',
      picture: '../assets/4041.jpg',
      up:0,//点赞数
      collection:0,//收藏数
      time:null,
      size:'0.01MB',
    }],
      /**
       * 服务器端要把端口改成http://49.234.132.241/
       * */
      baseURL:"http://localhost:8888",//后端端口
      windowURL:"http://localhost:8080/",//本地端口
      // 存储token
      Authorization: sessionStorage.getItem('Authorization') ? sessionStorage.getItem('Authorization') : '',
      //选择的main的子页面
      SelectedActive: 0
  },
  getters:{},
  mutations:{},
  actions:{},
})
