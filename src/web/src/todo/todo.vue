<template>
  <div id="main-app">
    <section class="real-app">
      <input class="add-input" type="text" autofocus="autofocus" placeholder="接下来要去做什么？" @keyup.enter="addTodo" v-model="content">
      <div class="todo-items">
        <Item v-for="(todo, index) in todoList" :key="index" v-if="!todo.removed" :todo="todo"></Item>
      </div>
    </section>
  </div>
</template>

<script>

import Item from "./item.vue"

export default {
  data() {
    return {
      content: null,
      generator: 0,
      todoList: [
        {
          id: 0,
          content: "2018-06-25 5:30 项目组日常会议",
          completed: true,
          removed: false
        },
        {
          id: 1,
          content: "2018-06-26 11:30 提交 Git，准备 Code Review",
          completed: false,
          removed: false
        },
      ]
    }
  },
  components: {
    Item
  },
  methods: {
    addTodo: function() {
      const todo = {
        id: this.generator,
        content: this.content,
        completed: false,
        removed: false
      }
      this.todoList.push(todo)
      this.generator++
      this.content = ""
    }
  },
  mounted: function() {
    this.content = ""
    if (this.generator && this.generator.length) {
      let max = todoList.slice(-1)[0].id
      this.generator = (max + 1)
    } else {
      this.generator = 0
    }
  }
}
</script>

<style lang="stylus" scoped>
.real-app {
  margin 0 auto
  width 600px
  box-shadow 0 0 5px #666
}
.add-input {
  position relative
  margin 0
  padding 16px 16px 16px 60px
  width 100%
  line-height 1.4em
  font-size 24px
  font-smoothing antialiased
  outline none
  border none
  box-sizing border-box
  box-shadow inset 0 -2px 1px rgba(0, 0, 0, .3)
}
</style>
