<template>
    <div>
        <el-input style="width: 20%" v-model="member.name" size="mini" placeholder="输入姓名搜索" @clear="getList()" clearable/>&nbsp;
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
                    label="密码"
                    prop="password">
            </el-table-column>
            <el-table-column
                    label="性别"
                    prop="sex">
            </el-table-column>
            <el-table-column
                    label="电话号码"
                    prop="tel">
            </el-table-column>
                <el-table-column
                        label="邮箱"
                        prop="email">
                </el-table-column>
            <el-table-column
                    label="健康情况"
                    prop="health">
            </el-table-column>

                <el-table-column
                        align="right">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="text"
                                @click="Edit_A(scope.row)">修改
                        </el-button>
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
        data() {
            return {
                flag:'1',
                pageSize: 0,
                total: 0,
                tableData: [],
                tableData2: [],
                currentPage:1,
                member: {
                    name: ''
                }
            }
        },

        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            getList() {
                const _this = this;
                axios.get('http://localhost:8181/member/findAll/0/6').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
            //获取用户所有数据并打印
            getAll() {
                const _this = this;
                axios.get('http://localhost:8181/member/find_excel').then(function (resp) {
                    // console.log(resp);
                    _this.tableData2 = resp.data;

                })

            },
            // 导出表格数据
            ExportData() {
                const that = this;
                require.ensure([], () => {
                    const export2excel = require("../../vendor/Export2Excel");
                    const tHeader = ["姓名", "账号", "密码", '性别', "电话号码","邮箱","健康情况"];
                    const filterVal = ['name', 'account', 'password', 'sex', 'tel','email','health'];
                    const list = that.tableData2;
                    const data = that.formatJson(filterVal, list);
                    console.log(list);
                    if (list.length >= 1) {
                        let date = new Date();
                        export2excel.export_json_to_excel(tHeader, data, '用户数据')
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
            findName() {
                const _this = this;
                _this.flag = 2;
                axios.post('http://localhost:8181/member/findAllByName/0/6', this.member).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
            Delete_A(row) {
                const _this = this;
                axios.delete('http://localhost:8181/member/deleteById/'+row.id).then(function (resp) {
                        _this.$alert(''+row.name+'  删除成功！', '消息', {
                            confirmButtonText: '确定',
                            callback: action => {
                               window.location.reload()
                            }
                        })
                })
            },

            Edit_A(row) {   //修改方法
                this.$router.push({
                    path:'/Admin_Update',
                    query:{
                        id:row.id,
                    }
                })
            },

            page(currentPage) {
                const _this = this;
                if(_this.flag == 1){
                axios.get('http://localhost:8181/member/findAll/' + (currentPage - 1) + '/6').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                }) } else {
                axios.post('http://localhost:8181/member/findAllByName/' + (currentPage - 1) + '/6', this.member).then(function (resp) {
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