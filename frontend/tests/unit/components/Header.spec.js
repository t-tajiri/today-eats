import { createLocalVue, shallowMount, RouterLinkStub } from '@vue/test-utils'
import VueRouter from 'vue-router'
import 'vue-awesome/icons/utensils'
import 'vue-awesome/icons/cog'
import Icon from 'vue-awesome/components/Icon'
import Header from '@/components/Header.vue'

const localVue = createLocalVue()
localVue.use(VueRouter)
localVue.component('v-icon', Icon)

describe('Header.vue', () => {
  const createWrapper = () => shallowMount(Header, {
    localVue,
    stubs: {
      RouterLink: RouterLinkStub
    }
  })

  it('正常に描画される', () => {
    const wrapper = createWrapper()

    expect(wrapper.isVisible()).toBe(true)
  })

  it('正しいリンクが設定される', async () => {
    const wrapper = createWrapper()

    const links = wrapper.findAll(RouterLinkStub)
    expect(links.filter(w => w.props().to === '/').length).toBe(1)
    expect(links.filter(w => w.props().to === '/settings').length).toBe(1)
  })
})
