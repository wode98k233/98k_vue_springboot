<template>
<!--主页面-->
  <div id="app">
    <router-link to="/"></router-link>
    <router-view></router-view>


  </div>
</template>

<script>
import HelloWorld from "./components/Main";
import Content from "./components/user/Content";
import store from "./assets/js/store";
export default {
  name: 'App',
  components: {HelloWorld},

  created() {
    if (sessionStorage.getItem('store')) {
      store.replaceState(Object.assign({}, store.state, JSON.parse(sessionStorage.getItem('store'))));
    }

    //在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem('store', JSON.stringify(store.state));
    });
  }

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
