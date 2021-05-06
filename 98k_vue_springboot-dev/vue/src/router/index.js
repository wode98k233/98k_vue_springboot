import Vue from 'vue';
import Router from 'vue-router';
import Content from "../components/user/Content";
import Main from "../components/Main";
import Login from '../components/Login';
import Signup from '../components/Signup';
import UserList from '../components/user/List';
import UserProfile from '../components/user/Profile';
import NotFound from "../components/NotFound";
import TextMessage from "../components/user/Content/TextMessage";
import Write from "../components/user/Profile/Wirte";
import MesList from "../components/user/MessageList";
import Opro from "../components/user/Opro";
import SortText from "../components/user/SortText";
import store from "../assets/js/store"
import axios from "axios";
import {
  Message,
  MessageBox
} from 'element-ui'
Vue.use(Router)
const router= new Router({
  mode:'history',
  routes: [
    {
      //路由路径
      path: '/main',
      name: 'Main',
      //跳转的组件
      component: Main,
      //嵌套路由
      children: [
        //:id
        {path:'/user/profile',name:'UserProfile' ,component:UserProfile,props:true},//个人中心
        {path:'/user/list',component:UserList},//用户列表
        {path: '/user/content', component: Content,},//博文
        {path:'/user/Content/TextMessage',component:TextMessage},//博文：浏览全文页面
        {path:'/user/MessageList',name:'MesList' ,component:MesList,props:true},//消息中心
        {path:'/user/Opro',name:'Opro' ,component:Opro,props:true},//他人主页
        {path:'/main',name:'SortText' ,component:SortText,props:true},//推荐页面
      ]
    },
    {
      //路由路径
      path: '/user/Profile/Write',
      name: 'Write',
      props:true,
      //跳转的组件
      component: Write,
    },
    {
      //路由路径
      path: '/',
      name: 'Login',
      props:true,
      //跳转的组件
      component: Login,


    },
    {
      //路由路径
      path: '/signup',
      name: 'Signup',
      //跳转的组件
      component: Signup
    },
    {
      //路由路径
      path: '*',
      //跳转的组件
      component: NotFound
    }
  ]
})
export default router
router.beforeEach((to, from, next) => {

  /**
   * 先从store获取username和password，然后再访问后端登录接口，
   * 若返回ok，则登录成功可以继续跳转到该跳转的路由
   * */


  if (to.path === '/'||to.path==='/signup') {
    //console.log("现在去登录页")
    next();
  } else {
    /**
     * 获取session内数据
     * */
    let token = sessionStorage.getItem('Authorization');
    if (token === null || token === '') {
      next('/');
    } else {
      //console.log("跳转ing")
      next();
    }
  }
  window.setInterval(() => {
    setTimeout(function(){
      /**
       * 服务器端要把端口改成http://49.234.132.241/
       * */
      axios.get(  'http://localhost:8888/user/findAll').then(res => {
        if (res.data==='no'){
          Message({
            message: '登录信息已经过期，请重新登录',
            type: 'warning',
            duration: 30 * 1000
          })
        }
      }).catch(error => {
        Message({
          message: '服务器在忙',
          type: 'warning',
          duration: 30 * 1000
        })
      });
    }, 0)
  }, 60000)//五分钟看一下是否是未登录
})
