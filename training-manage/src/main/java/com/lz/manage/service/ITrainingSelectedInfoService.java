package com.lz.manage.service;

import java.util.List;

import com.lz.manage.model.domain.TrainingSelectedInfo;
import com.lz.manage.model.vo.trainingSelectedInfo.TrainingSelectStatics;
import com.lz.manage.model.vo.trainingSelectedInfo.TrainingSelectedInfoVo;
import com.lz.manage.model.dto.trainingSelectedInfo.TrainingSelectedInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 实训选择Service接口
 *
 * @author YY
 * @date 2025-05-12
 */
public interface ITrainingSelectedInfoService extends IService<TrainingSelectedInfo> {
    //region mybatis代码

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
     * 批量删除实训选择
     *
     * @param selectedIds 需要删除的实训选择主键集合
     * @return 结果
     */
    public int deleteTrainingSelectedInfoBySelectedIds(Long[] selectedIds);

    /**
     * 删除实训选择信息
     *
     * @param selectedId 实训选择主键
     * @return 结果
     */
    public int deleteTrainingSelectedInfoBySelectedId(Long selectedId);
    //endregion

    /**
     * 获取查询条件
     *
     * @param trainingSelectedInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<TrainingSelectedInfo> getQueryWrapper(TrainingSelectedInfoQuery trainingSelectedInfoQuery);

    /**
     * 转换vo
     *
     * @param trainingSelectedInfoList TrainingSelectedInfo集合
     * @return TrainingSelectedInfoVO集合
     */
    List<TrainingSelectedInfoVo> convertVoList(List<TrainingSelectedInfo> trainingSelectedInfoList);

    /**
     * 统计
     *
     * @param trainingSelectedInfo 实训选择
     * @return TrainingSelectedInfoVO
     */
    TrainingSelectStatics statics(TrainingSelectedInfo trainingSelectedInfo);
}
