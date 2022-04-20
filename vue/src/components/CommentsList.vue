<template>
  <div class="commentsList">
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />

    <h3 id="commentHeader">Comments:</h3>
    <div class="comments2">
      <!-- double check key for comment -->
      <div
        v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment"
        id="commentDiv"
      >
        <div id="userAndDate">
          <router-link
            v-bind:to="{
              name: 'userPage',
              params: { username: comment.username },
            }"
            v-if="checkUser(comment)"
          >
            <div id="commentUsername">{{ comment.username }}</div>
          </router-link>

          <router-link
            v-bind:to="{
              name: 'userFeed',
              params: { username: comment.username },
            }"
            v-if="!checkUser(comment)"
          >
            <div id="commentUsername">{{ comment.username }}</div>
          </router-link>

          <p id="commentDate">
            Posted on {{ comment.date_and_time | formatDate }}
          </p>
        </div>

        <div class="commentList">
          <div class="commentText">
            <p
              contenteditable
              id="editor"
              v-if="checkforUser(comment)"
              @blur="(event) => onInput(event, comment.comment_id, comment)"
            >
              {{ comment.text }}
            </p>
            <p v-else>{{ comment.text }}</p>
          </div>

          <div class="icon">
            <span
              class="material-icons"
              id="editComment"
              v-if="checkforUser(comment)"
              v-on:click="editCommentActive(comment)"
            >
              edit_note
            </span>
            <span
              class="material-icons"
              id="editComment"
              v-if="checkforUser(comment)"
              v-on:click="deleteThisComment(comment.comment_id)"
            >
              clear
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommentService from "../services/CommentService";


export default {
  name: "comment-list",

  components: {
  
  },
  data() {
    return {
      yourComment: false,
      username: "",
      newComment: {
        comment_id: "",
        text: "",
      },
    };
  },
  created() {
    this.getCommentsByPhotoId();
  },
  mounted() {
    this.getCommentsByPhotoId();
  },
  methods: {
    deleteThisComment(id) {
      if (
        confirm(
          "Are you sure you want to delete this comment? This action cannot be undone."
        )
      ) {
      CommentService.deleteComment(id).then((response) => {
        if (response.status  === 200) {
          alert("Comment successfully removed")
          this.getCommentsByPhotoId();
        }
      })
      .catch((error) => {
            if (error.response) {
              this.errorMsg =
                "Error deleting comment. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting comment. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting comment. Request could not be created.";
            }
          });
      }
    },
    getCommentsByPhotoId() {
      CommentService.getCommentsByPhotoID(this.$route.params.photoId).then(
        (response) => {
          this.$store.commit("SET_COMMENTS", response.data);
        }
      );
    },
    checkUser(comment) {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == comment.username) {
        return (this.isUser = true);
      } else {
        return (this.isUser = false);
      }
    },
    checkforUser(comment) {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == comment.username) {
        return (this.yourComment = true);
      }
    },

    onInput(event, commentId, comment) {
      const value = event.target.innerText;
      this.newComment.text = value;
      this.newComment.comment_id = commentId;

      if (this.newComment.text != comment.text) {
        CommentService.updateComment(this.newComment)
          .then((response) => {
            if (response.status === 200) {
              alert("Comment edit successful!");
              this.getCommentsByPhotoId();
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "updating");
          });
      }
    },

    editCommentActive(comment) {
      const editComment2 = document.querySelectorAll("#editor");
      console.log(editComment2);
      editComment2.forEach((element) => {
        console.log(element.innerText);
        if (comment.text == element.innerText) {
          element.focus();
        }
      });
    },
  },
};
</script>

<style>
#commentText {
  margin-bottom: 0px;
  margin-top: 0px;
}
#editor {
  padding: 5px;
}
#userAndDate {
  display: flex;
  justify-content: space-between;
  width: 95%;
  height: 15px;
}
.commentList {
  display: flex;
  flex-direction: row;
}
#commentDiv {
  padding-top: 20px;
  border-bottom-style: solid;
  border-bottom-color: rgb(189, 159, 159);
  border-bottom-width: 1px;
  padding-left: 20px;
  height: 70%;
}
#commentUsername {
  color: #00adee;
}
div.main {
  margin: 1rem 0;
}
.comments2 {
  /* background-color: rgb(209, 208, 208); */
  background-color: #efe3ef;
  border: 4px solid rgb(253, 168, 168);
  border-radius: 20px;
}
h3 {
  margin: auto;
  font-weight: normal;
}
#commentHeader {
  color: white;
  font-size: 20px;
}
#commentDate {
  margin-top: 0;
}

#editComment {
  color: rgba(0, 0, 0, 0.54);
  cursor: pointer;
}
#editComment:hover {
  color: rgba(0, 0, 0, 0.904);
}
.commentText {
  width: 95%;
}
.icon {
  display: flex;
  align-items: center;
}
</style>
