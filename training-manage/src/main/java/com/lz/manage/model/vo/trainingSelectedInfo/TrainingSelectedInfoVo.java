package com.lz.manage.model.vo.trainingSelectedInfo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.TrainingSelectedInfo;
/**
 * 实训选择Vo对象 tb_training_selected_info
 *
 * @author YY
 * @date 2025-05-12
 */
@Data
public class TrainingSelectedInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 选择编号 */
    @Excel(name = "选择编号")
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


     /**
     * 对象转封装类
     *
     * @param trainingSelectedInfo TrainingSelectedInfo实体对象
     * @return TrainingSelectedInfoVo
     */
    public static TrainingSelectedInfoVo objToVo(TrainingSelectedInfo trainingSelectedInfo) {
        if (trainingSelectedInfo == null) {
            return null;
        }
        TrainingSelectedInfoVo trainingSelectedInfoVo = new TrainingSelectedInfoVo();
        BeanUtils.copyProperties(trainingSelectedInfo, trainingSelectedInfoVo);
        return trainingSelectedInfoVo;
    }
}
