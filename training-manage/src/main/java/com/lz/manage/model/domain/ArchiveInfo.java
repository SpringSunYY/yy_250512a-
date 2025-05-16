package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 学生档案对象 tb_archive_info
 *
 * @author YY
 * @date 2025-05-12
 */
@TableName("tb_archive_info")
@Data
public class ArchiveInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 档案编号
     */
    @Excel(name = "档案编号")
    @TableId(value = "archive_id", type = IdType.ASSIGN_ID)
    private Long archiveId;

    /**
     * 学生名字
     */
    @Excel(name = "学生名字")
    private String username;

    /**
     * 学号
     */
    @Excel(name = "学号")
    private String stuNo;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 入校日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入校日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinTime;

    /**
     * 高中毕业学校
     */
    @Excel(name = "高中毕业学校")
    private String graduatingSchool;

    /**
     * 高中毕业日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "高中毕业日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date graduatingTime;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idCard;

    /**
     * 最高学历
     */
    @Excel(name = "最高学历")
    private String education;

    /**
     * 民族
     */
    @Excel(name = "民族")
    private String nation;

    /**
     * 籍贯
     */
    @Excel(name = "籍贯")
    private String nativePlace;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String phone;

    /**
     * 院系
     */
    @Excel(name = "院系")
    private String department;

    /**
     * 学生
     */
    @Excel(name = "学生",  type = Excel.Type.EXPORT)
    @TableField(exist = false)
    private String userInfoName;
    @Excel(name = "学生编号",  type = Excel.Type.IMPORT)
    private Long userId;

    /**
     * 班级
     */
    @Excel(name = "班级编号", type = Excel.Type.IMPORT)
    private Long classId;
    @TableField(exist = false)
    private String className;

    /**
     * 班级
     */
    @Excel(name = "院校")
    @TableField(exist = false)
    private String deptName;
    private Long deptId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
