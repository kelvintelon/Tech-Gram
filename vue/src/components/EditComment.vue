<template>
  <div>
      
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"/>
      
      
      <span class="material-icons" id="editComment" v-if="this.showCommentIcon">
            edit_note
      </span>
      <!-- <button>Edit Comment</button> -->
  </div>
</template>

<script>
export default {
    name:"edit-comment",
    props: ["commentUsername"],
    data(){
        return{
            yourComment: true,
            username: "",
            showCommentIcon: false,
            newComment:{
                comment_id:"",
                text:"",
            }
        }
    },
    methods:{
        checkforUser(commentUsername){
            const UserString = localStorage.getItem("user");
            let firstIndex = UserString.indexOf("username");
            let secondIndex = UserString.indexOf("authorities");
            this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
            console.log(this.username);
            console.log(commentUsername);
            if (this.username == this.commentUsername){
                this.yourComment=true;
                this.showCommentIcon = true;
            } 
        },
        saveNewComment(){

        }

    },
    mounted(){
         this.checkforUser(this.commentUsername);
    }


}
</script>

<style>
#editComment{
    color: rgba(0, 0, 0, 0.54);
    cursor: pointer;
}
#editComment:hover{
    color: rgba(0, 0, 0, 0.904);
}



</style>