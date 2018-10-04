import "../assets/style/footer.styl"

export default {
  data() {
    return {
      author: "Jesus"
    }
  },
  render() {
    return (
      <div id="main-footer">
        <span>{this.author} 2018.06 Inc.</span>
      </div>
    )
  }
}