<template>
  <div style="padding: 10px">
<!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">Add</el-button>
<!--      <el-button type="primary">Import</el-button>-->
<!--      <el-button type="primary">Export</el-button>-->
    </div>

<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="Enter key words" style="width: 20% " clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">Search</el-button>
    </div>

<!--    内容显示区-->
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID"
          sortable
      >
      </el-table-column>
      <el-table-column
          prop="name"
          label="名称">
      </el-table-column>
      <el-table-column
          prop="price"
          label="单价">
      </el-table-column>
      <el-table-column
          prop="author"
          label="作者">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="出版时间">
      </el-table-column>
      <el-table-column label="Operation">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)" >Edit</el-button>
          <el-popconfirm title="Confirm delete?？" @confirm="handleDelete(scope.row.id)" >
            <template #reference>
              <el-button size="mini" type="danger" >Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSzie"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

      <el-dialog title="Infomation" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item  label="名称">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item  label="价格">
            <el-input v-model="form.price" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item  label="作者">
            <el-input v-model="form.author" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item  label="出版时间">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable></el-date-picker>
          </el-form-item>

          <el-form-item  label="Address">
            <el-input  type="textarea" v-model="form.address" style="width: 80%"></el-input>
          </el-form-item>

        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="save">Confirm</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'Book',
  components: {
  },
  data(){
    return{
      form:{},
      dialogVisible:false,
      currentPage:1,
      pageSize:10,
      total:0,
      search:'',
      tableData:[

      ]
    }
  },

  created() {
    this.load()
  },
  methods:{

    load(){
      request.get('/book',{
        params:{
          pageNum : this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add(){
      this.dialogVisible=true
      this.form={}

    },

    save(){
      if(this.form.id){  //update
        request.put('/book',this.form).then(res =>{
          console.log(res)
          if (res.code === '0'){
            this.$message({
              type:"success",
              message:"Update successfully"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()   //refresh data
          this.dialogVisible=false  //close the dialog

        })
      }else{
        request.post('/book',this.form).then(res =>{  //add
          console.log(res)
          if (res.code === '0'){
            this.$message({
              type:"success",
              message:"Add successfully"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()   //refresh data
          this.dialogVisible=false  //close the dialog
        })
      }


    },

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    handleDelete(id){
      console.log(id)
      request.delete('/book/'+ id).then(res => {
        console.log(res)
        if (res.code === '0'){
          this.$message({
            type:"success",
            message:"Delete successfully"
          })
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load() //refresh the table data
      })

    },

    handleSizeChange(pageSize){   //change PageSize
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){  //Change PageNumber
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>
