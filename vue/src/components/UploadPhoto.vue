<template>
  <div class="upload-pic">
    

      <div v-if="!imagePost.image_location">
        <h2>Select an image</h2>
        <div class="wrapper">
          <div class="text">
            No file chosen, yet!

          </div>
        </div>

        <input
          type="file"
          name="file"
          id="file"
          class="inputfile"
          @change="onFileChange"
          hidden
        />
        <button id="custom-btn" v-on:click="defaultBtnActive()">Choose an image</button>
      </div>

      <div v-else class="else">
        
        <div class="image">
            <img id="upload-img" :src="imagePost.image_location" />
        </div>
        
        <div class="caption-container">
          <form v-on:submit.prevent="submitForm">
            <textarea
              class="caption-input"
              placeholder="Write a caption..."
              type="text"
              :caption="imagePost.caption"
              @input="$emit('input', $event.target.value)"
              cols="200"
              rows="10"
              name="textarea"
            ></textarea>

            <br />
            <button id="cancel" @click="removeImage">Cancel</button> &nbsp;
            <button id="upload" type="submit">Upload</button>
          </form>
        </div>
      </div>
    
  </div>
</template>

<script>
import PhotoService from "../services/PhotoService";

export default {
  name: "upload-photo",

  data() {
    return {
      imagePost: {
        caption: "",
        image_location: "",
      },
      photoCount: "1",
      photo: "Photo",
      fileName: "",
      username: ""
    };
  },
  beforeMount() {
    let vm = this;
    vm.get("img");
    // before mount displays the image when the page loads
    // line 49 is to display nothing when page loads
    this.imagePost.image_location = "";

    const UserString= localStorage.getItem("user");
     let firstIndex = UserString.indexOf("username");
     let secondIndex = UserString.indexOf("authorities");
     this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
  },

  methods: {
    submitForm() {
      this.imagePost.caption = document.querySelector("textarea").value;
    
      // this.imagePost.image_location = this.image;
      if (localStorage.getItem("PhotoCount")) {
        this.photoCount = localStorage.getItem("PhotoCount");
        let intCount = parseInt(this.photoCount);
        intCount++;
        this.photoCount = intCount;
        localStorage.removeItem("PhotoCount");
        localStorage.setItem("PhotoCount", this.photoCount);
        let generatedName = this.photo + this.photoCount;
        // console.log(generatedName)
        localStorage.setItem(generatedName, JSON.stringify(this.imagePost));
      } else {
        localStorage.setItem("PhotoCount", this.photoCount);
        let generatedName = this.photo + this.photoCount;
        // console.log(generatedName)
        localStorage.setItem(generatedName, JSON.stringify(this.imagePost));
      }
      PhotoService.addPhoto(this.imagePost).then((response) => {
        if (response.status === 201) {
          this.image = "";
          this.imagePost.caption = "";
          this.imagePost.image_location = "";
          localStorage.removeItem("img");
          this.$router.push({name: "userPage", params: {username: this.username} });
        }
      });
    },
    get(key) {
      this.imagePost.image_location = localStorage.getItem(key);
    },

    set(key) {
      // let vm = this
      try {
        localStorage.setItem(key, this.imagePost.image_location);
      } catch (e) {
        console.log(`Storage failed: ${e}`);
      }
    },

    onFileChange(e) {
      let files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.createImage(files[0]);
    },

    createImage(file) {
      let reader = new FileReader();
      // The vm in this case means viewmodel, its a shortcut for writing this.someMethod() so that you can use vm.someMethod()
      let vm = this;

      reader.onload = (e) => {
        vm.imagePost.image_location = e.target.result;
        vm.set("img");
      };
      reader.readAsDataURL(file);
    },
    removeImage() {
      this.imagePost.image_location = "";
      localStorage.removeItem("img");
    },
    

    defaultBtnActive(){
        const defaultBtn = document.querySelector("#file");
        // const customeBtn = document.querySelector("#custom-btn");
        defaultBtn.click();
    },
/*    upload() {
      // this.progress = 0;

      UploadFileService.upload(this.imagePost)

        .then((response) => {
          if (response.status === 201) {
            return UploadFileService.getFiles();
          }
        })

        .catch((err) => {
          this.message = "Could not upload the image! " + err;
          this.imagePost.imageLocation = undefined;
        });
    }, */
  },
};
</script>

<style>

h2{
  margin-top: 40px;
  color:#3b3b3b;
}

.upload-pic{
  margin: 0;
  padding: 0;
  box-shadow: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

.wrapper{
  margin-top: 40px;
  margin-bottom: 40px;
  /* display: grid; */
  /* height: 80%; */
  place-items: center;
  text-align: center;

  position: relative;
  height: 350px;
  width: 600px;
  border-radius: 10px;
  background: rgb(233, 233, 233);
  border: 3px dashed #a4a4a5;
  
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.wrapper .text{
  font-size: 20px;
  font-weight: 500;
  color: #5B5B7B;
}

.caption-container button{
  margin-top: 30px;
  /* display: flex;
  flex-direction: row; */
  justify-content: center;
  text-align: center;
  margin: auto;
  align-items: center;
  
  width: 10%;
  height: 50px;
  border: none;
  outline: none;
  border-radius: 25px;

  border: 1px solid rgba(0, 0, 0, 0.61);
  /* border-radius: 10px; */
  
  font-size: 18px;
  font-weight: 500;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;
}

#cancel,
#upload{
 margin:20px 40px 0 40px;
}

#cancel:hover,
#upload:hover{
  color: #167fa5;
}

#custom-btn{
  margin-top: 30px;
  /* display: block; */
  width: 40%;
  height: 50px;
  border: none;
  outline: none;
  border-radius: 25px;
  background-color: aliceblue;

  border: 1px solid rgba(0, 0, 0, 0.61);
  /* border-radius: 10px; */
  
  font-size: 18px;
  font-weight: 500;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;

}

#custom-btn:hover{
  color: #167fa5;
}

textarea {
  width: 30%;
  border-radius: 10px;
  padding: 5px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.upload-pic {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  width: 80%;
}

#upload-img{
  margin: 30px auto 20px -10px;
  display: block;
  
  width:100%;  
  padding: 10px;
  border-radius: 10px;
  background: rgb(233, 233, 233);
  border: 3px dashed #a4a4a5;
  }


</style>