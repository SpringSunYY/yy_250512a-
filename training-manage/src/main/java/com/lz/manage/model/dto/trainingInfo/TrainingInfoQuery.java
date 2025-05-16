package com.lz.manage.model.dto.trainingInfo;

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
import com.lz.manage.model.domain.TrainingInfo;

/**
 * 实训信息Query对象 tb_training_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class TrainingInfoQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 实训编号
     */
    private Long trainingId;

    /**
     * 实训标题
     */
    private String trainingTitle;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 实训地点
     */
    private String location;

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 院系
     */
    private Long deptId;

    /**
     * 班级
     */
    private Long classId;

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
     * @param trainingInfoQuery 查询对象
     * @return TrainingInfo
     */
    public static TrainingInfo queryToObj(TrainingInfoQuery trainingInfoQuery) {
        if (trainingInfoQuery == null) {
            return null;
        }
        TrainingInfo trainingInfo = new TrainingInfo();
        BeanUtils.copyProperties(trainingInfoQuery, trainingInfo);
        return trainingInfo;
    }
}
