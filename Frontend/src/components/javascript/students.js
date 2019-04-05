import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-3417.herokuapp.com/'
var backendUrlGroup3 = 'https://sturegistration-backend-009b01.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

var AXIOS2 = axios.create({
    baseURL: backendUrlGroup3,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data() {
        return {
            students: [],
            term: '',
            studentsGroup3: []
        }
    },

    methods: {
        getActiveStudents(term) {
            if (term == '') {
                AXIOS.get('/students')
                    .then(response => {
                        this.students = response.data;
            })
            }
            AXIOS.get(`/student/active/` + term , {}, {})
                .then(response => {
                    this.students = response.data
                });
        }
    },

    created: function() {
        AXIOS.get('/students')
                .then(response => {
                        this.students = response.data;
                })
        AXIOS2.get('/allStudents')
            .then(response => {
                this.studentsGroup3 = response.data;
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