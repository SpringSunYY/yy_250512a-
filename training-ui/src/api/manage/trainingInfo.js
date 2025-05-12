import request from '@/utils/request'

// 查询实训信息列表
export function listTrainingInfo(query) {
  return request({
    url: '/manage/trainingInfo/list',
    method: 'get',
    params: query
  })
}

// 查询实训信息详细
export function getTrainingInfo(trainingId) {
  return request({
    url: '/manage/trainingInfo/' + trainingId,
    method: 'get'
  })
}

// 新增实训信息
export function addTrainingInfo(data) {
  return request({
    url: '/manage/trainingInfo',
    method: 'post',
    data: data
  })
}

// 修改实训信息
export function updateTrainingInfo(data) {
  return request({
    url: '/manage/trainingInfo',
    method: 'put',
    data: data
  })
}

// 删除实训信息
export function delTrainingInfo(trainingId) {
  return request({
    url: '/manage/trainingInfo/' + trainingId,
    method: 'delete'
  })
}
