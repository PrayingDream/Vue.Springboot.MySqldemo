<script>
  import axios from "axios";

  export default {
    data() {
      return {
        port: 'localhost',
        form: {
          id: 0,
          jobNumber:'',
          name:'',
          age:'',
          idNumber:'',
          phoneNumber:'',
          department:'',
          salary:'',
          deleteSign:0,
        },
        rules: {
          jobNumber: [
            {
              required: true, message: '请输入8位数字工号', trigger: 'blur'
            },
            {
              pattern: /^[0-9]{8}$/, message: '请输入8位有效数字', trigger: 'blur'
            }
          ],
          name: [
            {
              required: true, message: '请输入名字', trigger: 'blur'
            },
            {
              min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'
            }
          ],
          age: [
            {
              required: true, message: "请输入大于0的整数" , trigger: "blur"
            },
            {
              type: 'number',message: '请输入数字',trigger: "blur"
            },
            {
              pattern: /^[1-9]\d*$/,message: '输入数字必须大于0',trigger: "blur"
            }
          ],
          idNumber: [
            {
              required: true, message: "请填写身份证号" ,trigger: "blur"
            },
            {
              pattern: /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/ ,message: "请正确输入18位身份证号" ,trigger: "blur"
            }
          ],
          phoneNumber: [
            {
              required: true, message: "请填写手机号", trigger: "blur"
            },
            {
              pattern: /^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\d{8}$/ ,message: "请正确填写手机号",trigger: "blur"
            }
          ],
          department: [
            {
              required: true, message: '请输入部门名', trigger: 'blur'
            },
            {
              min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'
            }
          ],
          salary: [
            {
              required: true, message: '请输入薪水', trigger: 'blur'
            },
            {
              pattern: /^0\.([1-9]|\d[1-9])$|^[1-9]\d{0,8}\.\d{0,2}$|^[1-9]\d{0,8}$/,message: '请输入大于0的数字',trigger: "blur"
            }
          ]
        }
      }
    },
    methods: {
      onSubmit() {
        var that=this;
        this.$refs['form'].validate(valid => {
          if (valid) {
            this.salary = +this.salary;
            axios.post('http://'+that.port+':3000/user/insert',that.form).then(function (response) {
              if (response.data.code === 200) {
                that.$notify({
                  title:'添加成功',
                  message:'...成功',
                  type:'success',
                  duration:2000
                })
                that.$refs['form'].resetFields();
              } else {
                that.$notify({
                  title:'添加失败' ,
                  message:response.data.message,
                  type:'warning',
                  duration:2000
                })
              }
            })
          } else {
            this.$notify({
              title:'请正确填写信息',
              message:'...错误',
              type:'warning',
              duration:2000
            })
          }
        })
      },
      resetForm() {
        this.$refs['form'].resetFields();
      },
    }
  }
</script>

<template>
  <div>
    <el-form ref="form" :rules="rules" :model="form" label-width="auto" >
      <el-row type="flex" justify="center">
        <el-col :span="8" >
          <el-form-item label="工号" prop="jobNumber">
            <el-input v-model="form.jobNumber"></el-input>
          </el-form-item>
          <el-form-item label="名字" prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model.number="form.age"></el-input>
          </el-form-item>
          <el-form-item label="身份证号" prop="idNumber">
            <el-input v-model="form.idNumber"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phoneNumber">
            <el-input v-model="form.phoneNumber"></el-input>
          </el-form-item>
          <el-form-item label="部门" prop="department">
            <el-input v-model="form.department"></el-input>
          </el-form-item>
          <el-form-item label="薪资" prop="salary">
            <el-input v-model="form.salary" oninput="value=value.replace(/[^0-9.]/g,'')"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit();">立即创建</el-button>
            <el-button @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<style scoped>

</style>