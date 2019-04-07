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
            employerName: '',
            company: '',
        }
    },

    methods: {
        getActiveStudents(term) {
            if (term == '') {
                AXIOS.get('/employers')
                    .then(response => {
                        this.employers = response.data;
                    })
            }
            AXIOS.get(`/employer/active/` + term , {}, {})
                .then(response => {
                    this.employers = response.data
                });
        }
    },

    created: function() {
        AXIOS.get('/employers')
            .then(response => {
                this.employers = response.data;
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
    },

    computed: {
        filteredList() {
            if (this.company == '') {
                return this.employers.filter(employer => {
                    return employer.firstName.toLowerCase().includes(this.employerName.toLowerCase())
                })
            } else {
                return this.employers.filter(employer => {
                    return employer.company.toString().toLowerCase().includes(this.company.toString().toLowerCase())
                })
            }
        }
      }
}