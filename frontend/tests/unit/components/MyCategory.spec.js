import { shallowMount } from '@vue/test-utils'
import MyCategory from '@/components/MyCategory.vue'
import Button from '@/components/Button.vue'

describe('MyCategory.vue', () => {
  const selected = 0
  const categories = [{ id: 0, name: '洋風' }, { id: 1, name: '和風' }]

  const createWrapper = () => shallowMount(MyCategory, { propsData: { categories, selected } })

  it('ジャンルが選択できる', () => {
    const wrapper = createWrapper()

    expect(wrapper.props().selected).toBe(0)

    const options = wrapper.find('#settings__categories').findAll('option')
    options.at(1).setSelected()

    expect(options.at(1).text()).toBe('和風')
    expect(wrapper.props().selected).toBe(1)
  })

  it('自分のジャンルとして登録できる', () => {
    const wrapper = createWrapper()

    wrapper.find(Button).vm.$emit('click')

    expect(wrapper.emitted().registerCategory.length).toBe(1)
  })
})
