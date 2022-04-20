<template>
<div>
    <h1>Logout</h1>
    <div class="bg-image">

    </div>
    <!-- <img class="logoutImg" src="../../img/logout image2.png" /> -->
</div>
  
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/");
  }
};
</script>

<style>
.bg-image{
  background-image: src="../../img/logout image2.png" ;

}
</style>
