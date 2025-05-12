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
import com.lz.manage.model.domain.TrainingInfo;
import com.lz.manage.model.vo.trainingInfo.TrainingInfoVo;
import com.lz.manage.model.dto.trainingInfo.TrainingInfoQuery;
import com.lz.manage.model.dto.trainingInfo.TrainingInfoInsert;
import com.lz.manage.model.dto.trainingInfo.TrainingInfoEdit;
import com.lz.manage.service.ITrainingInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 实训信息Controller
 *
 * @author YY
 * @date 2025-05-12
 */
@RestController
@RequestMapping("/manage/trainingInfo")
public class TrainingInfoController extends BaseController
{
    @Resource
    private ITrainingInfoService trainingInfoService;

    /**
     * 查询实训信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainingInfoQuery trainingInfoQuery)
    {
        TrainingInfo trainingInfo = TrainingInfoQuery.queryToObj(trainingInfoQuery);
        startPage();
        List<TrainingInfo> list = trainingInfoService.selectTrainingInfoList(trainingInfo);
        List<TrainingInfoVo> listVo= list.stream().map(TrainingInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出实训信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingInfo:export')")
    @Log(title = "实训信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainingInfoQuery trainingInfoQuery)
    {
        TrainingInfo trainingInfo = TrainingInfoQuery.queryToObj(trainingInfoQuery);
        List<TrainingInfo> list = trainingInfoService.selectTrainingInfoList(trainingInfo);
        ExcelUtil<TrainingInfo> util = new ExcelUtil<TrainingInfo>(TrainingInfo.class);
        util.exportExcel(response, list, "实训信息数据");
    }

    /**
     * 获取实训信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingInfo:query')")
    @GetMapping(value = "/{trainingId}")
    public AjaxResult getInfo(@PathVariable("trainingId") Long trainingId)
    {
        TrainingInfo trainingInfo = trainingInfoService.selectTrainingInfoByTrainingId(trainingId);
        return success(TrainingInfoVo.objToVo(trainingInfo));
    }

    /**
     * 新增实训信息
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingInfo:add')")
    @Log(title = "实训信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainingInfoInsert trainingInfoInsert)
    {
        TrainingInfo trainingInfo = TrainingInfoInsert.insertToObj(trainingInfoInsert);
        return toAjax(trainingInfoService.insertTrainingInfo(trainingInfo));
    }

    /**
     * 修改实训信息
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingInfo:edit')")
    @Log(title = "实训信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainingInfoEdit trainingInfoEdit)
    {
        TrainingInfo trainingInfo = TrainingInfoEdit.editToObj(trainingInfoEdit);
        return toAjax(trainingInfoService.updateTrainingInfo(trainingInfo));
    }

    /**
     * 删除实训信息
     */
    @PreAuthorize("@ss.hasPermi('manage:trainingInfo:remove')")
    @Log(title = "实训信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trainingIds}")
    public AjaxResult remove(@PathVariable Long[] trainingIds)
    {
        return toAjax(trainingInfoService.deleteTrainingInfoByTrainingIds(trainingIds));
    }
}
