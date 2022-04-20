<template>
  <div>
    <header>
      <!-- <img
        class="logo"
        src="https://images.squarespace-cdn.com/content/v1/55ef2da9e4b03f6e1ef0cd28/1558034939902-652JBW936829PJQM7K30/Mark.jpg"
      /> -->
      <div class="inner_header">
        <router-link v-bind:to="{ name: 'home'}">

          <img class="logo" src="../../img/mark.png" />
          <div class="logo_container">
            <h1 class="title">Tech Gram</h1>
          </div>
          
        </router-link>

        <div class="loginUsername">
            <h3 class="loginUser" v-if="this.username != ''">Hi, {{this.username}} </h3>
        </div>

      </div>
      
    </header>

    <link href="http://fonts.cdnfonts.com/css/billabong" rel="stylesheet">

    <!-- <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@1,700&display=swap" rel="stylesheet"> -->

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Grand+Hotel&display=swap" rel="stylesheet">

    
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Fredoka+One"
    />
  </div>
</template>

<script>
export default {
    name:  "header-logo",
    data(){
        return{
          username:"",
        }
    },
    created(){
      const UserString= localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
    }
};
</script>

<style>
header {
  display: flex;
  
  text-align: center;
  background-color: #167fa5;
  /* background-color:aliceblue; */
  border-bottom: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
 
  width: 80%;
  height: 80px;
}

.inner_header{
  width: 99%;
  height: 100%;
  display: block;
  margin: 0 auto;
 
}

.logo_container{
  height: 100%;
  display: table;
  float: left;
}

.logo_container h1{
  font-family: 'Billabong', sans-serif;
  /* font-family: 'Montserrat', sans-serif; */
  color: aliceblue;
  height: 100%;
  display: table-cell;
  vertical-align: middle;
  font-size: 55px;
  font-weight: 200;
}

.logo{
  height: 100%;
  display: table-cell;

  vertical-align: middle;
  float:left;
  padding: 0px 0px 0px 10px;

  margin-top: 10px;
  margin-bottom: auto;

  width: 60px;
  height: 60px;

}
.loginUsername{
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: row-reverse;
  margin: 0 0 auto auto;
}
.loginUser{
  display: flex;
  color: aliceblue;
  /* border-bottom: 1px dashed aliceblue; */
  border:1px dashed aliceblue;
  border-radius: 5px;
  padding: 5px;
  margin-right: 20px;
  
}

</style>