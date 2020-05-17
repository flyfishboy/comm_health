<template>
    <div >
        <el-input style="width: 20%" v-model="admin.name" size="mini" placeholder="输入姓名搜索" @clear="getList()" clearable/>&nbsp;
        <el-button  @click="findName">搜索</el-button>
        <el-button @click="ExportData">导出表格</el-button>
        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    label="姓名"
                    prop="name">
            </el-table-column>
            <el-table-column
                    label="账号"
                    prop="account">
            </el-table-column>
            <el-table-column
                    label="电话号码"
                    prop="tel">
            </el-table-column>


        </el-table>
        <div class="block" align="center">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="page"
                    :current-page.sync="currentPage"
                    :page-size="pageSize"
                    layout="prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                flag:'1',
                pageSize: 0,
                total: 0,
                currentPage:1,
                tableData: [],
                tableData2: [],
                admin: {
                    name: '',
                }
            }
        },
        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            getList() {
                const _this = this;
                axios.get('http://localhost:8181/admin/findAll/0/6').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
            //获取管理员所有数据并打印
            getAll() {
                const _this = this;
                axios.get('http://localhost:8181/admin/find_excel').then(function (resp) {
                    _this.tableData2 = resp.data;
                })

            },
            // 导出表格数据
            ExportData() {
                var that = this;
                require.ensure([], () => {
                    const export2excel = require("../../vendor/Export2Excel");
                    const tHeader = ["姓名", "账号", "电话号码"];
                    const filterVal = ['name', 'account', 'tel'];
                    const list = that.tableData2;
                    const data = that.formatJson(filterVal, list);
                    console.log(list);
                    if (list.length >= 1) {
                        let date = new Date();
                        export2excel.export_json_to_excel(tHeader, data, '管理员数据')
                    } else {
                        that.$message({
                            showClose: true,
                            type: 'warning',
                            message: '没有符合条件的数据'
                        });
                        return;
                    }
                })
            },
            export: function export_json_to_excel(th,jsonData,defaultTitle){
                var data = jsonData;
                data.unshift(th);
                var ws_name = "sheet";
                var wb = new Workbook(),ws = sheet_from_array_of_arrays(data);

                const colWidth = data.map(row =>row.map(val =>{
                    if (val == null){
                        return {'wch':10};
                    }
                    else if(val.toString().charCodeAt(0) > 255){
                        return {'wch':val.toString().length * 2 + 5};
                    }else {
                        return {'wch':val.toString().length + 5};
                    }
                }));
                let result = colWidth[0];
                for (let i = 1; i < colWidth[i].length; j++){
                    for (let j = 0; j < colWidth[i].length; j++){
                        if (result[j]['wch'] < colWidth[i][j]['wch']) {
                            result[j]['wch'] = colWidth[i][j]['wch'];
                        }
                    }
                }
                ws['!cols'] = result;
                wb.SheetNames.push(ws_name);
                wb.Sheets[ws_name] = ws;
            },

            formatJson(filterVal,jsonData){
                return jsonData.map(v =>
                    filterVal.map(j => v[j]));
            },

            page(currentPage){
                const _this = this;
                if(_this.flag == 1){
                axios.get('http://localhost:8181/admin/findAll/'+(currentPage-1)+'/6').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                }) } else{
                axios.post('http://localhost:8181/admin/findAllByName/'+(currentPage-1)+'/6', this.admin).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
                }
            },
            findName() {
                const _this = this;
                _this.flag = 2;
                axios.post('http://localhost:8181/admin/findAllByName/0/6', this.admin).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
        },


        created(){
           this.getList();
           this.getAll()
        }
    }
</script>