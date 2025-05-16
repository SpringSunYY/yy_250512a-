import request from '@/utils/request'

// 查询班级信息列表
export function listClassInfo(query) {
  return request({
    url: '/manage/classInfo/list',
    method: 'get',
    params: query
  })
}

// 查询班级信息详细
export function getClassInfo(classId) {
  return request({
    url: '/manage/classInfo/' + classId,
    method: 'get'
  })
}

// 新增班级信息
export function addClassInfo(data) {
  return request({
    url: '/manage/classInfo',
    method: 'post',
    data: data
  })
}

// 修改班级信息
export function updateClassInfo(data) {
  return request({
    url: '/manage/classInfo',
    method: 'put',
    data: data
  })
}

// 删除班级信息
export function delClassInfo(classId) {
  return request({
    url: '/manage/classInfo/' + classId,
    method: 'delete'
  })
}
