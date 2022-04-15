<template>
  <div>
    <form v-on:submit.prevent="addNewComment">
     
      <div class="form-element">
        <label for="comment">comment:</label>
        <textarea id="comment" v-model="newComment.comment"></textarea>
      </div>
      <input type="submit" value="Save" />
      <input type="button" value="Cancel" v-on:click.prevent="resetForm" />
    </form>
  </div>
</template>

<script>
import CommentService from "../services/CommentService";

export default {
  name: "add-comment",
  data() {
    return {
      newComment: {
        photo_id: " ",
        text: " "
      }
    };
  },
  methods: {
    addNewcomment() {
              CommentService.addComment(this.newComment).then(response => {     
                   this.$store.commit("ADD_COMMENT", response.data);
});
      this.resetForm();
    },
    resetForm() {
      this.newComment = {};
    }
  }
};
</script>

<style>
div.form-element {
  margin-top: 10px;
}
div.form-element > label {
  display: block;
}
div.form-element > input,
div.form-element > select {
  height: 30px;
  width: 300px;
}
div.form-element > textarea {
  height: 60px;
  width: 300px;
}
form > input[type="button"] {
  width: 100px;
}
form > input[type="submit"] {
  width: 100px;
  margin-right: 10px;
}
</style>
