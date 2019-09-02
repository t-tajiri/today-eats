import { shallowMount } from '@vue/test-utils'
import Home from '@/views/Home.vue'
import Button from '@/components/Button.vue'
import HomeRepository from '@/repository/HomeRepository.js'

const message = '今日のご飯を決めよう！'
const eats = '🍛 カレーライス'

// メソッドをモック化するために第2引数にモック関数を代入
jest.mock('@/repository/HomeRepository.js', () => jest.fn())

const createShallowWrapper = () => shallowMount(Home)

describe('Home.vue', () => {
  describe('初期表示', () => {
    it('タイトルを表示する', () => {
      const wrapper = createShallowWrapper()

      const title = wrapper.find('h1').text()
      expect(title).toBe(message)
    })

    it('ボタンを表示する', () => {
      const wrapper = createShallowWrapper()

      const button = wrapper.find(Button)
      expect(button).toBeTruthy()
    })
  })

  describe('ボタンクリック時', () => {

    beforeAll(() => {
      HomeRepository.mockImplementation(() => ({
        getTodayEats: () => ({ data: { name: eats } })
      }))
    })

    afterAll(() => {
      jest.restoreAllMocks()
    })

    it('コンポーネント内のデータを変更する', async () => {
      const wrapper = createShallowWrapper()

      const eatsBeforeClicked = wrapper.vm.eats
      expect(eatsBeforeClicked).toBe('')

      wrapper.find(Button).vm.$emit('click')
      await wrapper.vm.$nextTick()

      const eatsAfterClicked = wrapper.vm.eats
      expect(eatsAfterClicked).toBe(eats)
    })
  })
})
