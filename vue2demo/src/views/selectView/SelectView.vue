<script>
import axios from "axios";

export default {
  data() {
    return {
      port: 'localhost',
      dialogVisible: false,
      selectForm: {
        jobNumber: null,
        name: null,
      },
      getForm: [{
        id: 0,
        jobNumber: null,
        name: null,
        age: '',
        idNumber: '',
        phoneNumber: '',
        department: '',
        salary: '',
        deleteSign: 0,
      }],
      state: {
        jobNumber: false,
        name: true,
        isUpdate: true,
      },
      updateForm: {
        id: 0,
        jobNumber: null,
        name: null,
        age: '',
        idNumber: '',
        phoneNumber: '',
        department: '',
        salary: '',
        deleteSign: 0,
      },
      rules: {
        jobNumber: [
          {
            pattern: /^[0-9]{8}$/, message: '请输入8位有效数字', trigger: 'blur'
          }
        ],
        name: [
          {
            min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'
          }
        ],
      },
      dialogRules: {
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
            required: true, message: "请输入大于0的整数", trigger: "blur"
          },
          {
            type: 'number', message: '请输入数字', trigger: "blur"
          },
          {
            pattern: /^[1-9]\d*$/, message: '输入数字必须大于0', trigger: "blur"
          }
        ],
        idNumber: [
          {
            required: true, message: "请填写身份证号", trigger: "blur"
          },
          {
            pattern: /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
            message: "请正确输入18位身份证号",
            trigger: "blur"
          }
        ],
        phoneNumber: [
          {
            required: true, message: "请填写手机号", trigger: "blur"
          },
          {
            pattern: /^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\d{8}$/, message: "请正确填写手机号", trigger: "blur"
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
            pattern: /^0\.([1-9]|\d[1-9])$|^[1-9]\d{0,8}\.\d{0,2}$|^[1-9]\d{0,8}$/,
            message: '请输入大于0的数字',
            trigger: "blur"
          }
        ]
      }
    }
  },
  methods: {
    switchInput() {
      var that = this;
      that.state.jobNumber = !that.state.jobNumber;
      that.state.name = !that.state.name;
    },
    resetForm() {
      this.$refs['selectForm'].resetFields();
    },
    onSubmit() {
      var that = this;
      axios.post('http://' + that.port + ':3000/user/select', that.selectForm).then(function (response) {
        that.getForm = response.data.data;
        if (response.data.code === 200) {
          that.$notify({
            title: '查询成功',
            message: '...成功',
            type: 'success',
            duration: 2000
          });
        } else {
          that.$notify({
            title: '查询失败',
            message: '...失败',
            type: 'warning',
            duration: 2000
          })
        }
      })
    },
    deleteUser(id) {
      var that = this;
      if (id !== 0) {
        axios.post('http://' + that.port + ':3000/user/delete?id=' + id).then(function (response) {
          if (response.data.code === 200) {
            axios.post('http://' + that.port + ':3000/user/select', that.selectForm).then(function (response) {
              that.getForm = response.data.data;
              that.$notify({
                title: '删除成功',
                message: '...成功',
                type: 'success',
                duration: 2000
              });
            })
          } else {
            that.$notify({
              title: '删除失败',
              message: '删除失败',
              type: 'warning',
              duration: 2000
            });
          }
        });
      } else {
        this.$notify({
          title: '请先查询信息',
          message: '...失败',
          type: 'error',
          duration: 2000
        });
      }

    },
    updateUser() {
      var that = this;
      this.$refs['updateForm'].validate(valid => {
        if (valid) {
          axios.post('http://'+ that.port +':3000/user/update', this.updateForm).then(function (response) {
            console.log(response);
            if (response.data.code === 200) {
              axios.post('http://' + that.port + ':3000/user/select', that.selectForm).then(function (response) {
                that.getForm = response.data.data;
                that.$notify({
                  title: '编辑成功',
                  message: response.data.message,
                  type: 'success',
                  duration: 2000
                });
              })
            } else {
              that.$notify({
                title: '编辑失败',
                message: response.data.message,
                type: 'warning',
                duration: 2000
              });
            }
          });
          that.dialogVisible = false;
        } else {
          that.$notify({
            title: '请正确填写信息',
            message: '...错误',
            type: 'warning',
            duration: 2000
          })
        }
      })
    },
    dialog(user) {
      if (user.jobNumber != null) {
        this.updateForm = Object.assign({}, user)
        this.dialogVisible = true;
      } else {
        this.$notify({
          title: '请先查询信息',
          message: '...查询',
          type: 'error',
          duration: 2000
        })
      }
    }
  }
}
</script>

<template>
  <div>
    <el-form ref="selectForm" :inline="true" :rules="rules" :model="selectForm" label-width="auto">
      <el-row type="flex" justify="center">
        <el-col :span="16">
          <el-button @click="switchInput();resetForm()">切换</el-button>
          <el-form-item label="工号" prop="jobNumber">
            <el-input v-model="selectForm.jobNumber" :disabled="state.jobNumber"></el-input>
          </el-form-item>
          <el-form-item label="名字" prop="name">
            <el-input v-model="selectForm.name" :disabled="state.name"></el-input>
          </el-form-item>
          <el-button @click="onSubmit">查询</el-button>
        </el-col>
      </el-row>
    </el-form>
    <el-table
        :data="getForm"
        :key="state.isUpdate"
        style="width: 100%"
        stripe>
      <el-table-column prop="jobNumber" label="工号" width=auto>
      </el-table-column>
      <el-table-column prop="name" label="姓名" width=auto>
      </el-table-column>
      <el-table-column prop="age" label="年龄" width=auto>
      </el-table-column>
      <el-table-column prop="idNumber" label="身份证号" width="200px">
      </el-table-column>
      <el-table-column prop="phoneNumber" label="手机号" width=auto>
      </el-table-column>
      <el-table-column prop="department" label="部门" width=auto>
      </el-table-column>
      <el-table-column prop="salary" label="薪资" width=auto>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template v-slot="scope">
          <el-button @click="deleteUser(scope.row.id);" type="text" size="small">移除</el-button>
          <el-button @click="dialog(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="修改表单弹窗" :visible.sync="dialogVisible" :key="dialogVisible">
      <el-form ref="updateForm" :model="updateForm" :rules="dialogRules" label-width="auto">
        <el-row type="flex" justify="center">
          <el-col :span="8">
            <el-form-item label="工号" prop="jobNumber">
              <el-input v-model="updateForm.jobNumber"></el-input>
            </el-form-item>
            <el-form-item label="名字" prop="name">
              <el-input v-model="updateForm.name"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input v-model.number="updateForm.age"></el-input>
            </el-form-item>
            <el-form-item label="身份证号" prop="idNumber">
              <el-input v-model="updateForm.idNumber"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phoneNumber">
              <el-input v-model="updateForm.phoneNumber"></el-input>
            </el-form-item>
            <el-form-item label="部门" prop="department">
              <el-input v-model="updateForm.department"></el-input>
            </el-form-item>
            <el-form-item label="薪资" prop="salary">
              <el-input v-model="updateForm.salary" oninput="value=value.replace(/[^0-9.]/g,'')"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateUser();">确定</el-button>
              <el-button @click="dialogVisible = false;">取消</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>