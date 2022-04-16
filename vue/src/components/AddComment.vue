<template>
  <div>
    <form v-on:submit.prevent="addNewComment" class="addComment">
     
      <!-- <div class="form-element"> -->
        <!-- <label for="comment">Comment:</label> -->
        <textarea id="comment" type="text" name="textarea" placeholder=" Add a comment..." 
            @input="$emit('input', $event.target.value)"
            v-model="newComment.text" ></textarea>
      <!-- </div> -->
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
        text: "",
      }
    };
  },
  methods: {
    addNewComment() {
      this.newComment.text = document.querySelector("textarea").value;
      this.newComment.photo_id = this.$store.state.pictureDetails.photo_id;

      CommentService.addComment(this.newComment).then(response => {     
         this.$store.commit("ADD_COMMENT", response.data);
      console.log(response.data);
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
.addComment{
  display: grid;
  justify-content: center;
  align-content: center;

  gap: 4px;
  grid-auto-flow: column;
}
/* div.form-element {
  margin-top: 10px;
} */
label {
  display: block;
}
input,
select {
  height: 30px;
  width: 300px;
}
/* edit here */
#comment {
  height: 20px;
  width: 600px;
  border-radius: 50px;
  padding: 20px;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  
}
form > input[type="button"] {
  margin: 10px 0px 10px 20px;
  
}
form > input[type="submit"] {
  
  margin: 10px 10px 10px 40px;
 
  /* margin-right: 10px; */
  
}
input[type="button"],
input[type="submit"]
{
  width: 120px;
  height: 50px;
  border: none;
  outline: none;
  border-radius: 25px;
  font-weight: bold;

  border: 1px solid rgba(0, 0, 0, 0.61);
  
  font-size: 16px;
  font-weight: 500;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;

}


</style>
