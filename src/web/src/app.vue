<template>
  <div id="app">
    <!-- The cover of background -->
    <div id="cover"></div>

    <!-- The Header of page -->
    <Header></Header>

    <!-- The main content of page -->
    <Todo></Todo>

    <!-- The footer of page -->
    <Footer></Footer>
  </div>
</template>

<script>

import Header from "./todo/header.vue"
import Todo from "./todo/todo.vue"
import Footer from "./todo/footer.jsx"

export default {
  data() {
    return {
      _meta_title: "Todo-List"
    }
  },
  components: {
    Header,
    Todo,
    Footer
  }
}
</script>

<style lang="stylus" scoped>
#app {
  position absolute
  top 0
  left 0
  right 0
  bottom 0
}

#cover {
  position absolute
  top 0
  left 0
  right 0
  bottom 0
  background-color #999
  opacity .4
  z-index -1
}
</style>

