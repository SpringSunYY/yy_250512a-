package com.lz.manage.model.vo.archiveInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.ArchiveInfo;
/**
 * 学生档案Vo对象 tb_archive_info
 *
 * @author YY
 * @date 2025-05-12
 */
@Data
public class ArchiveInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 档案编号 */
    @Excel(name = "档案编号")
    private Long archiveId;

    /** 学生名字 */
    @Excel(name = "学生名字")
    private String username;

    /** 学号 */
    @Excel(name = "学号")
    private String stuNo;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 入校日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入校日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinTime;

    /** 高中毕业学校 */
    @Excel(name = "高中毕业学校")
    private String graduatingSchool;

    /** 高中毕业日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "高中毕业日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date graduatingTime;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 最高学历 */
    @Excel(name = "最高学历")
    private String education;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 院系 */
    @Excel(name = "院系")
    private String department;

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


     /**
     * 对象转封装类
     *
     * @param archiveInfo ArchiveInfo实体对象
     * @return ArchiveInfoVo
     */
    public static ArchiveInfoVo objToVo(ArchiveInfo archiveInfo) {
        if (archiveInfo == null) {
            return null;
        }
        ArchiveInfoVo archiveInfoVo = new ArchiveInfoVo();
        BeanUtils.copyProperties(archiveInfo, archiveInfoVo);
        return archiveInfoVo;
    }
}
