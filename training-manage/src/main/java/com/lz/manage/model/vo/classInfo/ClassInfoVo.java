package com.lz.manage.model.vo.classInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.ClassInfo;

/**
 * 班级信息Vo对象 tb_class_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class ClassInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 班级编号
     */
    @Excel(name = "班级编号")
    private Long classId;

    /**
     * 班级标题
     */
    @Excel(name = "班级标题")
    private String className;

    /**
     * 班级描述
     */
    @Excel(name = "班级描述")
    private String classDesc;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String userName;
    private Long userId;

    /**
     * 学院
     */
    @Excel(name = "学院")
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
     * 对象转封装类
     *
     * @param classInfo ClassInfo实体对象
     * @return ClassInfoVo
     */
    public static ClassInfoVo objToVo(ClassInfo classInfo) {
        if (classInfo == null) {
            return null;
        }
        ClassInfoVo classInfoVo = new ClassInfoVo();
        BeanUtils.copyProperties(classInfo, classInfoVo);
        return classInfoVo;
    }
}
