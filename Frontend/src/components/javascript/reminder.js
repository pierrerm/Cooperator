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
            reminders: [],
            subject: '',
            date: '',
            deadline: '',
            description: '',
            urgency: '',
            coopId: '',
        }
    },

    methods: {
        sendReminder(subject, date, deadline, description, urgency, coopId) {
            if (subject == '') {
                var errorMsg = "Invalid Subject"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (date == '') {
                var errorMsg = "Invalid Date"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (description == '') {
                var errorMsg = "Invalid Date"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (urgency == '') {
                var errorMsg = "Invalid Date"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (deadline == '') {
                var errorMsg = "Invalid Date"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (coopId == '') {
                var errorMsg = "Invalid Date"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            var datearray = date.split("-");
            var newdate = datearray[2] + '-' + datearray[1] + '-' + datearray[0];

            var deadlinearray = deadline.split("-");
            var newdeadline = deadlinearray[2] + '-' + deadlinearray[1] + '-' + deadlinearray[0];
            AXIOS.post(`/reminder/` + subject + '/' + newdate + '/' + newdeadline + '/' + description + '/' + urgency + '/' + coopId, {}, {})
                .then(response => {
                    //console.log(response.data)
                    window.location.reload(true);
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorLogin = errorMsg
                });
        }
    },

    created: function() {
        AXIOS.get('/reminders')
            .then(response => {
                this.reminders = response.data;
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

}