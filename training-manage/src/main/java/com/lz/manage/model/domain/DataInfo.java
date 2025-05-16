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
 * 资料信息对象 tb_data_info
 *
 * @author YY
 * @date 2025-05-16
 */
@TableName("tb_data_info")
@Data
public class DataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资料编号
     */
    @Excel(name = "资料编号")
    @TableId(value = "data_id", type = IdType.ASSIGN_ID)
    private Long dataId;

    /**
     * 资料标题
     */
    @Excel(name = "资料标题")
    private String dataTitle;

    /**
     * 资料描述
     */
    @Excel(name = "资料描述")
    private String dataDesc;

    /**
     * 资料文件
     */
    private String dataFile;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    @TableField(exist = false)
    private String userName;
    private Long userId;

    /**
     * 院系
     */
    @Excel(name = "院系")
    @TableField(exist = false)
    private String deptName;
    private Long deptId;

    /**
     * 班级
     */
    @Excel(name = "班级")
    @TableField(exist = false)
    private String className;
    private Long classId;

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
