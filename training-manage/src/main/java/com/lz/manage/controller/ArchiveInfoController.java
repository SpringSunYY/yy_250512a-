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
import com.lz.manage.model.domain.ArchiveInfo;
import com.lz.manage.model.vo.archiveInfo.ArchiveInfoVo;
import com.lz.manage.model.dto.archiveInfo.ArchiveInfoQuery;
import com.lz.manage.model.dto.archiveInfo.ArchiveInfoInsert;
import com.lz.manage.model.dto.archiveInfo.ArchiveInfoEdit;
import com.lz.manage.service.IArchiveInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 学生档案Controller
 *
 * @author YY
 * @date 2025-05-12
 */
@RestController
@RequestMapping("/manage/archiveInfo")
public class ArchiveInfoController extends BaseController
{
    @Resource
    private IArchiveInfoService archiveInfoService;

    /**
     * 查询学生档案列表
     */
    @PreAuthorize("@ss.hasPermi('manage:archiveInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArchiveInfoQuery archiveInfoQuery)
    {
        ArchiveInfo archiveInfo = ArchiveInfoQuery.queryToObj(archiveInfoQuery);
        startPage();
        List<ArchiveInfo> list = archiveInfoService.selectArchiveInfoList(archiveInfo);
        List<ArchiveInfoVo> listVo= list.stream().map(ArchiveInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出学生档案列表
     */
    @PreAuthorize("@ss.hasPermi('manage:archiveInfo:export')")
    @Log(title = "学生档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ArchiveInfoQuery archiveInfoQuery)
    {
        ArchiveInfo archiveInfo = ArchiveInfoQuery.queryToObj(archiveInfoQuery);
        List<ArchiveInfo> list = archiveInfoService.selectArchiveInfoList(archiveInfo);
        ExcelUtil<ArchiveInfo> util = new ExcelUtil<ArchiveInfo>(ArchiveInfo.class);
        util.exportExcel(response, list, "学生档案数据");
    }

    /**
     * 获取学生档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:archiveInfo:query')")
    @GetMapping(value = "/{archiveId}")
    public AjaxResult getInfo(@PathVariable("archiveId") Long archiveId)
    {
        ArchiveInfo archiveInfo = archiveInfoService.selectArchiveInfoByArchiveId(archiveId);
        return success(ArchiveInfoVo.objToVo(archiveInfo));
    }

    /**
     * 新增学生档案
     */
    @PreAuthorize("@ss.hasPermi('manage:archiveInfo:add')")
    @Log(title = "学生档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArchiveInfoInsert archiveInfoInsert)
    {
        ArchiveInfo archiveInfo = ArchiveInfoInsert.insertToObj(archiveInfoInsert);
        return toAjax(archiveInfoService.insertArchiveInfo(archiveInfo));
    }

    /**
     * 修改学生档案
     */
    @PreAuthorize("@ss.hasPermi('manage:archiveInfo:edit')")
    @Log(title = "学生档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArchiveInfoEdit archiveInfoEdit)
    {
        ArchiveInfo archiveInfo = ArchiveInfoEdit.editToObj(archiveInfoEdit);
        return toAjax(archiveInfoService.updateArchiveInfo(archiveInfo));
    }

    /**
     * 删除学生档案
     */
    @PreAuthorize("@ss.hasPermi('manage:archiveInfo:remove')")
    @Log(title = "学生档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{archiveIds}")
    public AjaxResult remove(@PathVariable Long[] archiveIds)
    {
        return toAjax(archiveInfoService.deleteArchiveInfoByArchiveIds(archiveIds));
    }
}
