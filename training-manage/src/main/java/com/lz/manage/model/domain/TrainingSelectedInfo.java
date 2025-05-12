package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.math.BigDecimal;
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
 * 实训选择对象 tb_training_selected_info
 *
 * @author YY
 * @date 2025-05-12
 */
@TableName("tb_training_selected_info")
@Data
public class TrainingSelectedInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 选择编号 */
    @Excel(name = "选择编号")
    @TableId(value = "selected_id", type = IdType.ASSIGN_ID)
    private Long selectedId;

    /** 实训 */
    @Excel(name = "实训")
    private Long trainingId;

    /** 报告内容 */
    @Excel(name = "报告内容")
    private String content;

    /** 报告文件 */
    @Excel(name = "报告文件")
    private String contentFile;

    /** 提交状态 */
    @Excel(name = "提交状态")
    private String submitStatus;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 评分 */
    @Excel(name = "评分")
    private BigDecimal score;

    /** 评语 */
    @Excel(name = "评语")
    private String comment;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 老师 */
    @Excel(name = "老师")
    private Long teacherId;

    /** 学生 */
    @Excel(name = "学生")
    private Long userId;

    /** 班级 */
    @Excel(name = "班级")
    private Long deptId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
