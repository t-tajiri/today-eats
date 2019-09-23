<template>
  <div>
    <div class="flex mt-10 m-auto w-11/12">
      <h2 class="text-xl">
        ご飯の設定
      </h2>
    </div>
    <div class="flex mt-10 m-auto w-11/12 justify-center">
      <div class="table w-10/12">
        <div
          class="table-row"
          :class="index % 2 === 0 ? 'bg-gray-400' : 'bg-gray-200'"
          :key="eat.id"
          v-for="(eat, index) in eats"
        >
          <div class="table-cell px-4 py-2 text-sm">
            <input
              class="shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              :id="'settings__eats-name-' + (index + 1)"
              type="text"
              placeholder="ご飯"
              v-model="eat.name"
            >
          </div>
          <div class="table-cell px-4 py-2">
            <select
              class="px-5 py-2 border rounded w-full appearance-none focus:outline-none focus:shadow-outline"
              :id="'settings__eats-category-' + (index + 1)"
              v-model="eat.category_id"
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
                @click="deleteEats(index)"
                class="bg-red-500 hover:bg-red-700 text-white font-bold"
                :id="'settings__eats-delete-' + (index + 1)"
                name="削除"
              />
            </div>
          </div>
          <div
            v-else
            class="flex justify-center"
          >
            <div class="table-cell py-2 text-center">
              <Button
                @click="registerEats(index)"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold"
                :id="'settings__eats-register'"
                name="登録"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Button from '@/components/Button.vue'

export default {
  name: 'Eats',
  components: {
    Button
  },
  props: {
    eats: {
      type: Array,
      required: true
    },
    formCategories: {
      type: Array,
      required: true
    }
  },
  methods: {
    registerEats (index) {
      this.$emit('registerEats', index)
    },
    updateEats (index) {
      this.$emit('updateEats', index)
    },
    deleteEats (index) {
      this.$emit('deleteEats', index)
    }
  }
}
</script>
