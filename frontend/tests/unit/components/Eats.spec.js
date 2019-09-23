import { shallowMount } from '@vue/test-utils'
import Eats from '@/components/Eats.vue'

describe('Eats.vue', () => {
  const eats = [{ id: 1, name: 'おにぎり', category_id: 2 }, { id: 2, name: 'カレーライス', category_id: 1 }]
  const formCategories = [{ id: 1, name: '洋風' }, { id: 2, name: '和風' }]
  const createWrapper = () => shallowMount(Eats, { propsData: { eats, formCategories } })

  it.skip('ご飯の内容を変更できる', () => {
    const wrapper = createWrapper()

    wrapper.find('#settings__eats-update-1').vm.$emit('click')

    expect(wrapper.emitted().updateEats[0][0]).toBe(0)
  })

  it.skip('ご飯の内容を削除できる', () => {
    const wrapper = createWrapper()

    wrapper.find('#settings__eats-delete-1').vm.$emit('click')

    expect(wrapper.emitted().deleteEats[0][0]).toBe(0)
  })
})
