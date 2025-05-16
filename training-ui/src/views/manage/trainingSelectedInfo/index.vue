<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="选择编号" prop="selectedId">
        <el-input
          v-model="queryParams.selectedId"
          placeholder="请输入选择编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实训编号" prop="trainingId">
        <el-input
          v-model="queryParams.trainingId"
          placeholder="请输入实训编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交状态" prop="submitStatus">
        <el-select v-model="queryParams.submitStatus" placeholder="请选择提交状态" clearable>
          <el-option
            v-for="dict in dict.type.training_selected_submit_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间">
        <el-date-picker
          v-model="daterangeSubmitTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="评分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.training_selected_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="老师" prop="teacherId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.teacherId"-->
      <!--          placeholder="请输入老师"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="学生" prop="userId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userId"-->
      <!--          placeholder="请输入学生"-->
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
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['manage:trainingSelectedInfo:add']"-->
      <!--        >新增-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:trainingSelectedInfo:edit']"
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
          v-hasPermi="['manage:trainingSelectedInfo:remove']"
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
          v-hasPermi="['manage:trainingSelectedInfo:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"

          v-hasPermi="['manage:trainingSelectedInfo:list']"
        >平均分：{{ statics.avgScore }}
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          v-hasPermi="['manage:trainingSelectedInfo:list']"
        >通过率：{{ statics.passRate }}
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          v-hasPermi="['manage:trainingSelectedInfo:list']"
        >提交率：{{ statics.submitRate }}
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="trainingSelectedInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="选择编号" align="center" v-if="columns[0].visible" prop="selectedId"/>
      <el-table-column label="实训" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="trainingTitle"
      />
      <el-table-column label="报告内容" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="content"
      />
      <el-table-column label="报告文件" align="center" v-if="columns[3].visible" prop="contentFile" width="100">
        <template slot-scope="scope">
          <el-link v-show="scope.row.contentFile!=null"
                   :download="getFileName(scope.row.contentFile)"
                   :href="baseUrl+scope.row.contentFile"
                   :underline="false"
                   target="_blank"
          >
            <span style="cursor: pointer;"> 下载 </span>
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="提交状态" align="center" v-if="columns[4].visible" prop="submitStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.training_selected_submit_status" :value="scope.row.submitStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" v-if="columns[5].visible" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评分" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="score"
      />
      <el-table-column label="评语" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="comment"
      />
      <el-table-column label="状态" align="center" v-if="columns[8].visible" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.training_selected_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="老师" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="teacherName"
      />
      <el-table-column label="学生" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="userName"
      />
      <el-table-column label="班级" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="className"
      />
      <el-table-column label="院校" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="deptName"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[13].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[14].visible"
                       prop="updatedBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[15].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[16].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:trainingSelectedInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleScore(scope.row)"
            v-hasPermi="['manage:trainingSelectedInfo:score']"
          >评分
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:trainingSelectedInfo:remove']"
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

    <!-- 添加或修改实训选择对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="实训" prop="trainingId">-->
        <!--          <el-input v-model="form.trainingId" placeholder="请输入实训"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="报告内容" prop="content">
          <el-input v-model="form.content" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="报告文件" prop="contentFile">
          <file-upload :limit="1" v-model="form.contentFile"/>
        </el-form-item>
        <el-form-item label="提交状态" prop="submitStatus">
          <el-radio-group v-model="form.submitStatus">
            <el-radio
              v-for="dict in dict.type.training_selected_submit_status"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <!--        <el-form-item label="状态" prop="status">-->
        <!--          <el-radio-group v-model="form.status">-->
        <!--            <el-radio-->
        <!--              v-for="dict in dict.type.training_selected_status"-->
        <!--              :key="dict.value"-->
        <!--              :label="dict.value"-->
        <!--            >{{ dict.label }}-->
        <!--            </el-radio>-->
        <!--          </el-radio-group>-->
        <!--        </el-form-item>-->
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
    <el-dialog :title="title" :visible.sync="openScore" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评分" prop="score" v-if="checkPermi(['manage:trainingSelectedInfo:score'])">
          <el-input-number :min="0" :max="100" v-model="form.score" placeholder="请输入评分"/>
        </el-form-item>
        <el-form-item label="评语" prop="comment" v-if="checkPermi(['manage:trainingSelectedInfo:score'])">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入内容"/>
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
import {
  addTrainingSelectedInfo,
  delTrainingSelectedInfo,
  getTrainingSelectedInfo,
  listTrainingSelectedInfo,
  staticsTrainingSelectedInfo,
  updateTrainingSelectedInfo
} from '@/api/manage/trainingSelectedInfo'
import { checkPermi } from '@/utils/permission'
import { listClassInfo } from '@/api/manage/classInfo'

export default {
  name: 'TrainingSelectedInfo',
  dicts: ['training_selected_status', 'training_selected_submit_status'],
  data() {
    return {
      statics: {
        avgScore: 0,
        passRate: 0,
        submitRate: 0
      },
      openScore: false,
      isClassQuery: false,
      //班级相关信息
      classInfoList: [],
      classLoading: false,
      classQueryParams: {
        classTitle: '',
        pageNum: 1,
        pageSize: 100
      },
      baseUrl: process.env.VUE_APP_BASE_API,
      //表格展示列
      columns: [
        { key: 0, label: '选择编号', visible: false },
        { key: 1, label: '实训', visible: true },
        { key: 2, label: '报告内容', visible: true },
        { key: 3, label: '报告文件', visible: true },
        { key: 4, label: '提交状态', visible: true },
        { key: 5, label: '提交时间', visible: false },
        { key: 6, label: '评分', visible: true },
        { key: 7, label: '评语', visible: false },
        { key: 8, label: '状态', visible: true },
        { key: 9, label: '老师', visible: true },
        { key: 10, label: '学生', visible: true },
        { key: 11, label: '班级', visible: true },
        { key: 12, label: '院校', visible: true },
        { key: 13, label: '创建时间', visible: false },
        { key: 14, label: '更新人', visible: false },
        { key: 15, label: '更新时间', visible: false },
        { key: 16, label: '备注', visible: false }
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
      // 实训选择表格数据
      trainingSelectedInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeSubmitTime: [],
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        selectedId: null,
        trainingId: null,
        content: null,
        contentFile: null,
        submitStatus: null,
        submitTime: null,
        score: null,
        status: null,
        teacherId: null,
        userId: null,
        deptId: null,
        createTime: null,
        updatedBy: null,
        updateTime: null,
        classId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        trainingId: [
          { required: true, message: '实训不能为空', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '报告内容不能为空', trigger: 'blur' }
        ],
        contentFile: [
          { required: false, message: '报告文件不能为空', trigger: 'blur' }
        ],
        submitStatus: [
          { required: true, message: '提交状态不能为空', trigger: 'change' }
        ],
        status: [
          { required: true, message: '状态不能为空', trigger: 'change' }
        ],
        teacherId: [
          { required: true, message: '老师不能为空', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '学生不能为空', trigger: 'blur' }
        ],
        deptId: [
          { required: true, message: '班级不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
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
    /** 查询实训选择列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeSubmitTime && '' != this.daterangeSubmitTime) {
        this.queryParams.params['beginSubmitTime'] = this.daterangeSubmitTime[0]
        this.queryParams.params['endSubmitTime'] = this.daterangeSubmitTime[1]
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listTrainingSelectedInfo(this.queryParams).then(response => {
        this.trainingSelectedInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
      staticsTrainingSelectedInfo(this.queryParams).then(res => {
        this.statics = res.data
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
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        selectedId: null,
        trainingId: null,
        content: null,
        contentFile: null,
        submitStatus: null,
        submitTime: null,
        score: null,
        comment: null,
        status: null,
        teacherId: null,
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
      this.daterangeSubmitTime = []
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.selectedId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加实训选择'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const selectedId = row.selectedId || this.ids
      getTrainingSelectedInfo(selectedId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '实训'
      })
    },
    /** 评分按钮操作 */
    handleScore(row) {
      this.reset()
      const selectedId = row.selectedId || this.ids
      getTrainingSelectedInfo(selectedId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '实训'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.selectedId != null) {
            updateTrainingSelectedInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addTrainingSelectedInfo(this.form).then(response => {
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
      const selectedIds = row.selectedId || this.ids
      this.$modal.confirm('是否确认删除实训选择编号为"' + selectedIds + '"的数据项？').then(function() {
        return delTrainingSelectedInfo(selectedIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/trainingSelectedInfo/export', {
        ...this.queryParams
      }, `trainingSelectedInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
