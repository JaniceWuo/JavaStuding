import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'

//导入axios进行跨域
import axios from 'axios'

// Vue.prototype.$http = axios  //挂载
Vue.prototype.$axios = axios;   //这里定义了$axios
//设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
