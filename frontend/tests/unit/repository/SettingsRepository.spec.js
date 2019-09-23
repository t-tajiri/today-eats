import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import SettingsRepository from '@/repository/SettingsRepository.js'

const BASE_ENDPOINT = '/settings'
const MYCATEGORY_ENDPOINT = BASE_ENDPOINT + '/mine'
const EATS_ENDPOINT = BASE_ENDPOINT + '/eats'
const categories = ['和風', '洋風', '中華']
const myCategoryId = 1
const Id = 2
const eats = { id: Id, name: 'カレーライス', category_id: 1 }

const status = {
  OK: 200,
  CREATED: 201,
  NO_CONTENT: 204,
  INTERNAL_SERVER_ERROR: 500
}

describe('SettingsRepostiory.js', () => {
  describe('GET /settings', () => {
    let mock
    let api

    beforeAll(() => {
      mock = new MockAdapter(axios)
      api = new SettingsRepository()
    })

    afterEach(() => {
      mock.restore()
    })

    it('レスポンスが正常に返却される', async () => {
      mock.onGet(BASE_ENDPOINT).reply(status.OK, categories)

      const { data } = await api.retrieveCategories()

      expect(data).toBe(categories)
    })

    it('Internal Server Errorの時エラーが発生する', async () => {
      mock.onGet(BASE_ENDPOINT).reply(status.INTERNAL_SERVER_ERROR)

      try {
        await api.retrieveCategories()
        // eslint-disable-next-line no-undef
        fail('Internal Server Errorでエラー処理される必要があります')
      } catch (error) {
        expect(error.response.status).toBe(status.INTERNAL_SERVER_ERROR)
      }
    })
  })
  describe('GET /settings/mine', () => {
    let mock
    let api

    beforeAll(() => {
      mock = new MockAdapter(axios)
      api = new SettingsRepository()
    })

    afterEach(() => {
      mock.restore()
    })

    it('レスポンスが正常に返却される', async () => {
      mock.onGet(MYCATEGORY_ENDPOINT).reply(status.OK, { id: myCategoryId })

      const { data } = await api.retrieveMyCategory()

      expect(data.id).toBe(myCategoryId)
    })

    it('Internal Server Errorの時エラーが発生する', async () => {
      mock.onGet(BASE_ENDPOINT).reply(status.INTERNAL_SERVER_ERROR)

      try {
        await api.retrieveCategories()
        // eslint-disable-next-line no-undef
        fail('Internal Server Errorでエラー処理される必要があります')
      } catch (error) {
        expect(error.response.status).toBe(status.INTERNAL_SERVER_ERROR)
      }
    })
  })
  describe('POST /settings', () => {
    let mock
    let api

    beforeAll(() => {
      mock = new MockAdapter(axios)
      api = new SettingsRepository()
    })

    afterEach(() => {
      mock.restore()
    })

    it('レスポンスが正常に返却される', async () => {
      mock.onPost(BASE_ENDPOINT, { id: Id }).reply(status.CREATED, { id: myCategoryId })

      const result = await api.registerMyCategory(Id)

      expect(result.status).toBe(status.CREATED)
    })

    it('Internal Server Errorの時エラーが発生する', async () => {
      mock.onPost(BASE_ENDPOINT, { id: Id }).reply(status.INTERNAL_SERVER_ERROR)

      try {
        await api.registerMyCategory(Id)
        // eslint-disable-next-line no-undef
        fail('Internal Server Errorでエラー処理される必要があります')
      } catch (error) {
        expect(error.response.status).toBe(status.INTERNAL_SERVER_ERROR)
      }
    })
  })
  describe('GET /settings/eats', () => {
    let mock
    let api

    beforeAll(() => {
      mock = new MockAdapter(axios)
      api = new SettingsRepository()
    })

    afterEach(() => {
      mock.restore()
    })

    it('レスポンスが正常に返却される', async () => {
      mock.onGet(EATS_ENDPOINT).reply(status.OK, eats)

      const { data } = await api.retrieveEats()

      expect(data).toEqual(eats)
    })

    it('Internal Server Errorの時エラーが発生する', async () => {
      mock.onGet(EATS_ENDPOINT).reply(status.INTERNAL_SERVER_ERROR)

      try {
        await api.retrieveEats()
        // eslint-disable-next-line no-undef
        fail('Internal Server Errorでエラー処理される必要があります')
      } catch (error) {
        expect(error.response.status).toBe(status.INTERNAL_SERVER_ERROR)
      }
    })
  })
  describe('PUT /settings/eats', () => {
    let mock
    let api

    beforeAll(() => {
      mock = new MockAdapter(axios)
      api = new SettingsRepository()
    })

    afterEach(() => {
      mock.restore()
    })

    it('レスポンスが正常に返却される', async () => {
      mock.onPut(EATS_ENDPOINT, eats).reply(status.NO_CONTENT)

      const result = await api.updateEats(eats)

      expect(result.status).toBe(status.NO_CONTENT)
    })

    it('Internal Server Errorの時エラーが発生する', async () => {
      mock.onPut(EATS_ENDPOINT, eats).reply(status.INTERNAL_SERVER_ERROR)

      try {
        await api.updateEats(eats)
        // eslint-disable-next-line no-undef
        fail('Internal Server Errorでエラー処理される必要があります')
      } catch (error) {
        expect(error.response.status).toBe(status.INTERNAL_SERVER_ERROR)
      }
    })
  })
  describe.skip('DELETE /settings/eats', () => {})
})
