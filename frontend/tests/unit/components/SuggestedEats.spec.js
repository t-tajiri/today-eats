import { shallowMount } from '@vue/test-utils'
import SuggestedEats from '@/components/SuggestedEats.vue'
import Button from '@/components/Button.vue'

describe('SuggestedEats.vue', () => {
  const createWrapper = (eats) => shallowMount(SuggestedEats, {
    propsData: { eats: eats }
  })
  it('親コンポーネントからpropsで渡された値を表示する', () => {
    const eats = '🍖焼肉弁当'
    const wrapper = createWrapper(eats)

    expect(wrapper.find('#suggested-eats').text()).toBe(eats)
  })

  it('ボタンコンポーネントからイベントが伝播される', () => {
    const wrapper = createWrapper('')

    wrapper.find(Button).vm.$emit('click')

    expect(wrapper.emitted().click.length).toBe(1)
  })
})
