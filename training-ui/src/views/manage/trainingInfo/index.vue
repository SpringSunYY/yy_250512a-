<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="实训编号" prop="trainingId">
        <el-input
          v-model="queryParams.trainingId"
          placeholder="请输入实训编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实训标题" prop="trainingTitle">
        <el-input
          v-model="queryParams.trainingTitle"
          placeholder="请输入实训标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="daterangeStartTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="daterangeEndTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="实训地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入实训地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级" prop="classId" v-if="isClassQuery">
        <el-select
          v-model="queryParams.classId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入班级标题"
          :remote-method="selectClassInfoList"
          :loading="classLoading"
        >
          <el-option
            v-for="item in classInfoList"
            :key="item.classId"
            :label="item.className"
            :value="item.classId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="创建人" prop="userId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userId"-->
      <!--          placeholder="请输入创建人"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="班级" prop="deptId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.deptId"-->
      <!--          placeholder="请输入班级"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
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
      <!--      <el-form-item label="更新人" prop="updatedBy">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.updatedBy"-->
      <!--          placeholder="请输入更新人"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="更新时间">-->
      <!--        <el-date-picker-->
      <!--          v-model="daterangeUpdateTime"-->
      <!--          style="width: 240px"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          type="daterange"-->
      <!--          range-separator="-"-->
      <!--          start-placeholder="开始日期"-->
      <!--          end-placeholder="结束日期"-->
      <!--        ></el-date-picker>-->
      <!--      </el-form-item>-->
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
          v-hasPermi="['manage:trainingInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:trainingInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:trainingInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:trainingInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="trainingInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-card class="box-card" style="width: 80%;margin: 0 auto">
            <div slot="header" class="clearfix">
              <span>{{ props.row.trainingTitle }}</span>
            </div>
            <div class="text item">
              {{ props.row.trainingDesc }}
            </div>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column label="实训编号" align="center" v-if="columns[0].visible" prop="trainingId"/>
      <el-table-column label="实训标题" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="trainingTitle"
      />
      <el-table-column label="实训描述" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="trainingDesc"
      />
      <el-table-column label="实训文件" align="center" v-if="columns[3].visible" prop="trainingFile" width="100">
        <template slot-scope="scope">
          <el-link v-show="scope.row.trainingFile!=null"
                   :download="getFileName(scope.row.trainingFile)"
                   :href="baseUrl+scope.row.trainingFile"
                   :underline="false"
                   target="_blank"
          >
            <span style="cursor: pointer;"> 下载 </span>
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" v-if="columns[4].visible" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" v-if="columns[5].visible" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实训地点" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="location"
      />
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="userName"
      />
      <el-table-column label="院系" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="deptName"
      />
      <el-table-column label="班级" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="className"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[10].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="updatedBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[12].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleSelectedAdd(scope.row)"
            v-hasPermi="['manage:trainingSelectedInfo:add']"
          >选择实训
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:trainingInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:trainingInfo:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改实训信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="实训标题" prop="trainingTitle">
          <el-input v-model="form.trainingTitle" placeholder="请输入实训标题"/>
        </el-form-item>
        <el-form-item label="实训描述" prop="trainingDesc">
          <el-input v-model="form.trainingDesc" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="实训文件" prop="trainingFile">
          <file-upload :limit="1" v-model="form.trainingFile"/>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实训地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入实训地点" />
        </el-form-item>
        <el-form-item label="班级" prop="classId" v-if="isClassQuery">
          <el-select
            v-model="form.classId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入班级标题"
            :remote-method="selectClassInfoList"
            :loading="classLoading"
          >
            <el-option
              v-for="item in classInfoList"
              :key="item.classId"
              :label="item.className"
              :value="item.classId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改实训选择对话框 -->
    <el-dialog :title="title" :visible.sync="selectedOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormSelected">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTrainingInfo,
  getTrainingInfo,
  delTrainingInfo,
  addTrainingInfo,
  updateTrainingInfo
} from '@/api/manage/trainingInfo'
import { addTrainingSelectedInfo } from '@/api/manage/trainingSelectedInfo'
import { checkPermi } from '@/utils/permission'
import { listClassInfo } from '@/api/manage/classInfo'

export default {
  name: 'TrainingInfo',
  data() {
    return {
      isClassQuery: false,
      //班级相关信息
      classInfoList: [],
      classLoading: false,
      classQueryParams: {
        classTitle: '',
        pageNum: 1,
        pageSize: 100
      },
      //选择实训
      selectedOpen: false,
      baseUrl: process.env.VUE_APP_BASE_API,
      //表格展示列
      columns: [
        { key: 0, label: '实训编号', visible: false },
        { key: 1, label: '实训标题', visible: true },
        { key: 2, label: '实训描述', visible: true },
        { key: 3, label: '实训文件', visible: true },
        { key: 4, label: '开始时间', visible: true },
        { key: 5, label: '结束时间', visible: true },
        { key: 6, label: '实训地点', visible: true },
        { key: 7, label: '创建人', visible: true },
        { key: 8, label: '院系', visible: true },
        { key: 9, label: '班级', visible: true },
        { key: 10, label: '创建时间', visible: true },
        { key: 11, label: '更新人', visible: false },
        { key: 12, label: '更新时间', visible: false },
        { key: 13, label: '备注', visible: false },
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
      // 实训信息表格数据
      trainingInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeStartTime: [],
      // 备注时间范围
      daterangeEndTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        trainingId: null,
        trainingTitle: null,
        userId: null,
        deptId: null,
        createTime: null,
        updatedBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        trainingTitle: [
          { required: true, message: '实训标题不能为空', trigger: 'blur' }
        ],
        trainingDesc: [
          { required: true, message: '实训描述不能为空', trigger: 'blur' }
        ],
        trainingFile: [
          { required: false, message: '实训文件不能为空', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '创建人不能为空', trigger: 'blur' }
        ],
        deptId: [
          { required: true, message: '班级不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        classId: [
          { required: true, message: "班级不能为空", trigger: "blur" }
        ],
        startTime:[
          {required: true, message: "开始时间不能为空", trigger: "blur"}
        ],
        endTime:[
          {required: true, message: "结束时间不能为空", trigger: "blur"}
        ],
      }
    }
  },
  created() {
    this.getList()
    if (checkPermi(['manage:classInfo:list'])) {
      this.isClassQuery = true
      this.getClassInfoList()
    }
  },
  methods: {
    checkPermi,
    /**
     * 获取班级列表推荐
     * @param query
     */
    selectClassInfoList(query) {
      if (query !== '') {
        this.classLoading = true
        this.classQueryParams.classTitle = query
        setTimeout(() => {
          this.getClassInfoList()
        }, 200)
      } else {
        this.classInfoList = []
        this.classQueryParams.className = null
      }
    },
    /**
     * 获取班级信息列表
     */
    getClassInfoList() {
      //添加查询参数
      if (this.form.classId != null) {
        this.classQueryParams.classId = this.form.classId
      } else {
        this.classQueryParams.classId = null
      }
      if (this.classQueryParams.classTitle !== '') {
        this.classQueryParams.classId = null
      }
      listClassInfo(this.classQueryParams).then(res => {
        this.classInfoList = res?.rows
        this.classLoading = false
      })
    },
    //  打开选择实训
    handleSelectedAdd(row) {
      this.reset()
      this.form.trainingId = row.trainingId
      this.title = '选择实训-' + row.trainingTitle
      this.selectedOpen = true
    },
    //提交选择
    submitFormSelected() {
      addTrainingSelectedInfo(this.form).then(res => {
        this.$modal.msgSuccess('选择成功')
        this.selectedOpen = false
        this.getList()
      })
    },
    /** 查询实训信息列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      this.queryParams.params = {};
      if (null != this.daterangeStartTime && '' != this.daterangeStartTime) {
        this.queryParams.params["beginStartTime"] = this.daterangeStartTime[0];
        this.queryParams.params["endStartTime"] = this.daterangeStartTime[1];
      }
      if (null != this.daterangeEndTime && '' != this.daterangeEndTime) {
        this.queryParams.params["beginEndTime"] = this.daterangeEndTime[0];
        this.queryParams.params["endEndTime"] = this.daterangeEndTime[1];
      }
      listTrainingInfo(this.queryParams).then(response => {
        this.trainingInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
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
      this.open = false
      this.selectedOpen = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        trainingId: null,
        trainingTitle: null,
        trainingDesc: null,
        trainingFile: null,
        userId: null,
        deptId: null,
        createTime: null,
        updatedBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.daterangeStartTime = [];
      this.daterangeEndTime = [];
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.trainingId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加实训信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const trainingId = row.trainingId || this.ids
      getTrainingInfo(trainingId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改实训信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.trainingId != null) {
            updateTrainingInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addTrainingInfo(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const trainingIds = row.trainingId || this.ids
      this.$modal.confirm('是否确认删除实训信息编号为"' + trainingIds + '"的数据项？').then(function() {
        return delTrainingInfo(trainingIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/trainingInfo/export', {
        ...this.queryParams
      }, `trainingInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
