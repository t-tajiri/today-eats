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
        @registerEats="registerEats"
        @updateEats="updateEats"
        @deleteEats="deleteEats"
      />
    </div>
    <Notification
      :is-notified="isNotified"
      :is-valid="isValid"
      :error-messages="errorMessages"
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
const ERROR_BAD_REQUEST = 400

function notifySuccess (vm) {
  vm.isNotified = true
  setTimeout(() => {
    vm.isNotified = false
  }, 3500)
}

function notifyError (vm) {
  if (!vm.errorMessages) {
    vm.errorMessages.push('エラーが発生しました。しばらくしてから再度お試しください。')
  }

  vm.isValid = false
  setTimeout(() => {
    vm.isValid = true
  }, 3500)
}

function validate (vm, targetIndex) {
  vm.errorMessages = []

  const name = vm.eats[targetIndex].name
  const categoryId = vm.eats[targetIndex].category_id

  if (name && categoryId) {
    return true
  }

  let valid = true

  if (!name) {
    vm.errorMessages.push('ご飯の名前を入力してください')
    valid = false
  }
  if (!categoryId) {
    vm.errorMessages.push('ジャンルを設定してください')
    valid = false
  }

  return valid
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
    errorMessages: [],
    formCategories: [],
    isNotified: false,
    isValid: true,
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
        notifySuccess(this)
      } else if (status === ERROR_BAD_REQUEST) {
        notifyError(this)
      }
    },
    async registerEats (targetIndex) {
      this.isNotified = false
      this.isValid = true

      const valid = validate(this, targetIndex)
      if (!valid) {
        this.isValid = false
        notifyError(this)
        return
      }

      const { status, headers } = await this.api.registerEats(this.eats[targetIndex])

      if (status === SUCCESS_RESPONSE_CREATED) {
        const id = headers.location.substr(headers.location.lastIndexOf('/') + 1)
        this.eats[targetIndex].id = id
        this.eats.push({ id: '', name: '', category_id: '' })
        notifySuccess(this)
      } else if (status === ERROR_BAD_REQUEST) {
        notifyError(this)
      }
    },
    async updateEats (targetIndex) {
      this.isNotified = false
      this.isValid = true

      const valid = validate(this, targetIndex)
      if (!valid) {
        this.isValid = false
        notifyError(this)
        return
      }

      const { status } = await this.api.updateEats(this.eats[targetIndex])

      if (status === SUCCESS_RESPONSE_NO_CONTENT) {
        notifySuccess(this)
      } else if (status === ERROR_BAD_REQUEST) {
        notifyError(this)
      }
    },
    async deleteEats (targetIndex) {
      this.isNotified = false
      const id = this.eats[targetIndex].id
      const { status } = await this.api.deleteEats(id)

      if (status === SUCCESS_RESPONSE_NO_CONTENT) {
        this.eats = this.eats.filter(eat => eat.id !== id)
        notifySuccess(this)
      } else if (status === ERROR_BAD_REQUEST) {
        notifyError(this)
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
