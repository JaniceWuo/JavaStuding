<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户列表</el-breadcrumb-item>
        </el-breadcrumb>

        <el-card>
            <el-row :gutter="10">
                <el-col :span="5">
                <el-input placeholder="请输入您要查询的姓名" v-model="queryInfo.query" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
                </el-input>
                </el-col>

                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
                </el-col>
            </el-row>

            <!-- 用户列表 -->
            <el-table :data="userlist" border  height="350">
                <el-table-column type="index"></el-table-column>
                <el-table-column label="姓名" prop="username"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="密码" prop="password"></el-table-column>
                <el-table-column label="角色" prop="role"></el-table-column>
                <el-table-column label="状态" prop="state">
                <template slot-scope="scope"> -->
                    <el-switch v-model="scope.row.state" @change="userStateChanged(scope.row)"></el-switch>
                </template>
                </el-table-column>


                <el-table-column label = "操作">
                    <template slot-scope="scope">
                        <!-- 修改 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
                        <!-- 删除 -->
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteUser(scope.row.id)"></el-button>
                        <!-- 权限 -->
                        <el-tooltip effect="dark" content="分配权限" placement="top-start" :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>

            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pageNum"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>

        </el-card>

        <!-- 创建新用户对话框 -->
        <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="40%" @close="addDialogClosed" >
             <!-- 内容主体区域 添加了表单验证规则addFormRules-->
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">  
            <!-- 用户名 -->
            <el-form-item label="用户名" prop="username" >
                <el-input v-model="addForm.username"></el-input>
            </el-form-item>
            <!-- 密码 -->
            <el-form-item label="密码" prop="password">
                <el-input v-model="addForm.password"></el-input>
            </el-form-item>
            <!-- 邮箱 -->
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="addForm.email"></el-input>
            </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
                <!-- 点击确定按钮即调用addUser事件 -->
            </span>
       </el-dialog>

        <!-- 修改用户对话框 -->
       <el-dialog title="修改用户信息" :visible.sync="editDialogVisible" width="40%" @colse="editDialogClosed">
       <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="60px">
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUserInfo">确 定</el-button>
      </span>
    </el-dialog>



    </div>
</template>

<script>
export default {
    data(){
        return{
            queryInfo: {
            query: "",
            pageNum: 1,
            pageSize: 5
      },
      userlist: [],// 用户列表
      total: 0, // 最大数据记录
      addDialogVisible:false,
      addForm:{
          username:'',
          password:'',
          email:'',
      },

      editForm:{},  //修改的用户信息表单
      editDialogVisible:false,
      addFormRules:{
           username:[
           { required: true, message: "请输入用户名", trigger: "blur" },
           { min: 5, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
        ],
            password:[
           { required: true, message: "请输入密码", trigger: "blur" },
           { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
        ],
            email:[
           { required: true, message: "请输入邮箱", trigger: "blur" },
           { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
        ],
    },
    editFormRules:{
        password:[
           { required: true, message: "请输入密码", trigger: "blur" },
           { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
          ],
        email:[
           { required: true, message: "请输入邮箱", trigger: "blur" },
           { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
          ],
    },
    };
    },
    created(){
        this.getUserList();
    },
    methods:{
        async getUserList(){
            const{data:res} = await this.$axios.get("allUser",{
                params:this.queryInfo   //带参数的传递
            });
            this.userlist = res.data;
            this.total = res.numbers;
        },
            //监听pageSize改变的事件
        handleSizeChange(newSize){
            this.queryInfo.pageSize = newSize;
            this.getUserList(); 
        },
        handleCurrentChange(newPage){
            // console.log("调用了页面切换事件");
            this.queryInfo.pageNum = newPage;
            this.getUserList();
        },
        addDialogClosed(){
            this.$refs.addFormRef.resetFields();// 重置表单项
        },
        // 修改用户状态
        async userStateChanged(userinfo) {
            const { data: res } = await this.$axios.put(
            `userState?id=${userinfo.id}&state=${userinfo.state}`
        );
            if (res != "success") {
                userinfo.state = !userinfo.state;
                return this.$message.error("操作失败！！！");
            }
            this.$message.success("操作成功！！！");
        },

        addUser(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res} = await this.$axios.post("addUser",this.addForm);
                // console.log(res);
                if(res != "success"){
                    userinfo.state = !userinfo.state;
                    return this.$message.error("操作失败！！！");
                }
                this.$message.success("操作成功！！！");
                this.addDialogVisible = false;
                this.getUserList();
            })
        },
        //展示修改框
        async showEditDialog(id){
            const{data:res} = await this.$axios.get("getUpdate",{
                params:{"id":id}
            });
            this.editForm = res;  //把当前查到的用户信息回显
            this.editDialogVisible = true;
        },
        // 关闭窗口
        editDialogClosed(){
            this.$refs.editFormRef.resetFields();
        },
        //点击确认修改的按钮事件  将修改的数据存回数据库中
        editUserInfo(){
            this.$refs.editFormRef.validate(async valid=>{
                if(!valid) return;
                const{data:res} = await this.$axios.put("editUser",this.editForm);  //this.editForm已经变为修改后的内容
                console.log(res);
                if (res != "success") return this.$message.error("操作失败！！！");
                this.$message.success("操作成功！！！");
                this.editDialogVisible = false;
                this.getUserList();  //调用这个方法应该也是为了回显
            });
        },
        async deleteUser(id){
            const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).catch(err => err)        
           if(confirmResult!='confirm'){
               return this.$message.info("已取消删除");
            }
            const {data:res} = await this.$axios.delete("deleteUser?id="+id);
            if (res != "success") {
                return this.$message.error("操作失败！！！");
            }
            this.$message.success("操作成功！！！");
            this.getUserList();
        },




    },

}
</script>

<style lang="less" scoped>

</style>