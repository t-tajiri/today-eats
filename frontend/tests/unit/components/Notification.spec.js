import { createLocalVue, shallowMount } from '@vue/test-utils'
import 'vue-awesome/icons/info-circle'
import Icon from 'vue-awesome/components/Icon'
import Notification from '@/components/Notification.vue'

const localVue = createLocalVue()
localVue.component('v-icon', Icon)

describe('Notification.vue', () => {
  const createWrapper = (isNotified) => shallowMount(Notification, { localVue, propsData: { isNotified } })
  it('通知ONの場合はメッセージが表示される', () => {
    const isNotified = true

    const wrapper = createWrapper(isNotified)

    expect(wrapper.find('#settings__notification').exists()).toBe(true)
  })

  it('通知OFFの場合はメッセージが表示されない', () => {
    const isNotified = false

    const wrapper = createWrapper(isNotified)

    expect(wrapper.find('#settings__notification').exists()).toBe(false)
  })
})
