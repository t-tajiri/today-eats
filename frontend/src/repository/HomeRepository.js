import axios from 'axios'

const endpoint = '/today-eats'

export default class HomeRepository {
  constructor () {
    this.api = axios.create({
      baseURL: process.env.VUE_APP_BACKEND_URL,
      headers: {
        'Access-Control-Allow-Origin': process.env.VUE_APP_BACKEND_URL,
        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': '*'
      }
    })
  }

  getTodayEats () {
    return this.api.get(endpoint)
  }
}
