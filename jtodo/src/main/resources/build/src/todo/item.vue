<template>
  <div :class="['todo-item', todo.completed ? 'completed' : '']">
    <input class="toggle-input" type="checkbox" name="toggle-input" v-model="todo.completed">
    <label class="todo-label ellipsis" for="toggle-input" :title="todo.content">{{todo.content}}</label>
    <button class="destroy" @click="deleteTodo"></button>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  props: {
    todo: {
      type: Object,
      required: true
    }
  },
  methods: {
    deleteTodo: function() {
      if (this.todo) {
        this.todo.removed = true
      }
    }
  }
}
</script>

<style lang="stylus" scoped>

.todo-item {
  position relative
  height 45px
  font-size 24px
  background-color #FFF
  border-bottom 1px solid rgba(0, 0, 0, 0.06)
  &:hover {
    .destroy:after {
      content: '×'
    }
  }
  .todo-label {
    display block
    margin-left 45px
    padding 10px 60px 10px 10px
    line-height 1.2
    word-break break-all
    transition color 0.4s
  }
  &.completed {
    .todo-label {
      color #D9D9D9
      text-decoration line-through
    }
  }
}

.toggle-input {
  border-radius 2px
  border 1px solid #C9C9C9
  background-color #FFF
  outline none
  appearance none
}

.toggle-input {
  margin auto 0
  position absolute
  top 0
  bottom 0
  width 40px
  height 40px
  text-align center
  border none
  outline none
  &:after {
    position: relative;
    top: 8px;
    content url("../assets/image/round.png")
  }
  &:checked:after {
    position: relative;
    top: 8px;
    content url("../assets/image/done.svg")
  }
}

.destroy {
  margin auto 0
  position absolute
  top 0px
  right 10px
  bottom 0px
  width 40px
  height 40px
  font-size 30px
  color #CC9A9A
  transition color 0.2s easy-out
  background-color transparent
  appearance none
  border-width 0
  outline none
  cursor pointer
}
</style>
