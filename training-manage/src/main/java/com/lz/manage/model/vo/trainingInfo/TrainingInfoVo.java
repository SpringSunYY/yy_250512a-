package com.lz.manage.model.vo.trainingInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.TrainingInfo;
/**
 * 实训信息Vo对象 tb_training_info
 *
 * @author YY
 * @date 2025-05-12
 */
@Data
public class TrainingInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 实训编号 */
    @Excel(name = "实训编号")
    private Long trainingId;

    /** 实训标题 */
    @Excel(name = "实训标题")
    private String trainingTitle;

    /** 实训描述 */
    @Excel(name = "实训描述")
    private String trainingDesc;

    /** 实训文件 */
    @Excel(name = "实训文件")
    private String trainingFile;

    /** 创建人 */
    @Excel(name = "创建人")
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
     * @param trainingInfo TrainingInfo实体对象
     * @return TrainingInfoVo
     */
    public static TrainingInfoVo objToVo(TrainingInfo trainingInfo) {
        if (trainingInfo == null) {
            return null;
        }
        TrainingInfoVo trainingInfoVo = new TrainingInfoVo();
        BeanUtils.copyProperties(trainingInfo, trainingInfoVo);
        return trainingInfoVo;
    }
}
