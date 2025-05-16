package com.lz.manage.model.dto.classInfo;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.ClassInfo;
/**
 * 班级信息Query对象 tb_class_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class ClassInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 班级编号 */
    private Long classId;

    /** 班级标题 */
    private String className;

    /** 创建人 */
    private Long userId;

    /** 学院 */
    private Long deptId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param classInfoQuery 查询对象
     * @return ClassInfo
     */
    public static ClassInfo queryToObj(ClassInfoQuery classInfoQuery) {
        if (classInfoQuery == null) {
            return null;
        }
        ClassInfo classInfo = new ClassInfo();
        BeanUtils.copyProperties(classInfoQuery, classInfo);
        return classInfo;
    }
}
