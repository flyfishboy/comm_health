<template>
    <div>
        <el-input style="width: 20%" v-model="sendemail.time" size="mini" placeholder="输入日期搜索" @clear="getList()" clearable/>&nbsp;
        <el-button  @click="findTime">搜索</el-button>
        <el-button @click="ExportData">导出表格</el-button>
        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    label="制定时间"
                    prop="time">
            </el-table-column>
            <el-table-column
                    label="计划标题"
                    prop="title">
            </el-table-column>
            <el-table-column
                    label="计划内容"
                    prop="text">
            </el-table-column>

            <el-table-column
                    align="right"
                    label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="Delete_A(scope.row)">删除
                    </el-button>
                </template>
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
        inject:['reload'],                                 //注入App里的reload方法
        data() {
            return {
                flag:'1',
                pageSize: 0,
                total: 0,
                tableData: [],
                tableData2: [],
                currentPage:1,
                sendemail: {
                    account:sessionStorage.getItem('account'),
                    time:''
                },
            }
        },

        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },

            getList() {
                const _this = this;
                axios.post('http://localhost:8181/email/findAllByAccount/0/6',this.sendemail).then(function (resp) {
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            //获取用户所有制定计划数据并打印
            getAll() {
                const _this = this;
                axios.post('http://localhost:8181/email/find_excel',this.sendemail).then(function (resp) {
                    _this.tableData2 = resp.data;

                })

            },

            // 导出表格数据
            ExportData() {
                const that = this;
                require.ensure([], () => {
                    const export2excel = require("../../vendor/Export2Excel");
                    const tHeader = ["测量时间", "标题", "内容"];
                    const filterVal = ['time', 'title', 'text'];
                    const list = that.tableData2;
                    const data = that.formatJson(filterVal, list);
                    console.log(list);
                    if (list.length >= 1) {
                        let date = new Date();
                        export2excel.export_json_to_excel(tHeader, data, '健康计划数据')
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
                const data = jsonData;
                data.unshift(th);
                const ws_name = "sheet";
                const wb = new Workbook(),ws = sheet_from_array_of_arrays(data);

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

            // 模糊查找
            findTime() {
                const _this = this;
                _this.flag = 2;
                axios.post('http://localhost:8181/email/findAllByTime/0/6', this.sendemail).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            // 删除
            Delete_A(row) {
                const _this = this;
                axios.delete('http://localhost:8181/email/deleteById/'+row.id).then(function (resp) {
                    _this.$alert(' 删除成功！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            _this.reload();
                        }
                    })
                })
            },
            // 查询
            page(currentPage) {
                const _this = this;
                if(_this.flag == 1){
                axios.post('http://localhost:8181/email/findAllByAccount/' + (currentPage - 1) + '/6',this.sendemail).then(function (resp) {
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                }) } else {
                axios.post('http://localhost:8181/email/findAllByTime/' + (currentPage - 1) + '/6', this.sendemail).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
                }
            }

        },

        created() {
            this.getList();
            this.getAll()
        }
    }
</script>

<style scoped>

</style>