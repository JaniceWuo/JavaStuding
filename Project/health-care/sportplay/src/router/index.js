import Vue from 'vue'
import VueRouter from 'vue-router'
//引入login组件
import Login from '../components/Login.vue'

import Home from '../components/Home.vue'

import Welcome from '../components/Welcome.vue'

// import UserList from '../components/admin/UserList.vue'

import User from '../components/admin/User.vue'

Vue.use(VueRouter)

  const routes = [

    {
      path:"/",
      redirect:"/login"
    },
    {
      path:"/login",
      component:Login
    },
    {
      path:"/home",
      component:Home,
      redirect:"/welcome",
      children:[
        {path:"/welcome",component:Welcome,},
        // {path:"/user",component:UserList,},
        {path:"/user",component:User,},
      ]
    }
  ]

const router = new VueRouter({
  routes
})

//挂载路由导航守卫
router.beforeEach((to,from,next)=>{
  if(to.path=='/login') return next();
  // let token = localStorage.getItem('Authorization');
  // if (token === 'null' || token === '') {
  //   return next('/login');
  // } 
  const userFlag = window.sessionStorage.getItem("user");  //取出当前用户
  if(!userFlag){
    return next("/login")
  }
  next();

})

export default router
