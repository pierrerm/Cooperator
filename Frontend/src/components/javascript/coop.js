import axios from 'axios';
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-3417.herokuapp.com/'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
  data() {
    return {
      coops: [],
      coop: '',
      forms: [],
      counter: 0,
    }
  },

  created: function() {
    AXIOS.get('/coops')
      .then(response => {
        this.coops = response.data;
        // Find coop by coopId
        for (var i in this.coops) {
          if (this.coops[i].coopId === this.$route.params.coopId) {
            this.coop = this.coops[i];

            AXIOS.get('/forms/'+ this.coop.coopId)
              .then(response => {
                this.forms = response.data;
                this.counter = this.forms.length;
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
