import { createLocalVue, shallowMount } from '@vue/test-utils'
import 'vue-awesome/icons/info-circle'
import Icon from 'vue-awesome/components/Icon'
import Home from '@/views/Home.vue'
import Button from '@/components/Button.vue'
import HomeRepository from '@/repository/EatsRepository.js'
import SuggestedEats from '@/components/SuggestedEats.vue'

const message = '今日のご飯を決めよう！'
const eats = '🍛 カレーライス'

// メソッドをモック化するために第2引数にモック関数を代入
jest.mock('@/repository/EatsRepository.js', () => jest.fn())

const localVue = createLocalVue()
localVue.component('v-icon', Icon)

const createShallowWrapper = () => shallowMount(Home, { localVue })

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

    it('ボタンをクリックした時にコンポーネント内のデータを変更される', async () => {
      const wrapper = createShallowWrapper()

      const eatsBeforeClicked = wrapper.vm.eats
      expect(eatsBeforeClicked).toBe(null)

      wrapper.find(Button).vm.$emit('click')
      await wrapper.vm.$nextTick()

      const eatsAfterClicked = wrapper.vm.eats
      expect(eatsAfterClicked).toBe(eats)
    })

    it('子コンポーネントからイベントが伝播されるとモーダルが切り替わる', async () => {
      const wrapper = createShallowWrapper()

      wrapper.find(Button).vm.$emit('click')
      await wrapper.vm.$nextTick()

      expect(wrapper.vm.showModal).toBe(true)

      wrapper.find(SuggestedEats).vm.$emit('click')
      await wrapper.vm.$nextTick()

      expect(wrapper.vm.showModal).toBe(false)
    })
  })
})
