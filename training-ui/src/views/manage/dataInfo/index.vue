<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资料编号" prop="dataId">
        <el-input
          v-model="queryParams.dataId"
          placeholder="请输入资料编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资料标题" prop="dataTitle">
        <el-input
          v-model="queryParams.dataTitle"
          placeholder="请输入资料标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="院系" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入院系"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="更新人" prop="updatedBy">
        <el-input
          v-model="queryParams.updatedBy"
          placeholder="请输入更新人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['manage:dataInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:dataInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:dataInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:dataInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资料编号" align="center" v-if="columns[0].visible" prop="dataId" />
        <el-table-column label="资料标题" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="dataTitle" />
        <el-table-column label="资料描述" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="dataDesc" />
        <el-table-column label="资料文件" align="center" v-if="columns[3].visible" prop="dataFile" width="100">
        <template slot-scope="scope">
          <el-link v-show="scope.row.dataFile!=null"
                   :download="getFileName(scope.row.dataFile)"
                   :href="baseUrl+scope.row.dataFile"
                   :underline="false"
                   target="_blank"
          >
            <span style="cursor: pointer;"> 下载 </span>
          </el-link>
        </template>
      </el-table-column>
        <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="userId" />
        <el-table-column label="院系" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="deptId" />
        <el-table-column label="班级" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="classId" />
        <el-table-column label="创建时间" align="center" v-if="columns[7].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible" prop="updatedBy" />
        <el-table-column label="更新时间" align="center" v-if="columns[9].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:dataInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:dataInfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改资料信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资料标题" prop="dataTitle">
          <el-input v-model="form.dataTitle" placeholder="请输入资料标题" />
        </el-form-item>
        <el-form-item label="资料描述" prop="dataDesc">
          <el-input v-model="form.dataDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="资料文件" prop="dataFile">
          <file-upload v-model="form.dataFile"/>
        </el-form-item>
        <el-form-item label="创建人" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="院系" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入院系" />
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDataInfo, getDataInfo, delDataInfo, addDataInfo, updateDataInfo } from "@/api/manage/dataInfo";

export default {
  name: "DataInfo",
  data() {
    return {
      baseUrl: process.env.VUE_APP_BASE_API,
      //表格展示列
      columns: [
        { key: 0, label: '资料编号', visible: true },
          { key: 1, label: '资料标题', visible: true },
          { key: 2, label: '资料描述', visible: true },
          { key: 3, label: '资料文件', visible: true },
          { key: 4, label: '创建人', visible: true },
          { key: 5, label: '院系', visible: true },
          { key: 6, label: '班级', visible: true },
          { key: 7, label: '创建时间', visible: true },
          { key: 8, label: '更新人', visible: true },
          { key: 9, label: '更新时间', visible: true },
          { key: 10, label: '备注', visible: true },
        ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 资料信息表格数据
      dataInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dataId: null,
        dataTitle: null,
        userId: null,
        deptId: null,
        classId: null,
        createTime: null,
        updatedBy: null,
        updateTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dataTitle: [
          { required: true, message: "资料标题不能为空", trigger: "blur" }
        ],
        dataDesc: [
          { required: true, message: "资料描述不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "院系不能为空", trigger: "blur" }
        ],
        classId: [
          { required: true, message: "班级不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询资料信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listDataInfo(this.queryParams).then(response => {
        this.dataInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
      //获取文件名 此功能只可以下载只有一个文件的
      getFileName(filePath) {
        if (filePath == null) {
          return
        }
        // 提取文件名或根据需求生成文件名
        const fileName = filePath.substring(filePath.lastIndexOf('/') + 1)
        return fileName
      },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        dataId: null,
        dataTitle: null,
        dataDesc: null,
        dataFile: null,
        userId: null,
        deptId: null,
        classId: null,
        createTime: null,
        updatedBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.dataId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资料信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dataId = row.dataId || this.ids
      getDataInfo(dataId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资料信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dataId != null) {
            updateDataInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDataInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const dataIds = row.dataId || this.ids;
      this.$modal.confirm('是否确认删除资料信息编号为"' + dataIds + '"的数据项？').then(function() {
        return delDataInfo(dataIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/dataInfo/export', {
        ...this.queryParams
      }, `dataInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
