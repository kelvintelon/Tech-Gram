<template>
  <div class="commentsList">
    <h3>Comments: </h3>
    <div class="comments">
    <!-- double check key for comment -->
      <comment-display
        v-for="comment in this.$store.state.pictureDetails.comments"
        v-bind:key="comment.id"
        v-bind:comment="comment"
      >{{comment.username}}</comment-display>
    </div>
  </div>
</template>

<script>
import CommentDisplay from "./CommentDisplay";

export default {
  name: "comment-list",
  components: {
    CommentDisplay
  },
  computed: {
    comments() {
      return this.$store.state.pictureDetails.comments;
      }
  }
};
</script>

<style>
div.main {
  margin: 1rem 0;
}
.comments{
  background-color: rgb(209, 208, 208);
}

</style>
