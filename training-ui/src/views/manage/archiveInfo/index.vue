<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="档案编号" prop="archiveId">
        <el-input
          v-model="queryParams.archiveId"
          placeholder="请输入档案编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生名字" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入学生名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="stuNo">
        <el-input
          v-model="queryParams.stuNo"
          placeholder="请输入学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker
          v-model="daterangeBirthday"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入校日期">
        <el-date-picker
          v-model="daterangeJoinTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <!--      <el-form-item label="高中毕业学校" prop="graduatingSchool">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.graduatingSchool"-->
      <!--          placeholder="请输入高中毕业学校"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="高中毕业日期">-->
      <!--        <el-date-picker-->
      <!--          v-model="daterangeGraduatingTime"-->
      <!--          style="width: 240px"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          type="daterange"-->
      <!--          range-separator="-"-->
      <!--          start-placeholder="开始日期"-->
      <!--          end-placeholder="结束日期"-->
      <!--        ></el-date-picker>-->
      <!--      </el-form-item>-->
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最高学历" prop="education">
        <el-input
          v-model="queryParams.education"
          placeholder="请输入最高学历"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="民族" prop="nation">
        <el-input
          v-model="queryParams.nation"
          placeholder="请输入民族"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="籍贯" prop="nativePlace">
        <el-input
          v-model="queryParams.nativePlace"
          placeholder="请输入籍贯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="院系" prop="department">
        <el-input
          v-model="queryParams.department"
          placeholder="请输入院系"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['manage:archiveInfo:add']"
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
          v-hasPermi="['manage:archiveInfo:edit']"
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
          v-hasPermi="['manage:archiveInfo:remove']"
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
          v-hasPermi="['manage:archiveInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" @doTable="doList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="archiveInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="档案编号" align="center" v-if="columns[0].visible" prop="archiveId"/>
      <el-table-column label="学生名字" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="username"
      />
      <el-table-column label="学号" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="stuNo"
      />
      <el-table-column label="性别" align="center" v-if="columns[3].visible" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="生日" align="center" v-if="columns[4].visible" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="email"
      />
      <el-table-column label="入校日期" align="center" v-if="columns[6].visible" prop="joinTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.joinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="高中毕业学校" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="graduatingSchool"
      />
      <el-table-column label="高中毕业日期" align="center" v-if="columns[8].visible" prop="graduatingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.graduatingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="idCard"
      />
      <el-table-column label="最高学历" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="education"
      />
      <el-table-column label="民族" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="nation"
      />
      <el-table-column label="籍贯" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="nativePlace"
      />
      <el-table-column label="联系电话" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible"
                       prop="phone"
      />
      <el-table-column label="院系" :show-overflow-tooltip="true" align="center" v-if="columns[14].visible"
                       prop="department"
      />
      <el-table-column label="学生" :show-overflow-tooltip="true" align="center" v-if="columns[15].visible"
                       prop="userInfoName"
      >
        <template slot-scope="scope">
          {{scope.row.userInfoName}}
        </template>
      </el-table-column>
      <el-table-column label="班级" :show-overflow-tooltip="true" align="center" v-if="columns[16].visible"
                       prop="deptName"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[17].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[18].visible"
                       prop="updatedBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[19].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[20].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:archiveInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:archiveInfo:remove']"
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

    <!-- 添加或修改学生档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生名字" prop="username">
          <el-input v-model="form.username" placeholder="请输入学生名字"/>
        </el-form-item>
        <el-form-item label="学号" prop="stuNo">
          <el-input v-model="form.stuNo" placeholder="请输入学号"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker clearable
                          v-model="form.birthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择生日"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"/>
        </el-form-item>
        <el-form-item label="入校日期" prop="joinTime">
          <el-date-picker clearable
                          v-model="form.joinTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入校日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="毕业学校" prop="graduatingSchool">
          <el-input v-model="form.graduatingSchool" placeholder="请输入高中毕业学校"/>
        </el-form-item>
        <el-form-item label="毕业日期" prop="graduatingTime">
          <el-date-picker clearable
                          v-model="form.graduatingTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择高中毕业日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证号"/>
        </el-form-item>
        <el-form-item label="最高学历" prop="education">
          <el-input v-model="form.education" placeholder="请输入最高学历"/>
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input v-model="form.nation" placeholder="请输入民族"/>
        </el-form-item>
        <el-form-item label="籍贯" prop="nativePlace">
          <el-input v-model="form.nativePlace" placeholder="请输入籍贯"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话"/>
        </el-form-item>
        <el-form-item label="院系" prop="department">
          <el-input v-model="form.department" placeholder="请输入院系"/>
        </el-form-item>
        <el-form-item label="学生" prop="userId" v-if="isUserQuery">
          <el-select
            v-model="form.userId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入用户账号"
            :remote-method="selectUserInfoList"
            :loading="userLoading"
          >
            <el-option
              v-for="item in userInfoList"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!--        <el-form-item label="班级" prop="deptId">-->
        <!--          <el-input v-model="form.deptId" placeholder="请输入班级"/>-->
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
  </div>
</template>

<script>
import {
  listArchiveInfo,
  getArchiveInfo,
  delArchiveInfo,
  addArchiveInfo,
  updateArchiveInfo
} from '@/api/manage/archiveInfo'
import { allocatedUserList } from '@/api/system/role'
import { checkPermi } from '@/utils/permission'

export default {
  name: 'ArchiveInfo',
  dicts: ['sys_user_sex'],
  data() {
    return {
      isUserQuery: false,
      //用户相关信息
      userInfoList: [],
      userLoading: false,
      userQueryParams: {
        userName: '',
        roleId: 100,
        pageNum: 1,
        pageSize: 100
      },
      //表格展示列
      columns: [
        { key: 0, label: '档案编号', visible: false },
        { key: 1, label: '学生名字', visible: true },
        { key: 2, label: '学号', visible: true },
        { key: 3, label: '性别', visible: true },
        { key: 4, label: '生日', visible: false },
        { key: 5, label: '邮箱', visible: true },
        { key: 6, label: '入校日期', visible: true },
        { key: 7, label: '高中毕业学校', visible: true },
        { key: 8, label: '高中毕业日期', visible: true },
        { key: 9, label: '身份证号', visible: true },
        { key: 10, label: '最高学历', visible: false },
        { key: 11, label: '民族', visible: false },
        { key: 12, label: '籍贯', visible: false },
        { key: 13, label: '联系电话', visible: true },
        { key: 14, label: '院系', visible: true },
        { key: 15, label: '学生', visible: false },
        { key: 16, label: '班级', visible: false },
        { key: 17, label: '创建时间', visible: false },
        { key: 18, label: '更新人', visible: false },
        { key: 19, label: '更新时间', visible: false },
        { key: 20, label: '备注', visible: false }
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
      // 学生档案表格数据
      archiveInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeBirthday: [],
      // 备注时间范围
      daterangeJoinTime: [],
      // 备注时间范围
      daterangeGraduatingTime: [],
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        archiveId: null,
        username: null,
        stuNo: null,
        sex: null,
        birthday: null,
        email: null,
        joinTime: null,
        graduatingSchool: null,
        graduatingTime: null,
        idCard: null,
        education: null,
        nation: null,
        nativePlace: null,
        phone: null,
        department: null,
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
        username: [
          { required: true, message: '学生名字不能为空', trigger: 'blur' }
        ],
        stuNo: [
          { required: true, message: '学号不能为空', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '性别不能为空', trigger: 'change' }
        ],
        joinTime: [
          { required: true, message: '入校日期不能为空', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '身份证号不能为空', trigger: 'blur' },
          //  身份证号码
          {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '请输入正确的身份证号码',
            trigger: 'blur'
          }
        ],
        phone: [
          { required: true, message: '联系电话不能为空', trigger: 'blur' },
          // 手机号码
          {
            pattern: /^1[3|4|5|7|8][0-9]\d{8}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ],
        department: [
          { required: true, message: '院系不能为空', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '学生不能为空', trigger: 'blur' }
        ],
        deptId: [
          { required: true, message: '班级不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        email: [
          { required: false, message: '邮箱不能为空', trigger: 'blur' },
          // 邮箱
          {
            pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: '请输入正确的邮箱地址',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.getList()
    if (checkPermi(['manage:archiveInfo:add'])) {
      this.isUserQuery = true
      this.getUserInfoList()
    }
  },
  methods: {
    checkPermi,
    doList() {
      const that = this
      let list = this.archiveInfoList
      this.archiveInfoList = []
      //重新渲染数据
      setTimeout(function() {
        that.archiveInfoList = list
      }, 1)
    },
    /**
     * 获取学生用户列表推荐
     * @param query
     */
    selectUserInfoList(query) {
      if (query !== '') {
        this.userLoading = true
        this.userQueryParams.userName = query
        setTimeout(() => {
          this.getUserInfoList()
        }, 200)
      } else {
        this.userInfoList = []
        this.userQueryParams.userName = null
      }
    },
    /**
     * 获取学生用户信息列表
     */
    getUserInfoList() {
      //添加查询参数
      if (this.form.userId != null) {
        this.userQueryParams.userId = this.form.userId
      } else {
        this.userQueryParams.userId = null
      }
      if (this.userQueryParams.userName !== '') {
        this.userQueryParams.userId = null
      }
      allocatedUserList(this.userQueryParams).then(res => {
        this.userInfoList = res?.rows
        this.userLoading = false
      })
    },
    /** 查询学生档案列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeBirthday && '' != this.daterangeBirthday) {
        this.queryParams.params['beginBirthday'] = this.daterangeBirthday[0]
        this.queryParams.params['endBirthday'] = this.daterangeBirthday[1]
      }
      if (null != this.daterangeJoinTime && '' != this.daterangeJoinTime) {
        this.queryParams.params['beginJoinTime'] = this.daterangeJoinTime[0]
        this.queryParams.params['endJoinTime'] = this.daterangeJoinTime[1]
      }
      if (null != this.daterangeGraduatingTime && '' != this.daterangeGraduatingTime) {
        this.queryParams.params['beginGraduatingTime'] = this.daterangeGraduatingTime[0]
        this.queryParams.params['endGraduatingTime'] = this.daterangeGraduatingTime[1]
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listArchiveInfo(this.queryParams).then(response => {
        this.archiveInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        archiveId: null,
        username: null,
        stuNo: null,
        sex: null,
        birthday: null,
        email: null,
        joinTime: null,
        graduatingSchool: null,
        graduatingTime: null,
        idCard: null,
        education: null,
        nation: null,
        nativePlace: null,
        phone: null,
        department: null,
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
      this.daterangeBirthday = []
      this.daterangeJoinTime = []
      this.daterangeGraduatingTime = []
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.archiveId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加学生档案'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const archiveId = row.archiveId || this.ids
      getArchiveInfo(archiveId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改学生档案'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.archiveId != null) {
            updateArchiveInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addArchiveInfo(this.form).then(response => {
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
      const archiveIds = row.archiveId || this.ids
      this.$modal.confirm('是否确认删除学生档案编号为"' + archiveIds + '"的数据项？').then(function() {
        return delArchiveInfo(archiveIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/archiveInfo/export', {
        ...this.queryParams
      }, `archiveInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
