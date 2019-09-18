const tailwindcss = require('tailwindcss')
const autoprefixer = require('autoprefixer')
const purgecss = require('@fullhuman/postcss-purgecss')

module.exports = {
  plugins: [
    autoprefixer,
    tailwindcss('./tailwind.config.js'),
    process.env.NODE_ENV === 'production'
      ? purgecss({ content: ['./src/**/*.vue'] })
      : ''
  ]
}
