import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import VueAxios from 'vue-axios';
import Vuex from 'vuex';
import store from './assets/js/store'
import staticPicture from "./assets/a.jpg"
Vue.config.productionTip = false

//显示声明，使用vuerouter
//Vue.use(ElementPlus)
Vue.use(router);
Vue.use(Vuex);
Vue.use(ElementUI);
Vue.use(VueAxios, axios);

Vue.prototype.$axios = axios;
//Vue.prototype.$http=axios;
new Vue({
  el: '#app',
  //配置路由
  router,
  store:store,
  components: {App},
  template: '<App/>',
  render: h => h(App),
  data: function () {
    return {

    }
  },


})
// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
  config => {
    if (sessionStorage.getItem('Authorization')) {
      config.headers.AUTH_TOKEN = sessionStorage.getItem('Authorization');
      //console.log(config.headers.AUTH_TOKEN)
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  });
