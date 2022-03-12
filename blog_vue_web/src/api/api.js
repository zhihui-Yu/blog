import axios from 'axios'

const request = axios.create({
  baseURL: 'api', // api 的 base_url
  withCredentials: true, // 允许后台的cookie传递到前端
  timeout: 100000 // 请求超时时间
})

export function getDirectory () {
  return request({
    url: process.env.API_HOST + '/directory',
    method: 'get'
  })
}
