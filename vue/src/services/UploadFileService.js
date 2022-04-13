import axios from 'axios';

// change the http address later
const http = axios.create({
    baseURL: "http://localhost:8080",
    headers:{
        "Content-type":"application/json"
    }
  });

export default {
    // double check this method
    // upload(image) {
    //     let formData = new FormData();
    //     formData.append("image", image);
    //     return http.post("/upload", formData, {
    //       headers: {
    //         "Content-Type": "multipart/form-data"
    //       },
          
    //     });
    //   },
      upload(image){
        return http.post(`/images`, image);

      },

      getFiles(){
          return http.get('/images');
      },


}