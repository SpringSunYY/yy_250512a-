package com.lz.manage.model.vo.trainingSelectedInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @Project: training
 * @Author: YY
 * @CreateTime: 2025-05-16  21:07
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class TrainingSelectStatics {
    private BigDecimal avgScore;
    //通过率
    private BigDecimal passRate;
    //提交率
    private BigDecimal submitRate;
}
