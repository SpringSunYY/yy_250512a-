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
import com.lz.manage.model.domain.TrainingSelectedInfo;
import com.lz.manage.model.vo.trainingSelectedInfo.TrainingSelectedInfoVo;
import com.lz.manage.model.dto.trainingSelectedInfo.TrainingSelectedInfoQuery;
import com.lz.manage.model.dto.trainingSelectedInfo.TrainingSelectedInfoInsert;
import com.lz.manage.model.dto.trainingSelectedInfo.TrainingSelectedInfoEdit;
import com.lz.manage.service.ITrainingSelectedInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 实训选择Controller
 *
 * @author YY
 * @date 2025-05-12
 */
@RestController
@RequestMapping("/manage/trainingSelectedInfo")
public class TrainingSelectedInfoController extends BaseController
{
    @Resource
    private ITrainingSelectedInfoService trainingSelectedInfoService;

    /**
     * 查询实训选择列表
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingSelectedInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainingSelectedInfoQuery trainingSelectedInfoQuery)
    {
        TrainingSelectedInfo trainingSelectedInfo = TrainingSelectedInfoQuery.queryToObj(trainingSelectedInfoQuery);
        startPage();
        List<TrainingSelectedInfo> list = trainingSelectedInfoService.selectTrainingSelectedInfoList(trainingSelectedInfo);
        List<TrainingSelectedInfoVo> listVo= list.stream().map(TrainingSelectedInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出实训选择列表
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingSelectedInfo:export')")
    @Log(title = "实训选择", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainingSelectedInfoQuery trainingSelectedInfoQuery)
    {
        TrainingSelectedInfo trainingSelectedInfo = TrainingSelectedInfoQuery.queryToObj(trainingSelectedInfoQuery);
        List<TrainingSelectedInfo> list = trainingSelectedInfoService.selectTrainingSelectedInfoList(trainingSelectedInfo);
        ExcelUtil<TrainingSelectedInfo> util = new ExcelUtil<TrainingSelectedInfo>(TrainingSelectedInfo.class);
        util.exportExcel(response, list, "实训选择数据");
    }

    /**
     * 获取实训选择详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingSelectedInfo:query')")
    @GetMapping(value = "/{selectedId}")
    public AjaxResult getInfo(@PathVariable("selectedId") Long selectedId)
    {
        TrainingSelectedInfo trainingSelectedInfo = trainingSelectedInfoService.selectTrainingSelectedInfoBySelectedId(selectedId);
        return success(TrainingSelectedInfoVo.objToVo(trainingSelectedInfo));
    }

    /**
     * 新增实训选择
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingSelectedInfo:add')")
    @Log(title = "实训选择", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainingSelectedInfoInsert trainingSelectedInfoInsert)
    {
        TrainingSelectedInfo trainingSelectedInfo = TrainingSelectedInfoInsert.insertToObj(trainingSelectedInfoInsert);
        return toAjax(trainingSelectedInfoService.insertTrainingSelectedInfo(trainingSelectedInfo));
    }

    /**
     * 修改实训选择
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingSelectedInfo:edit')")
    @Log(title = "实训选择", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainingSelectedInfoEdit trainingSelectedInfoEdit)
    {
        TrainingSelectedInfo trainingSelectedInfo = TrainingSelectedInfoEdit.editToObj(trainingSelectedInfoEdit);
        return toAjax(trainingSelectedInfoService.updateTrainingSelectedInfo(trainingSelectedInfo));
    }

    /**
     * 删除实训选择
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingSelectedInfo:remove')")
    @Log(title = "实训选择", businessType = BusinessType.DELETE)
	@DeleteMapping("/{selectedIds}")
    public AjaxResult remove(@PathVariable Long[] selectedIds)
    {
        return toAjax(trainingSelectedInfoService.deleteTrainingSelectedInfoBySelectedIds(selectedIds));
    }
}
