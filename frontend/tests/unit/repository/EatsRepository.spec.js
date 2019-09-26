import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import EatsRepository from '@/repository/EatsRepository.js'

const endpoint = '/today-eats'
const eats = 'ハンバーガー🍔'

const status = {
  OK: 200,
  INTERNAL_SERVER_ERROR: 500
}

describe('HomeRepostiory.js', () => {
  describe('GET /today-eats', () => {
    let mock
    let api

    beforeAll(() => {
      mock = new MockAdapter(axios)
      api = new EatsRepository()
    })

    afterEach(() => {
      mock.restore()
    })

    it('レスポンスが正常に返却される', async () => {
      mock.onGet(endpoint).reply(status.OK, { name: eats })

      const { data } = await api.getTodayEats()

      expect(data.name).toBe(eats)
    })

    it('Internal Server Errorの時エラーが発生する', async () => {
      mock.onGet(endpoint).reply(status.INTERNAL_SERVER_ERROR)

      try {
        await api.getTodayEats()
        // eslint-disable-next-line no-undef
        fail('Internal Server Errorでエラー処理される必要があります')
      } catch (error) {
        expect(error.response.status).toBe(status.INTERNAL_SERVER_ERROR)
      }
    })
  })
})
