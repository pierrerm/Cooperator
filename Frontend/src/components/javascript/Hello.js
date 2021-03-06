import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-3417.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'hello',
    data() {
        return {
          loginType: ''
        }
      },

      created: function () {
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
                            if(localStorage.getItem('loggedIn') != "Administrator") {
                                localStorage.setItem('loggedIn', "Administrator");
                                window.location.href = "/";
                            }                           
                        }
                        else {
                            localStorage.removeItem('loggedIn')
                            this.$cookie.delete('username');
                            this.$cookie.delete('password');
                            console.log("Wrong Login Information");
                            window.location.href = "/";
                        }
                    })
                    .catch(e => {
                        localStorage.removeItem('loggedIn')
                        this.$cookie.delete('username');
                        this.$cookie.delete('password');
                        console.log("Error in post request: " + e);
                        window.location.href = "/";
                    });
                console.log(localStorage.getItem('loggedIn'))
                this.loginType = localStorage.getItem('loggedIn')
            }
        }
        this.loginType = localStorage.getItem('loggedIn')
      },
}