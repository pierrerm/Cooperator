import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-3417.herokuapp.com/'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  data() {
    return {
      students: [],
      student: '',
      forms: [],
    }
  },

  created: function() {
    AXIOS.get('/students')
      .then(response => {
        this.students = response.data;
        // Find student by userId
        for (var i in this.students) {
          if (this.students[i].userId === this.$route.params.userId) {
            this.student = this.students[i]

            AXIOS.get('/forms/student/byId/'+ this.student.userId)
              .then(response => {
                this.forms = response.data;
              })
          }
        }
      })

    if ((localStorage.getItem('loggedIn') != null)) {
      //if cookies expired, refresh
      if (this.$cookie.get("username") == null || this.$cookie.get("password") == null) {
        localStorage.removeItem('loggedIn')
        this.$cookie.delete('username');
        this.$cookie.delete('password');
        window.location.href = "/";
      }
      else {
        //reverify login information
        AXIOS.post(`/login/` + this.$cookie.get("username") + '/' + this.$cookie.get("password"), {}, {})
          .then(response => {
            if (response.data == 'Accepted') {
              if(localStorage.getItem('loggedIn') != "Administrator"){
                localStorage.setItem('loggedIn', "Administrator");
                window.location.href = "/";
              }

            }
            else {
              localStorage.removeItem('loggedIn')
              this.$cookie.delete('username');
              this.$cookie.delete('password');
              console.log("bad log in information");
              window.location.href = "/";
            }
          })
          .catch(e => {
            localStorage.removeItem('loggedIn')
            this.$cookie.delete('username');
            this.$cookie.delete('password');
            console.log("error in post request: " + e);
            window.location.href = "/";
          });
      }
    }
  }
}
