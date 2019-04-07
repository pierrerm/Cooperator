import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-3417.herokuapp.com/'
var backendUrlGroup9 = 'https://cooperator-backend-060606.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

var AXIOS2 = axios.create({
    baseURL: backendUrlGroup9,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data() {
        return {
            employers: [],
            employersGroup9: [],
            employerName: '',
            company: '',
            term: ''
        }
    },

    methods: {
        getActiveEmployers(term) {
            if (term == '') {
                location.reload();
            }
            AXIOS.get(`/employer/active/` + term, {}, {})
                .then(response => {
                    this.employers = response.data
                });
        }
    },

    created: function () {
        AXIOS.get('/employers')
            .then(response => {
                this.employers = response.data;
                AXIOS2.get('/employers')
                    .then(response => {
                        for (var i = 0; i < response.data.length; i++) {
                            var employer = response.data[i];
                            employer.firstName = '';
                            employer.company = employer.name;
                            this.employersGroup9.push(employer);
                        }
                    })
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
                            if (localStorage.getItem('loggedIn') != "Administrator") {
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
        },
        filteredListGroup9() {
            if (this.company == '') {
                return this.employersGroup9.filter(employer => {
                    return employer.firstName.toLowerCase().includes(this.employerName.toLowerCase())
                })
            } else {
                return this.employersGroup9.filter(employer => {
                    return employer.company.toString().toLowerCase().includes(this.company.toString().toLowerCase())
                })
            }
        }
    }
}