package com.lz.manage.model.dto.trainingInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.TrainingInfo;
/**
 * 实训信息Vo对象 tb_training_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class TrainingInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 实训编号 */
    private Long trainingId;

    /** 实训标题 */
    private String trainingTitle;

    /** 实训描述 */
    private String trainingDesc;

    /** 实训文件 */
    private String trainingFile;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 实训地点 */
    private String location;

    /** 创建人 */
    private Long userId;

    /** 班级 */
    private Long classId;

    /** 更新人 */
    private String updatedBy;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param trainingInfoInsert 插入对象
     * @return TrainingInfoInsert
     */
    public static TrainingInfo insertToObj(TrainingInfoInsert trainingInfoInsert) {
        if (trainingInfoInsert == null) {
            return null;
        }
        TrainingInfo trainingInfo = new TrainingInfo();
        BeanUtils.copyProperties(trainingInfoInsert, trainingInfo);
        return trainingInfo;
    }
}
