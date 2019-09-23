import BaseRepository from '@/repository/BaseRepository.js'

const endpoint = '/settings'

export default class SettingsRepository extends BaseRepository {
  retrieveCategories () {
    return this.api.get(endpoint)
  }

  retrieveMyCategory () {
    return this.api.get(endpoint + '/mine')
  }

  registerMyCategory (id) {
    return this.api.post(endpoint, { id })
  }

  retrieveEats () {
    return this.api.get(endpoint + '/eats')
  }

  updateEats (eats) {
    return this.api.put(endpoint + '/eats', eats)
  }
}
