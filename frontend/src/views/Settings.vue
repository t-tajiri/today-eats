<template>
  <div class="flex">
    <div class="w-8/12 mt-10 m-auto">
      <h1 class="text-4xl">
        設定
      </h1>
      <hr class="solid">
      <div class="flex mt-10 m-auto w-11/12">
        <div class="inline-flex w-9/12">
          <h2 class="text-xl">
            好みのジャンル設定
          </h2>
        </div>
        <div class="inline-flex w-2/12">
          <select
            class="border rounded w-full appearance-none focus:outline-none focus:shadow-outline"
            id="settings__categories"
            v-model.lazy="selected"
          >
            <option
              class="p-16"
              :key="category.id"
              :value="category.id"
              v-for="category in categories"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
        <div class="inline-flex w-1/12 justify-end">
          <Button
            @click="registerCategory"
            class="bg-blue-500 hover:bg-blue-700 text-white font-bold"
            name="登録"
          />
        </div>
      </div>
      <div class="flex mt-10 m-auto w-11/12">
        <div>
          <h2 class="text-xl">
            ご飯の設定
          </h2>
        </div>
      </div>
      <div class="flex mt-10 m-auto w-11/12 justify-center">
        <div class="table w-10/12">
          <div
            class="table-row"
            :class="index % 2 === 0 ? 'bg-gray-400' : 'bg-gray-200'"
            :key="eat.name"
            v-for="(eat, index) in eats"
          >
            <div class="table-cell px-4 py-2 text-sm">
              <input
                class="shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                :id="'settings__eats-name-' + (index + 1)"
                type="text"
                placeholder="ご飯"
                v-model.lazy="eat.name"
              />
            </div>
            <div class="table-cell px-4 py-2">
              <select
                class="px-5 py-2 border rounded w-full appearance-none focus:outline-none focus:shadow-outline"
                :id="'settings__eats-category-' + (index + 1)"
                v-model.lazy="eat.category_id"
              >
                <option
                  :key="category.id"
                  :value="category.id"
                  v-for="category in formCategories"
                >
                  {{ category.name }}
                </option>
              </select>
            </div>
            <div
              v-if="eat.id"
              class="flex justify-center"
            >
              <div class="table-cell mr-4 py-2 text-center">
                <Button
                  @click="updateEats(index)"
                  class="bg-teal-500 hover:bg-teal-700 text-white font-bold"
                  :id="'settings__eats-update-' + (index + 1)"
                  name="変更"
                />
              </div>
              <div class="table-cell py-2 text-center">
                <Button
                  class="bg-red-500 hover:bg-red-700 text-white font-bold"
                  :id="'settings__eats-delete-' + (index + 1)"
                  name="削除"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <transition
      name="toast"
      mode="out-in"
    >
      <div
        class="flex fixed right-0 w-3/12 m-8 py-2 items-center rounded bg-blue-500 text-white text-sm font-bold"
        role="alert"
        v-if="isNotified"
      >
        <v-icon
          class="mx-2"
          name="info-circle"
          scale="1.25"
        />
        <p
          id="settings__notification"
        >
          変更を保存しました！
        </p>
      </div>
    </transition>
  </div>
</template>

<script>
import SettingsRepository from '@/repository/SettingsRepository.js'
import Button from '@/components/Button.vue'

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
    Button
  },
  data: () => ({
    api: new SettingsRepository(),
    eats: [],
    categories: [],
    formCategories: [],
    isNotified: false,
    myCategory: null,
    selected: null,
    name: [],
    category: []
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
    async registerCategory () {
      this.isNotified = false
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
.toast-enter-active, .toast-leave-active {
  transition: all .3s ease;
}

.toast-enter, .toast-leave-to {
  transform: translateX(20px);
  opacity: 0;
}
</style>
