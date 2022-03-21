import axios from 'axios'

const API_HOST = 'http://yuzhihui.xyz/api'

// 如果要跨域的话，对axios进行一些设置
const axiosInstance = axios.create({
  headers: {'Content-Type': 'application/json;charset=utf-8'}, // 设置传输内容的类型和编码
  withCredentials: true // 指定某个请求应该发送凭据
})

export function getDirectory () {
  return axiosInstance({
    url: API_HOST + '/directory',
    method: 'get'
  })
}

export function getBlog (id) {
  return axiosInstance({
    url: API_HOST + '/blog/' + id,
    method: 'get'
  })
}
