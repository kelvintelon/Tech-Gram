<template>
  <div class="comments">
    <!-- double check key for comment -->
    <comment-display
      v-for="comment in comments"
      v-bind:key="comment.id"
      v-bind:comment="comment"
    />
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
div.main div.well-display {
  display: flex;
  justify-content: space-around;
}

div.main div.well-display div.well {
  display: inline-block;
  width: 15%;
  border: 1px black solid;
  border-radius: 6px;
  text-align: center;
  margin: 0.25rem;
}

div.main div.well-display div.well span.amount {
  color: darkslategray;
  display: block;
  font-size: 2.5rem;
}

div.main div.well-display div.well {
  cursor: pointer;
}
</style>
