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
      employers: [],
      employer: '',
      forms: []
    }
  },

  created: function() {
    AXIOS.get('/employers')
      .then(response => {
        this.employers = response.data;
        // Find employer by userId
        for (var i in this.employers) {
          if (this.employers[i].userId === this.$route.params.userId) {
            this.employer = this.employers[i]

            // TODO refactor this function (timing problems)
            AXIOS.get('/forms/employer/'+ this.employer.userId +'/Summer/2019')
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
