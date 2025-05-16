package com.lz.manage.model.dto.dataInfo;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.DataInfo;
/**
 * 资料信息Query对象 tb_data_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class DataInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 资料编号 */
    private Long dataId;

    /** 资料标题 */
    private String dataTitle;

    /** 创建人 */
    private Long userId;

    /** 院系 */
    private Long deptId;

    /** 班级 */
    private Long classId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param dataInfoQuery 查询对象
     * @return DataInfo
     */
    public static DataInfo queryToObj(DataInfoQuery dataInfoQuery) {
        if (dataInfoQuery == null) {
            return null;
        }
        DataInfo dataInfo = new DataInfo();
        BeanUtils.copyProperties(dataInfoQuery, dataInfo);
        return dataInfo;
    }
}
