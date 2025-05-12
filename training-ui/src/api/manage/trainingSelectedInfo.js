import request from '@/utils/request'

// 查询实训选择列表
export function listTrainingSelectedInfo(query) {
  return request({
    url: '/manage/trainingSelectedInfo/list',
    method: 'get',
    params: query
  })
}

// 查询实训选择详细
export function getTrainingSelectedInfo(selectedId) {
  return request({
    url: '/manage/trainingSelectedInfo/' + selectedId,
    method: 'get'
  })
}

// 新增实训选择
export function addTrainingSelectedInfo(data) {
  return request({
    url: '/manage/trainingSelectedInfo',
    method: 'post',
    data: data
  })
}

// 修改实训选择
export function updateTrainingSelectedInfo(data) {
  return request({
    url: '/manage/trainingSelectedInfo',
    method: 'put',
    data: data
  })
}

// 删除实训选择
export function delTrainingSelectedInfo(selectedId) {
  return request({
    url: '/manage/trainingSelectedInfo/' + selectedId,
    method: 'delete'
  })
}
