import request from '@/utils/request'

// 查询学生档案列表
export function listArchiveInfo(query) {
  return request({
    url: '/manage/archiveInfo/list',
    method: 'get',
    params: query
  })
}

// 查询学生档案详细
export function getArchiveInfo(archiveId) {
  return request({
    url: '/manage/archiveInfo/' + archiveId,
    method: 'get'
  })
}

// 新增学生档案
export function addArchiveInfo(data) {
  return request({
    url: '/manage/archiveInfo',
    method: 'post',
    data: data
  })
}

// 修改学生档案
export function updateArchiveInfo(data) {
  return request({
    url: '/manage/archiveInfo',
    method: 'put',
    data: data
  })
}

// 删除学生档案
export function delArchiveInfo(archiveId) {
  return request({
    url: '/manage/archiveInfo/' + archiveId,
    method: 'delete'
  })
}
