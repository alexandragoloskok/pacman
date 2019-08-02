<template>
  <div>
    <h2>registration</h2>

    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
      <el-form-item label="Name" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Confirm" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    import axios from 'axios'
    export default{
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password again'));
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('Two inputs don\'t match!'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    pass: '',
                    checkPass: '',
                    name: ''
                },
                rules: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                        axios.post('/registration', {username: this.ruleForm.name, password: this.ruleForm.pass}).catch(_ => {
                            alert(_.response.data.message)
                        });
                        location.href = "/#/login"
                    } else {
                        alert('error submit!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style>
  .el-button:focus {
    background-color:black;
    border-color: white;
    color:white;
  }
  .el-button {
    color:white;
    background-color:black;
    border-color:white
  }
  .el-button:hover {
    background:white;
    border-color:white;
    color:black
  }
  .el-button.is-active,.el-button:active {
    background:yellow;
    border-color:yellow;
    color:black
  }

  .el-button--primary:focus {
    background-color:black;
    border-color: yellow;
    color:yellow;
  }
  .el-button--primary {
    color:yellow;
    background-color:black;
    border-color:yellow
  }
  .el-button--primary:hover {
    background:yellow;
    border-color:yellow;
    color:black
  }
  .el-button--primary.is-active,.el-button--primary:active {
    background:white;
    border-color:white;
    color:black
  }

  .el-input, .el-input__inner:hover {
    border-color:yellow
  }
  .el-input, .el-input__inner:focus {
    border-color:yellow
  }

  .el-form-item__label{
    color: yellow
  }
</style>
