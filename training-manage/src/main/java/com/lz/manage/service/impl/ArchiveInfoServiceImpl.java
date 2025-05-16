package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.annotation.DataScope;
import com.lz.common.core.domain.entity.SysDept;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.ClassInfo;
import com.lz.manage.service.IClassInfoService;
import com.lz.system.service.ISysDeptService;
import com.lz.system.service.ISysUserService;
import org.apache.poi.hpsf.IllegalPropertySetDataException;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.ArchiveInfoMapper;
import com.lz.manage.model.domain.ArchiveInfo;
import com.lz.manage.service.IArchiveInfoService;
import com.lz.manage.model.dto.archiveInfo.ArchiveInfoQuery;
import com.lz.manage.model.vo.archiveInfo.ArchiveInfoVo;

/**
 * 学生档案Service业务层处理
 *
 * @author YY
 * @date 2025-05-12
 */
@Service
public class ArchiveInfoServiceImpl extends ServiceImpl<ArchiveInfoMapper, ArchiveInfo> implements IArchiveInfoService {
    @Resource
    private ArchiveInfoMapper archiveInfoMapper;

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService deptService;

    @Resource
    private IClassInfoService classInfoService;

    //region mybatis代码

    /**
     * 查询学生档案
     *
     * @param archiveId 学生档案主键
     * @return 学生档案
     */
    @Override
    public ArchiveInfo selectArchiveInfoByArchiveId(Long archiveId) {
        return archiveInfoMapper.selectArchiveInfoByArchiveId(archiveId);
    }

    /**
     * 查询学生档案列表
     *
     * @param archiveInfo 学生档案
     * @return 学生档案
     */
    @DataScope(userAlias = "tb_archive_info", deptAlias = "tb_archive_info")
    @Override
    public List<ArchiveInfo> selectArchiveInfoList(ArchiveInfo archiveInfo) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("student")) {
            archiveInfo.setUserId(SecurityUtils.getUserId());
        }
        List<ArchiveInfo> archiveInfos = archiveInfoMapper.selectArchiveInfoList(archiveInfo);
        for (ArchiveInfo info : archiveInfos) {
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserInfoName(sysUser.getUserName());
            }
            SysDept sysDept = deptService.selectDeptById(info.getDeptId());
            if (StringUtils.isNotNull(sysDept)) {
                info.setDeptName(sysDept.getDeptName());
            }
            ClassInfo classInfo = classInfoService.selectClassInfoByClassId(info.getClassId());
            if (StringUtils.isNotNull(classInfo)) {
                info.setClassName(classInfo.getClassName());
            }
        }
        return archiveInfos;
    }

    /**
     * 新增学生档案
     *
     * @param archiveInfo 学生档案
     * @return 结果
     */
    @Override
    public int insertArchiveInfo(ArchiveInfo archiveInfo) {
        checkClass(archiveInfo);
        archiveInfo.setCreateTime(DateUtils.getNowDate());
        return archiveInfoMapper.insertArchiveInfo(archiveInfo);
    }

    private void checkClass(ArchiveInfo archiveInfo) {
        SysUser sysUser = userService.selectUserById(archiveInfo.getUserId());
        if (StringUtils.isNull(sysUser)) {
            throw new ServiceException("该学生不存在！！！");
        }
        //查询班级
        ClassInfo classInfo = classInfoService.selectClassInfoByClassId(sysUser.getClassId());
        if (StringUtils.isNull(classInfo)) {
            throw new ServiceException("该学生未绑定班级！！！");
        }
        if (StringUtils.isNull(classInfo.getDeptId())) {
            throw new ServiceException("该班级未绑定学院！！！");
        }
        archiveInfo.setDeptId(classInfo.getDeptId());
        archiveInfo.setClassId(sysUser.getClassId());
    }

    /**
     * 修改学生档案
     *
     * @param archiveInfo 学生档案
     * @return 结果
     */
    @Override
    public int updateArchiveInfo(ArchiveInfo archiveInfo) {
        checkClass(archiveInfo);
        archiveInfo.setUpdateTime(DateUtils.getNowDate());
        archiveInfo.setUpdatedBy(SecurityUtils.getUsername());
        return archiveInfoMapper.updateArchiveInfo(archiveInfo);
    }

    /**
     * 批量删除学生档案
     *
     * @param archiveIds 需要删除的学生档案主键
     * @return 结果
     */
    @Override
    public int deleteArchiveInfoByArchiveIds(Long[] archiveIds) {
        return archiveInfoMapper.deleteArchiveInfoByArchiveIds(archiveIds);
    }

    /**
     * 删除学生档案信息
     *
     * @param archiveId 学生档案主键
     * @return 结果
     */
    @Override
    public int deleteArchiveInfoByArchiveId(Long archiveId) {
        return archiveInfoMapper.deleteArchiveInfoByArchiveId(archiveId);
    }

    //endregion
    @Override
    public QueryWrapper<ArchiveInfo> getQueryWrapper(ArchiveInfoQuery archiveInfoQuery) {
        QueryWrapper<ArchiveInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = archiveInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long archiveId = archiveInfoQuery.getArchiveId();
        queryWrapper.eq(StringUtils.isNotNull(archiveId), "archive_id", archiveId);

        String username = archiveInfoQuery.getUsername();
        queryWrapper.like(StringUtils.isNotEmpty(username), "username", username);

        String stuNo = archiveInfoQuery.getStuNo();
        queryWrapper.like(StringUtils.isNotEmpty(stuNo), "stu_no", stuNo);

        String sex = archiveInfoQuery.getSex();
        queryWrapper.eq(StringUtils.isNotEmpty(sex), "sex", sex);

        Date birthday = archiveInfoQuery.getBirthday();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginBirthday")) && StringUtils.isNotNull(params.get("endBirthday")), "birthday", params.get("beginBirthday"), params.get("endBirthday"));

        String email = archiveInfoQuery.getEmail();
        queryWrapper.eq(StringUtils.isNotEmpty(email), "email", email);

        Date joinTime = archiveInfoQuery.getJoinTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginJoinTime")) && StringUtils.isNotNull(params.get("endJoinTime")), "join_time", params.get("beginJoinTime"), params.get("endJoinTime"));

        String graduatingSchool = archiveInfoQuery.getGraduatingSchool();
        queryWrapper.eq(StringUtils.isNotEmpty(graduatingSchool), "graduating_school", graduatingSchool);

        Date graduatingTime = archiveInfoQuery.getGraduatingTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginGraduatingTime")) && StringUtils.isNotNull(params.get("endGraduatingTime")), "graduating_time", params.get("beginGraduatingTime"), params.get("endGraduatingTime"));

        String idCard = archiveInfoQuery.getIdCard();
        queryWrapper.eq(StringUtils.isNotEmpty(idCard), "id_card", idCard);

        String education = archiveInfoQuery.getEducation();
        queryWrapper.eq(StringUtils.isNotEmpty(education), "education", education);

        String nation = archiveInfoQuery.getNation();
        queryWrapper.eq(StringUtils.isNotEmpty(nation), "nation", nation);

        String nativePlace = archiveInfoQuery.getNativePlace();
        queryWrapper.eq(StringUtils.isNotEmpty(nativePlace), "native_place", nativePlace);

        String phone = archiveInfoQuery.getPhone();
        queryWrapper.eq(StringUtils.isNotEmpty(phone), "phone", phone);

        String department = archiveInfoQuery.getDepartment();
        queryWrapper.eq(StringUtils.isNotEmpty(department), "department", department);

        Long userId = archiveInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long deptId = archiveInfoQuery.getDeptId();
        queryWrapper.eq(StringUtils.isNotNull(deptId), "dept_id", deptId);

        Date createTime = archiveInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updatedBy = archiveInfoQuery.getUpdatedBy();
        queryWrapper.like(StringUtils.isNotEmpty(updatedBy), "updated_by", updatedBy);

        Date updateTime = archiveInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<ArchiveInfoVo> convertVoList(List<ArchiveInfo> archiveInfoList) {
        if (StringUtils.isEmpty(archiveInfoList)) {
            return Collections.emptyList();
        }
        return archiveInfoList.stream().map(ArchiveInfoVo::objToVo).collect(Collectors.toList());
    }

}
