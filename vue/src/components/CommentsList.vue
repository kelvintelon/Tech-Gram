<template>
  <div class="commentsList">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"/>

    <h3 id="commentHeader">Comments: </h3>
    <div class="comments2">
    <!-- double check key for comment -->
      <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment"
        id="commentDiv">
      <div id="userAndDate">
    <router-link
        v-bind:to="{ name: 'userPage', params: { username: comment.username } }" v-if="checkUser(comment)"
      >
        <div id="commentUsername">{{ comment.username }}</div>
      </router-link>

      <router-link
        v-bind:to="{ name: 'userFeed', params: { username: comment.username } }" v-if="!checkUser(comment)"
      >
        <div id="commentUsername">{{ comment.username }}</div>
      </router-link>

      <p id="commentDate">Posted on {{comment.date_and_time | formatDate}}</p>
      </div>

      <div class="commentList">
          <div class="commentText">
                <p contenteditable 
                    v-if="checkforUser(comment)"
                    @blur="event=>onInput(event,comment.comment_id,comment)"
                >{{ comment.text }}
                    
                </p>
                <p v-else>{{ comment.text }}</p>

          </div>

          <div class="icon">
                <span class="material-icons" id="editComment" v-if="checkforUser(comment)">
                    edit_note
                </span>
          </div>

      </div>
            
     
            
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
    checkUser(comment){
      
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == comment.username){
        return this.isUser=true;
      }else{
        return this.isUser=false;
      }
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

    onInput(event,commentId,comment){
      const value = event.target.innerText;
      this.newComment.text = value;
      this.newComment.comment_id = commentId;

      if (this.newComment.text!=comment.text){
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
      }
     
    },

  }
};
</script>

<style>
#commentText {
  margin-top: 0;

}
#userAndDate{
 display: flex;
 justify-content: space-between;
 width: 95%;
}
#commentUsername {
  color:#00adee;
}
div.main {
  margin: 1rem 0;
}
.comments2{
  /* background-color: rgb(209, 208, 208); */
  background-color:#efe3ef;
  border: 4px solid rgb(253, 168, 168);
  border-radius: 20px;
}
h3{
  margin: auto;
  font-weight: normal;
}
#commentDiv {
  padding-top: 20px;
  border-bottom-style: solid;
  border-bottom-color:rgb(189, 159, 159) ;
  border-bottom-width: 1px;
  padding-left: 20px;
}
#commentHeader {
  color: white;
  font-size: 20px;
}
#commentDate {
  margin-top: 0;
}

#editComment{
    color: rgba(0, 0, 0, 0.54);
    cursor: pointer;
}
#editComment:hover{
    color: rgba(0, 0, 0, 0.904);
}

.commentList{
  display: flex;
  flex-direction: row;
}
.commentText{
  width: 95%;
}
.icon{
  display: flex;
  align-items: center;
  

}
</style>
