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
import com.lz.manage.model.domain.ClassInfo;
import com.lz.manage.model.vo.classInfo.ClassInfoVo;
import com.lz.manage.model.dto.classInfo.ClassInfoQuery;
import com.lz.manage.model.dto.classInfo.ClassInfoInsert;
import com.lz.manage.model.dto.classInfo.ClassInfoEdit;
import com.lz.manage.service.IClassInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 班级信息Controller
 *
 * @author YY
 * @date 2025-05-16
 */
@RestController
@RequestMapping("/manage/classInfo")
public class ClassInfoController extends BaseController
{
    @Resource
    private IClassInfoService classInfoService;

    /**
     * 查询班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:classInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClassInfoQuery classInfoQuery)
    {
        ClassInfo classInfo = ClassInfoQuery.queryToObj(classInfoQuery);
        startPage();
        List<ClassInfo> list = classInfoService.selectClassInfoList(classInfo);
        List<ClassInfoVo> listVo= list.stream().map(ClassInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:classInfo:export')")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClassInfoQuery classInfoQuery)
    {
        ClassInfo classInfo = ClassInfoQuery.queryToObj(classInfoQuery);
        List<ClassInfo> list = classInfoService.selectClassInfoList(classInfo);
        ExcelUtil<ClassInfo> util = new ExcelUtil<ClassInfo>(ClassInfo.class);
        util.exportExcel(response, list, "班级信息数据");
    }

    /**
     * 获取班级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:classInfo:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        ClassInfo classInfo = classInfoService.selectClassInfoByClassId(classId);
        return success(ClassInfoVo.objToVo(classInfo));
    }

    /**
     * 新增班级信息
     */
    @PreAuthorize("@ss.hasPermi('manage:classInfo:add')")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClassInfoInsert classInfoInsert)
    {
        ClassInfo classInfo = ClassInfoInsert.insertToObj(classInfoInsert);
        return toAjax(classInfoService.insertClassInfo(classInfo));
    }

    /**
     * 修改班级信息
     */
    @PreAuthorize("@ss.hasPermi('manage:classInfo:edit')")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClassInfoEdit classInfoEdit)
    {
        ClassInfo classInfo = ClassInfoEdit.editToObj(classInfoEdit);
        return toAjax(classInfoService.updateClassInfo(classInfo));
    }

    /**
     * 删除班级信息
     */
    @PreAuthorize("@ss.hasPermi('manage:classInfo:remove')")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(classInfoService.deleteClassInfoByClassIds(classIds));
    }
}
