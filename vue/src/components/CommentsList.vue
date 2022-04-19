<template>
  <div class="commentsList">
    
    <h3>Comments: </h3>
    <div class="comments">
    <!-- double check key for comment -->
      <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment">
          
            <p>{{ comment.username }}</p>

            <p contenteditable 
                v-if="checkforUser(comment)"
                @blur="event=>onInput(event,comment.comment_id)"
            >{{ comment.text }}</p>
            <p v-else>{{ comment.text }}</p>

            <p>{{comment.date_and_time | formatDate}}</p>

        </div>
      <!-- <comment-display
      /> -->
    </div>
  </div>
</template>

<script>
import CommentService from "../services/CommentService";
// import EditComment from "./EditComment.vue"


export default {
  name: "comment-list",
  components: {
    // EditComment,
    // CommentDisplay
  },
  data(){
        return{
            yourComment: false,
            username: "",
            newComment:{
                comment_id:"",
                text:"",
            }
        }
    },
  created(){
      this.getCommentsByPhotoId();
  },
  mounted(){
    this.getCommentsByPhotoId();
    

  },
  methods:{
    getCommentsByPhotoId(){
      CommentService.getCommentsByPhotoID(this.$route.params.photoId)
                    .then((response) =>{
                      this.$store.commit("SET_COMMENTS", response.data)
                    })  
    },

    checkforUser(comment){
            const UserString = localStorage.getItem("user");
            let firstIndex = UserString.indexOf("username");
            let secondIndex = UserString.indexOf("authorities");
            this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
            if (this.username == comment.username){
                return this.yourComment=true;
            } 
    },

    onInput(event,commentId){
      const value = event.target.innerText;
      this.newComment.text = value;
      this.newComment.comment_id = commentId;
      CommentService.updateComment(this.newComment)
                    .then((response)=>{
                      if (response.status === 200) {
                        alert("Comment edit successful!");
                        location.reload();
                    }
                    })
                    .catch((error) => {
                    this.handleErrorResponse(error, "updating");
          });
    },

  }
};
</script>

<style>
div.main {
  margin: 1rem 0;
}
.comments{
  /* background-color: rgb(209, 208, 208); */
  background-color:#efe3ef;
}
h3{
  margin: auto;
  font-weight: normal;
}
</style>
