package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.enums.BusinessType;
import com.lz.manage.model.domain.DataInfo;
import com.lz.manage.model.vo.dataInfo.DataInfoVo;
import com.lz.manage.model.dto.dataInfo.DataInfoQuery;
import com.lz.manage.model.dto.dataInfo.DataInfoInsert;
import com.lz.manage.model.dto.dataInfo.DataInfoEdit;
import com.lz.manage.service.IDataInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 资料信息Controller
 *
 * @author YY
 * @date 2025-05-16
 */
@RestController
@RequestMapping("/manage/dataInfo")
public class DataInfoController extends BaseController
{
    @Resource
    private IDataInfoService dataInfoService;

    /**
     * 查询资料信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:dataInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(DataInfoQuery dataInfoQuery)
    {
        DataInfo dataInfo = DataInfoQuery.queryToObj(dataInfoQuery);
        startPage();
        List<DataInfo> list = dataInfoService.selectDataInfoList(dataInfo);
        List<DataInfoVo> listVo= list.stream().map(DataInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出资料信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:dataInfo:export')")
    @Log(title = "资料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DataInfoQuery dataInfoQuery)
    {
        DataInfo dataInfo = DataInfoQuery.queryToObj(dataInfoQuery);
        List<DataInfo> list = dataInfoService.selectDataInfoList(dataInfo);
        ExcelUtil<DataInfo> util = new ExcelUtil<DataInfo>(DataInfo.class);
        util.exportExcel(response, list, "资料信息数据");
    }

    /**
     * 获取资料信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:dataInfo:query')")
    @GetMapping(value = "/{dataId}")
    public AjaxResult getInfo(@PathVariable("dataId") Long dataId)
    {
        DataInfo dataInfo = dataInfoService.selectDataInfoByDataId(dataId);
        return success(DataInfoVo.objToVo(dataInfo));
    }

    /**
     * 新增资料信息
     */
    @PreAuthorize("@ss.hasPermi('manage:dataInfo:add')")
    @Log(title = "资料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataInfoInsert dataInfoInsert)
    {
        DataInfo dataInfo = DataInfoInsert.insertToObj(dataInfoInsert);
        return toAjax(dataInfoService.insertDataInfo(dataInfo));
    }

    /**
     * 修改资料信息
     */
    @PreAuthorize("@ss.hasPermi('manage:dataInfo:edit')")
    @Log(title = "资料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataInfoEdit dataInfoEdit)
    {
        DataInfo dataInfo = DataInfoEdit.editToObj(dataInfoEdit);
        return toAjax(dataInfoService.updateDataInfo(dataInfo));
    }

    /**
     * 删除资料信息
     */
    @PreAuthorize("@ss.hasPermi('manage:dataInfo:remove')")
    @Log(title = "资料信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dataIds}")
    public AjaxResult remove(@PathVariable Long[] dataIds)
    {
        return toAjax(dataInfoService.deleteDataInfoByDataIds(dataIds));
    }
}
