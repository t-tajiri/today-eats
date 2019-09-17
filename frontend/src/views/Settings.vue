<template>
  <div>
    <p id="settings__my-category">現在の設定は {{ myCategory }} になっています</p>
    <label for="settings__categories">好みのジャンル設定</label>
    <select
      id="settings__categories"
      v-model="selected"
    >
      <option
        v-for="category in categories"
        :value="category.id"
        :key="category.id"
      >
        {{ category.name }}
      </option>
    </select>
    <button @click="onClick">
      登録
    </button>
  </div>
</template>

<script>
import SettingsRepository from '@/repository/SettingsRepository.js'

export default {
  name: 'Settings',
  data: () => ({
    selected: null,
    categories: [],
    myCategory: '設定なし',
    api: new SettingsRepository()
  }),
  async created () {
    const { data } = await this.api.retrieveCategory()
    this.categories = data

    const allCategory = data.find(category => { return category.name === '全て' })
    this.selected = allCategory.id
  },
  methods: {
    async onClick () {
      const myCategory = this.categories.find(category => { return category.id === this.selected })
      const { status } = await this.api.registerMyCategory(myCategory.id)

      if (status === 201) {
        this.myCategory = myCategory.name
      }
    }
  }
}
</script>
