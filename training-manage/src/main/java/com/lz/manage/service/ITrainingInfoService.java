package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.TrainingInfo;
import com.lz.manage.model.vo.trainingInfo.TrainingInfoVo;
import com.lz.manage.model.dto.trainingInfo.TrainingInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 实训信息Service接口
 * 
 * @author YY
 * @date 2025-05-12
 */
public interface ITrainingInfoService extends IService<TrainingInfo>
{
    //region mybatis代码
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
     * 批量删除实训信息
     * 
     * @param trainingIds 需要删除的实训信息主键集合
     * @return 结果
     */
    public int deleteTrainingInfoByTrainingIds(Long[] trainingIds);

    /**
     * 删除实训信息信息
     * 
     * @param trainingId 实训信息主键
     * @return 结果
     */
    public int deleteTrainingInfoByTrainingId(Long trainingId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param trainingInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<TrainingInfo> getQueryWrapper(TrainingInfoQuery trainingInfoQuery);

    /**
     * 转换vo
     *
     * @param trainingInfoList TrainingInfo集合
     * @return TrainingInfoVO集合
     */
    List<TrainingInfoVo> convertVoList(List<TrainingInfo> trainingInfoList);
}
