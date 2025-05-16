package com.lz.manage.model.dto.trainingSelectedInfo;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.TrainingSelectedInfo;

/**
 * 实训选择Query对象 tb_training_selected_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class TrainingSelectedInfoQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 选择编号
     */
    private Long selectedId;

    /**
     * 实训
     */
    private Long trainingId;

    /**
     * 报告内容
     */
    private String content;

    /**
     * 提交状态
     */
    private String submitStatus;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 老师
     */
    private Long teacherId;

    /**
     * 学生
     */
    private Long userId;

    /**
     * 班级
     */
    private Long classId;

    /**
     * 院校
     */
    private Long deptId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param trainingSelectedInfoQuery 查询对象
     * @return TrainingSelectedInfo
     */
    public static TrainingSelectedInfo queryToObj(TrainingSelectedInfoQuery trainingSelectedInfoQuery) {
        if (trainingSelectedInfoQuery == null) {
            return null;
        }
        TrainingSelectedInfo trainingSelectedInfo = new TrainingSelectedInfo();
        BeanUtils.copyProperties(trainingSelectedInfoQuery, trainingSelectedInfo);
        return trainingSelectedInfo;
    }
}
