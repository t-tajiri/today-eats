import axios from 'axios'

export default class BaseRepository {
  constructor () {
    this.api = axios.create({
      baseURL: process.env.VUE_APP_BACKEND_URL,
      headers: {
        'Access-Control-Allow-Origin': process.env.VUE_APP_BACKEND_URL,
        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': process.env.VUE_APP_BACKEND_URL
      }
    })
  }
}
