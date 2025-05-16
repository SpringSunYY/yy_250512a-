package com.lz.manage.model.vo.dataInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.DataInfo;

/**
 * 资料信息Vo对象 tb_data_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class DataInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资料编号
     */
    @Excel(name = "资料编号")
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
    private String userName;
    private Long userId;

    /**
     * 院系
     */
    @Excel(name = "院系")
    private String deptName;
    private Long deptId;

    /**
     * 班级
     */
    @Excel(name = "班级")
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
     * 对象转封装类
     *
     * @param dataInfo DataInfo实体对象
     * @return DataInfoVo
     */
    public static DataInfoVo objToVo(DataInfo dataInfo) {
        if (dataInfo == null) {
            return null;
        }
        DataInfoVo dataInfoVo = new DataInfoVo();
        BeanUtils.copyProperties(dataInfo, dataInfoVo);
        return dataInfoVo;
    }
}
