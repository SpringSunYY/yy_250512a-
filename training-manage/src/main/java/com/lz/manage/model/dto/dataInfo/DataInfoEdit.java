package com.lz.manage.model.dto.dataInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.DataInfo;
/**
 * 资料信息Vo对象 tb_data_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class DataInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 资料编号 */
    private Long dataId;

    /** 资料标题 */
    private String dataTitle;

    /** 资料描述 */
    private String dataDesc;

    /** 资料文件 */
    private String dataFile;

    /** 创建人 */
    private Long userId;

    /** 院系 */
    private Long deptId;

    /** 班级 */
    private Long classId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param dataInfoEdit 编辑对象
     * @return DataInfo
     */
    public static DataInfo editToObj(DataInfoEdit dataInfoEdit) {
        if (dataInfoEdit == null) {
            return null;
        }
        DataInfo dataInfo = new DataInfo();
        BeanUtils.copyProperties(dataInfoEdit, dataInfo);
        return dataInfo;
    }
}
