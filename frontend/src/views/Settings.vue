<template>
  <div>
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
    <p
      v-if="myCategory"
      id="settings__my-category"
    >
      現在の設定を {{ myCategory }} にしました！
    </p>
  </div>
</template>

<script>
import SettingsRepository from '@/repository/SettingsRepository.js'

export default {
  name: 'Settings',
  data: () => ({
    selected: null,
    categories: [],
    myCategory: null,
    api: new SettingsRepository()
  }),
  async created () {
    const [{ data: categories }, { data: myCategory }] = await Promise.all([ this.api.retrieveCategories(), this.api.retrieveMyCategory() ])

    this.categories = categories
    this.selected = myCategory.id
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
