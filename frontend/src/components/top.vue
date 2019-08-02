<template>
  <div>
    <h2>top</h2>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="number"
        label="Number"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="Name"
        width="180">
      </el-table-column>
      <el-table-column
        prop="score"
        label="Score">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

    import axios from 'axios'

  export default{
      data() {
          return {
              tableData: []
          }
      },
      created() {
          axios.get('/top').then(response => {
              var limit;
              if(response.data.length<10){
                  limit=response.data.length
              }else{
                  limit=10
              }
              for(var i=0; i<limit; i++) {
                  this.tableData.push({
                      number: i+1,
                      name: response.data[i].login,
                      score: response.data[i].score
                  });
              }
          }).catch(_ => {
              alert(_.response.data.message)
          });
      }
  }

</script>

<style>
  .el-table th, .el-table tr{background-color:black; color:yellow; text-align: center;}
  .el-table th, .el-table tr{background-color:black; color:yellow; text-align: center;}/*!!!*/
  .el-table td{ color: yellow; border: 1px solid yellow;}
  .el-table, .el-table th{border: none;}
  .el-table, .el-table th{border: none;}
</style>
