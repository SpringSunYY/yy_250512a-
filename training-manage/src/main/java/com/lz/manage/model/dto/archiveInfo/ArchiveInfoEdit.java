package com.lz.manage.model.dto.archiveInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.annotation.Excel;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.ArchiveInfo;
/**
 * 学生档案Vo对象 tb_archive_info
 *
 * @author YY
 * @date 2025-05-12
 */
@Data
public class ArchiveInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 档案编号 */
    private Long archiveId;

    /** 学生名字 */
    private String username;

    /** 学号 */
    private String stuNo;

    /** 性别 */
    private String sex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /** 邮箱 */
    private String email;

    /** 入校日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;

    /** 高中毕业学校 */
    private String graduatingSchool;

    /** 高中毕业日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date graduatingTime;

    /** 身份证号 */
    private String idCard;

    /** 最高学历 */
    private String education;

    /** 民族 */
    private String nation;

    /** 籍贯 */
    private String nativePlace;

    /** 联系电话 */
    private String phone;

    /** 院系 */
    private String department;

    /** 学生 */
    private Long userId;

    private Long classId;

    /** 班级 */
    private Long deptId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param archiveInfoEdit 编辑对象
     * @return ArchiveInfo
     */
    public static ArchiveInfo editToObj(ArchiveInfoEdit archiveInfoEdit) {
        if (archiveInfoEdit == null) {
            return null;
        }
        ArchiveInfo archiveInfo = new ArchiveInfo();
        BeanUtils.copyProperties(archiveInfoEdit, archiveInfo);
        return archiveInfo;
    }
}
