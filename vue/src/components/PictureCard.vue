<template>
  <div class="container">
    <div class="picture">
      <img
        src="https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg?resize=2048,1339"
      />
    </div>
    <div class="likes">3</div>
    <div class="comments">Comments:</div>
  </div>
</template>

<script>
export default {
  name: "picture-card",
};
</script>
#B5C9D5
<style>
.container {
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 250px;
  height: 550px;
  margin: 20px;
  padding: 10px;
}
.likes:before {
  content: "❤️";
}
img {
  width: 80%;
  height: 80%;
  margin: 20px;
  margin-bottom: 10px;
}
.likes,
.comments {
  margin-bottom: 10px;
}
</style>