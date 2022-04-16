<template>
  <div class="comment" v-bind:key="comment.id">
    <!-- double check comment.commenter -->
    <h4>{{ comment.username }}</h4>
    <p>{{ comment.text }}</p>
  </div>
</template>

<script>
export default {
  name: "comment-display",
  props: ["comment"],
 
};
</script>

<style>
div.main div.comment {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
}

div.main div.comment p {
  margin: 20px;
}

div.main div.comment h4 {
  font-size: 1rem;
}


</style>
