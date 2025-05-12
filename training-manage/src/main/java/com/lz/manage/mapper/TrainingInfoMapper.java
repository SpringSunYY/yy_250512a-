package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.TrainingInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 实训信息Mapper接口
 * 
 * @author YY
 * @date 2025-05-12
 */
public interface TrainingInfoMapper extends BaseMapper<TrainingInfo>
{
    /**
     * 查询实训信息
     * 
     * @param trainingId 实训信息主键
     * @return 实训信息
     */
    public TrainingInfo selectTrainingInfoByTrainingId(Long trainingId);

    /**
     * 查询实训信息列表
     * 
     * @param trainingInfo 实训信息
     * @return 实训信息集合
     */
    public List<TrainingInfo> selectTrainingInfoList(TrainingInfo trainingInfo);

    /**
     * 新增实训信息
     * 
     * @param trainingInfo 实训信息
     * @return 结果
     */
    public int insertTrainingInfo(TrainingInfo trainingInfo);

    /**
     * 修改实训信息
     * 
     * @param trainingInfo 实训信息
     * @return 结果
     */
    public int updateTrainingInfo(TrainingInfo trainingInfo);

    /**
     * 删除实训信息
     * 
     * @param trainingId 实训信息主键
     * @return 结果
     */
    public int deleteTrainingInfoByTrainingId(Long trainingId);

    /**
     * 批量删除实训信息
     * 
     * @param trainingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainingInfoByTrainingIds(Long[] trainingIds);
}
