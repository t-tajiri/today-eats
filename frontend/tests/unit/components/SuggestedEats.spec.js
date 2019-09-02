import { shallowMount } from '@vue/test-utils'
import SuggestedEats from '@/components/SuggestedEats.vue'

describe('SuggestedEats.vue', () => {
  it('親コンポーネントからpropsで渡された値を表示する', () => {
    const eats = '🍖焼肉弁当'

    const wrapper = shallowMount(SuggestedEats, {
      propsData: { eats: eats }
    })

    expect(wrapper.find('#suggested-eats').text()).toBe(eats)
  })
})
