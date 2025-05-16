package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.DataInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 资料信息Mapper接口
 * 
 * @author YY
 * @date 2025-05-16
 */
public interface DataInfoMapper extends BaseMapper<DataInfo>
{
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
     * 删除资料信息
     * 
     * @param dataId 资料信息主键
     * @return 结果
     */
    public int deleteDataInfoByDataId(Long dataId);

    /**
     * 批量删除资料信息
     * 
     * @param dataIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataInfoByDataIds(Long[] dataIds);
}
