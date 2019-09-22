import axios from 'axios'

const endpoint = '/settings'

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

  retrieveCategories () {
    return this.api.get(endpoint)
  }

  retrieveMyCategory () {
    return this.api.get(endpoint + '/mine')
  }

  registerMyCategory (id) {
    return this.api.post(endpoint, { id })
  }
}
