<template>
  <div>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"/>
      
      <span class="material-icons" id="editComment">
                edit_note
      </span>
      <button>Edit Comment</button>
  </div>
</template>

<script>
export default {
    name:"edit-comment",


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