import { shallowMount } from '@vue/test-utils'
import Button from '@/components/Button.vue'

const buttonName = 'ご飯を決める'

describe('Button.vue', () => {
  it('あらかじめ決めたボタン名を表示する', () => {
    const wrapper = shallowMount(Button)

    expect(wrapper.find('button').text()).toBe(buttonName)
  })

  it('クリックするとイベントが伝播する', () => {
    const wrapper = shallowMount(Button)

    wrapper.find('button').trigger('click')

    expect(wrapper.emitted().click.length).toBe(1)
  })
})
