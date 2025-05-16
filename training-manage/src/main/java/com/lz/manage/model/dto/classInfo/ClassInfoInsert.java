package com.lz.manage.model.dto.classInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.ClassInfo;
/**
 * 班级信息Vo对象 tb_class_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class ClassInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 班级编号 */
    private Long classId;

    /** 班级标题 */
    private String className;

    /** 班级描述 */
    private String classDesc;

    /** 创建人 */
    private Long userId;

    /** 学院 */
    private Long deptId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param classInfoInsert 插入对象
     * @return ClassInfoInsert
     */
    public static ClassInfo insertToObj(ClassInfoInsert classInfoInsert) {
        if (classInfoInsert == null) {
            return null;
        }
        ClassInfo classInfo = new ClassInfo();
        BeanUtils.copyProperties(classInfoInsert, classInfo);
        return classInfo;
    }
}
