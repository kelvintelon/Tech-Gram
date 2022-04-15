<template>
  <div>
    <form v-on:submit.prevent="addNewComment">
     
      <div class="form-element">
        <label for="comment">Comment:</label>
        <textarea id="comment" type="text" name="textarea"  
            @input="$emit('input', $event.target.value)"
            v-model="newComment.text" ></textarea>
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
        photo_id: "",
        text: ""
      }
    };
  },
  methods: {
    addNewComment() {
      this.newComment.text = document.querySelector("textarea").value;
      this.newComment.photo_id = this.$store.state.pictureDetails.photo_id;
      CommentService.addComment(this.newComment).then(response => {     
         this.$store.commit("ADD_COMMENT", response.data);
      console.log(this.newComment.text);
});
      this.resetForm();
    },
    resetForm() {
      this.newComment.photo_id= "";
      this.newComment.text="";
    }
  },
  
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
