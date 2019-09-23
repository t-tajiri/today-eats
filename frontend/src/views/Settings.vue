<template>
  <div class="flex">
    <div class="w-8/12 mt-10 m-auto">
      <h1 class="text-4xl">
        設定
      </h1>
      <hr class="solid">
      <MyCategory
        :categories="categories"
        :selected="selected"
        @registerCategory="registerCategory"
      />
      <Eats
        :eats="eats"
        :form-categories="formCategories"
        @updateEats="updateEats"
      />
    </div>
    <Notification
      :is-notified="isNotified"
    />
  </div>
</template>

<script>
import SettingsRepository from '@/repository/SettingsRepository.js'
import MyCategory from '@/components/MyCategory.vue'
import Eats from '@/components/Eats.vue'
import Notification from '@/components/Notification.vue'

const SUCCESS_RESPONSE_CREATED = 201
const SUCCESS_RESPONSE_NO_CONTENT = 204

function toggleNotify (vm) {
  vm.isNotified = true
  setTimeout(() => {
    vm.isNotified = false
  }, 3500)
}

export default {
  name: 'Settings',
  components: {
    Notification,
    MyCategory,
    Eats
  },
  data: () => ({
    api: new SettingsRepository(),
    categories: [],
    eats: [],
    formCategories: [],
    isNotified: false,
    selected: 0
  }),
  async created () {
    const [{ data: categories }, { data: myCategory }, { data: eats }] = await Promise.all([ this.api.retrieveCategories(), this.api.retrieveMyCategory(), this.api.retrieveEats() ])

    this.categories = categories
    this.formCategories = categories.filter(category => category.name !== '全て')

    this.eats = eats
    this.eats.push({ id: '', name: '', category_id: '' })

    this.selected = myCategory.id
  },
  methods: {
    async registerCategory (selected) {
      this.isNotified = false
      this.selected = selected
      const myCategory = this.categories.find(category => { return category.id === this.selected })
      const { status } = await this.api.registerMyCategory(myCategory.id)

      if (status === SUCCESS_RESPONSE_CREATED) {
        toggleNotify(this)
      }
    },
    async updateEats (targetIndex) {
      this.isNotified = false
      const { status } = await this.api.updateEats(this.eats[targetIndex])

      if (status === SUCCESS_RESPONSE_NO_CONTENT) {
        toggleNotify(this)
      }
    }
  }
}
</script>

<style scoped>
.solid {
  border-top: 1px solid #8c8b8b;
}
</style>
