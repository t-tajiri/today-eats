import BaseRepository from '@/repository/BaseRepository.js'

const endpoint = '/today-eats'

export default class EatsRepository extends BaseRepository {
  getTodayEats () {
    return this.api.get(endpoint)
  }
}
