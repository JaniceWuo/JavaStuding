<template>
    <div class="login_container">
        <div class="login_box">
            <div class="avator_box">
                <img src="../assets/logo.png"/>
            </div>
            <!-- 表单区域 -->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm"  class="login_form" label-width="0">
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" prefix-icon="el-icon-key" type="password"></el-input>
                </el-form-item>

                <el-form-item class="btns">
                    <el-button type="primary" @click="login()">登录</el-button>
                    <el-button type="info" @click="resetLoginForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
// jQuery.support.cors = true;
import {mapMutations} from "vuex";
export default {
    data(){
        return{
            loginForm:{
                username:"admin",
                password:"123456"
            },
            userToken:'',  //加验证的token
            //验证对象
            loginRules:{
                //校验用户名
                username:[
                    { required: true, message: '请输入用户名', trigger: 'blur' },  //失去焦点时触发
                    { min: 3, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }  //长度验证
                ],
                //校验密码
                password:[
                    { required: true, message: '请输入密码', trigger: 'blur' },  //失去焦点时触发
                    { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' }  //长度验证
                ],
            },
        }
    },
    methods:{
        ...mapMutations(['changeLogin']),
        //重置表单内容
        resetLoginForm(){
            this.$refs.loginFormRef.resetFields();
        },
        //处理登录
        login(){
            this.$refs.loginFormRef.validate(async valid =>{
                //如果验证没成功
                if(!valid){
                    return
                }
                //成功
                const {data:res} = await this.$axios.post("login",this.loginForm);  //访问后台
                if(res.flag=="ok"){
                    window.sessionStorage.setItem("user",res.user); //存储用户
                    this.userToken = 'Bearer ' + res.token;
                    // this.changeLogin({ Authorization:this.userToken });
                    this.$message.success("操作成功");
                    this.$router.push({path:"/home"});  //页面路由跳转到home
                    console.log(res.user);
                }else{
                    this.$message.error("操作失败");
                }
            })
        },
    }
}
</script>

<style lang = "less" scoped>
.login_container{
    /* background-color: #2b4b6b; */
    height: 100%;
}
.login_box{
    width: 450px;
    height:300px;
    background-color: #fff;
    border-radius: 5px;
    position: absolute;
    left:35%;
    top:20%;
    .avator_box{
        width:130px;
        height:130px;
        border:1px solid #eee;
        border-radius: 50%;
        box-shadow: 0 0 10px #ddd;
        position: absolute;
        left:50%;
        padding: 2px;
        transform: translate(-50%,-50%);
        background-color:aquamarine;
        img{
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #eee;
        }

    }
}
.btns{
    display: flex;
    /* position: relative;
    left:28%; */
    justify-content: center;
}

.login_form{
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}
</style>