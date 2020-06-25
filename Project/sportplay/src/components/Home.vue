<template>
    <!-- 引入container布局 -->
    <el-container class="home-container">
        <el-header>
            <!-- <div>
                <img src="../assets/logo2.jpg" alt=""/>
                <span>个人运动平台</span>
            </div>             -->
            <el-button type="info" @click="logout">安全退出</el-button>
        </el-header>
        <el-container>
            <el-aside :width="isCollapse?'64px':'200px'"> 
            <div class="toggle-button" @click="toggleCollapse">|||</div>
            <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff" :collapse=isCollapse 
            :collapse-transition="false" :router="true" :default-active="activePath">
                <!-- 一级菜单 -->
            <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
                <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>{{item.title}}</span>
                </template>
            <!-- 二级菜单 -->
                <el-menu-item :index="it.path" v-for="it in item.sList" :key="it.id" @click="saveNavState(it.path)">
                    <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>{{it.title}}</span>
                </template>
                </el-menu-item>
            </el-submenu>
            </el-menu>
            </el-aside>
            <el-main>
                <router-view>

                </router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {
    data(){
        return{
            menuList:[],
            isCollapse:false,
            activePath:'/welcome',
        }
    },
    created(){
        //查询菜单列表
        this.getMenuList();
        this.activePath = window.sessionStorage.getItem("activePath");
    },
    methods:{
        //如果用户退出了应该清除session
        logout(){
            window.sessionStorage.clear();
            this.$router.push("/login");
        },
        //获取导航菜单
        async getMenuList(){
            const {data:res} = await this.$axios.get("menus");
            console.log(res);
            if(res.flag!=200) return this.$message.error("获取列表失败");
            this.menuList = res.menus;
        },
        //控制伸缩
        toggleCollapse(){
            this.isCollapse = !this.isCollapse;
        },
        //保存路径
        saveNavState(activePath){
            window.sessionStorage.setItem("activePath",activePath);
            this.activePath = activePath;  //给点击的菜单加高亮
        }
    },
}
</script>

  
<style scoped>
.home-container{
    height: 100%;
}
.el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    display: flex;
    justify-content: space-between;
    padding-left: 0%;
    color:#fff;
    font-size: 20px;
    align-items: center;
    > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
  
.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;

}

.el-menu {
    border-right: none;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

/* img{
    width: 60px;
    height: 60px;
} */

.toggle-button{
    background-color: #4a5064;
    font-size: 10px;
    line-height: 24px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer; 
}
</style>