package com.lz.manage.model.dto.trainingSelectedInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.TrainingSelectedInfo;
/**
 * 实训选择Vo对象 tb_training_selected_info
 *
 * @author YY
 * @date 2025-05-12
 */
@Data
public class TrainingSelectedInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 选择编号 */
    private Long selectedId;

    /** 实训 */
    private Long trainingId;

    /** 报告内容 */
    private String content;

    /** 报告文件 */
    private String contentFile;

    /** 提交状态 */
    private String submitStatus;

    /** 评分 */
    private BigDecimal score;

    /** 评语 */
    private String comment;

    /** 状态 */
    private String status;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param trainingSelectedInfoEdit 编辑对象
     * @return TrainingSelectedInfo
     */
    public static TrainingSelectedInfo editToObj(TrainingSelectedInfoEdit trainingSelectedInfoEdit) {
        if (trainingSelectedInfoEdit == null) {
            return null;
        }
        TrainingSelectedInfo trainingSelectedInfo = new TrainingSelectedInfo();
        BeanUtils.copyProperties(trainingSelectedInfoEdit, trainingSelectedInfo);
        return trainingSelectedInfo;
    }
}
