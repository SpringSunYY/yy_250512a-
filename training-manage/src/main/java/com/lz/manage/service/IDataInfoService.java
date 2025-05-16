package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.DataInfo;
import com.lz.manage.model.vo.dataInfo.DataInfoVo;
import com.lz.manage.model.dto.dataInfo.DataInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 资料信息Service接口
 * 
 * @author YY
 * @date 2025-05-16
 */
public interface IDataInfoService extends IService<DataInfo>
{
    //region mybatis代码
    /**
     * 查询资料信息
     * 
     * @param dataId 资料信息主键
     * @return 资料信息
     */
    public DataInfo selectDataInfoByDataId(Long dataId);

    /**
     * 查询资料信息列表
     * 
     * @param dataInfo 资料信息
     * @return 资料信息集合
     */
    public List<DataInfo> selectDataInfoList(DataInfo dataInfo);

    /**
     * 新增资料信息
     * 
     * @param dataInfo 资料信息
     * @return 结果
     */
    public int insertDataInfo(DataInfo dataInfo);

    /**
     * 修改资料信息
     * 
     * @param dataInfo 资料信息
     * @return 结果
     */
    public int updateDataInfo(DataInfo dataInfo);

    /**
     * 批量删除资料信息
     * 
     * @param dataIds 需要删除的资料信息主键集合
     * @return 结果
     */
    public int deleteDataInfoByDataIds(Long[] dataIds);

    /**
     * 删除资料信息信息
     * 
     * @param dataId 资料信息主键
     * @return 结果
     */
    public int deleteDataInfoByDataId(Long dataId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param dataInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<DataInfo> getQueryWrapper(DataInfoQuery dataInfoQuery);

    /**
     * 转换vo
     *
     * @param dataInfoList DataInfo集合
     * @return DataInfoVO集合
     */
    List<DataInfoVo> convertVoList(List<DataInfo> dataInfoList);
}
