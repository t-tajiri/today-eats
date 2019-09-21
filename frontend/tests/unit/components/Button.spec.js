import { shallowMount } from '@vue/test-utils'
import Button from '@/components/Button.vue'

const name = 'ご飯を決める'

describe('Button.vue', () => {
  const createWrapper = () => shallowMount(Button, { propsData: { name: name } })

  it('親コンポーネントからpropsで渡された値を表示する', () => {
    const wrapper = createWrapper()

    expect(wrapper.find('button').text()).toBe(name)
  })

  it('クリックするとイベントが伝播する', () => {
    const wrapper = createWrapper()

    wrapper.find('button').trigger('click')

    expect(wrapper.emitted().click.length).toBe(1)
  })
})
