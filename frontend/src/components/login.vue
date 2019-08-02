<template>
  <div>
    <h2>login</h2>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
      <el-form-item label="Name" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
        <div class="nav" style="float: right">
          <router-link to="/registration">registration</router-link>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    import auth from '../auth'
export default {
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
        return {
            ruleForm: {
                pass: '',
                name: ''
            },
            rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.login()
                } else {
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        login () {
            auth.login(this.ruleForm.name, this.ruleForm.pass, loggedIn => {
                if (!loggedIn) {
                    alert('Нет такого пользователя')
                } else {
                    alert('Вход выполнен')
                    this.$router.replace(this.$route.query.redirect || '/')
                }
            })
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
  .el-button--primary{
    color:yellow;
    background-color:black;
    border-color:yellow
  }
  .el-button--primary:hover {background:yellow;
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

  .el-form-item__label {
    color: yellow
  }
</style>
