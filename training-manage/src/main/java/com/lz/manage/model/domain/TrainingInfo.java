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
 * 实训信息对象 tb_training_info
 *
 * @author YY
 * @date 2025-05-12
 */
@TableName("tb_training_info")
@Data
public class TrainingInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 实训编号
     */
    @Excel(name = "实训编号")
    @TableId(value = "training_id", type = IdType.ASSIGN_ID)
    private Long trainingId;

    /**
     * 实训标题
     */
    @Excel(name = "实训标题")
    private String trainingTitle;

    /**
     * 实训描述
     */
    @Excel(name = "实训描述")
    private String trainingDesc;

    /**
     * 实训文件
     */
    @Excel(name = "实训文件")
    private String trainingFile;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    @TableField(exist = false)
    private String userName;
    private Long userId;

    /**
     * 班级
     */
    @Excel(name = "班级")
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
