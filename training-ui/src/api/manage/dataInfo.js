import request from '@/utils/request'

// 查询资料信息列表
export function listDataInfo(query) {
  return request({
    url: '/manage/dataInfo/list',
    method: 'get',
    params: query
  })
}

// 查询资料信息详细
export function getDataInfo(dataId) {
  return request({
    url: '/manage/dataInfo/' + dataId,
    method: 'get'
  })
}

// 新增资料信息
export function addDataInfo(data) {
  return request({
    url: '/manage/dataInfo',
    method: 'post',
    data: data
  })
}

// 修改资料信息
export function updateDataInfo(data) {
  return request({
    url: '/manage/dataInfo',
    method: 'put',
    data: data
  })
}

// 删除资料信息
export function delDataInfo(dataId) {
  return request({
    url: '/manage/dataInfo/' + dataId,
    method: 'delete'
  })
}
