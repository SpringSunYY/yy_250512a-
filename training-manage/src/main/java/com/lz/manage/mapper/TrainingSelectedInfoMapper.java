package com.lz.manage.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.lz.manage.model.domain.TrainingSelectedInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 实训选择Mapper接口
 *
 * @author YY
 * @date 2025-05-12
 */
public interface TrainingSelectedInfoMapper extends BaseMapper<TrainingSelectedInfo>
{
    /**
     * 查询实训选择
     *
     * @param selectedId 实训选择主键
     * @return 实训选择
     */
    public TrainingSelectedInfo selectTrainingSelectedInfoBySelectedId(Long selectedId);

    /**
     * 查询实训选择列表
     *
     * @param trainingSelectedInfo 实训选择
     * @return 实训选择集合
     */
    public List<TrainingSelectedInfo> selectTrainingSelectedInfoList(TrainingSelectedInfo trainingSelectedInfo);

    /**
     * 新增实训选择
     *
     * @param trainingSelectedInfo 实训选择
     * @return 结果
     */
    public int insertTrainingSelectedInfo(TrainingSelectedInfo trainingSelectedInfo);

    /**
     * 修改实训选择
     *
     * @param trainingSelectedInfo 实训选择
     * @return 结果
     */
    public int updateTrainingSelectedInfo(TrainingSelectedInfo trainingSelectedInfo);

    /**
     * 删除实训选择
     *
     * @param selectedId 实训选择主键
     * @return 结果
     */
    public int deleteTrainingSelectedInfoBySelectedId(Long selectedId);

    /**
     * 批量删除实训选择
     *
     * @param selectedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainingSelectedInfoBySelectedIds(Long[] selectedIds);

    BigDecimal staticsAvgScore(TrainingSelectedInfo trainingSelectedInfo);

    BigDecimal staticsPassRate(TrainingSelectedInfo trainingSelectedInfo);

    BigDecimal staticsSubmitRate(TrainingSelectedInfo trainingSelectedInfo);
}
