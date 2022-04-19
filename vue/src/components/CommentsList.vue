<template>
  <div class="commentsList">
    
    <h3>Comments: </h3>
    <div class="comments">
    <!-- double check key for comment -->
      <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment">
          
            <p>{{ comment.username }}</p>

            <!-- adjust for editComment it works!!!!-->
            <p contenteditable 
                v-if="checkforUser(comment)"
                @input="event=>onInput(event)"
            >{{ comment.text }}</p>
            <p v-else>{{ comment.text }}</p>
            <!-- z edit above -->

            <p>{{comment.date_and_time | formatDate}}</p>
            <edit-comment id="editComment" 
                v-bind:commentUsername="comment.username"
            ></edit-comment>
        </div>
      <!-- <comment-display
      /> -->
    </div>
  </div>
</template>

<script>
import CommentService from "../services/CommentService";
import EditComment from "./EditComment.vue"


export default {
  name: "comment-list",
  components: {
    EditComment,
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
    onInput(event){
      const value = event.target.innerText;
      this.content.value=value;
    }

   
       

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
