import BaseRepository from '@/repository/BaseRepository.js'

describe('BaseRepository.js', () => {
  it('baseURLとheaderにcorsの設定がされている', () => {
    const result = new BaseRepository()
    console.log(result.api.defaults.headers)
  })
})
