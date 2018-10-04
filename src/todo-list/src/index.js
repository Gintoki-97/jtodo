// This is the entry file.

import Vue from "vue"
import App from "./app.vue"

import "./assets/image/bg-lg.jpg"
import "./assets/image/favicon.ico"
import "./assets/style/static.css"
import "./assets/style/static.styl"

let root = document.createElement("div")
document.body.appendChild(root)

new Vue({
  render: (h) => h(App),
}).$mount(root)