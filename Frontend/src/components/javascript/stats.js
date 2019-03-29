import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-3417.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'login',
    data() {
        return {
            stats: [],
            students: []
        }
    },
    methods: {
        getStats(term) {
            if (term == '') {
                var errorMsg = "Invalid Term"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            AXIOS.get(`/stats/` + term , {}, {})
                .then(response => {
                    this.stats = response.data
                });
            AXIOS.get(`/student/problem/` + term , {}, {})
                .then(response => {
                    this.students = response.data
                });
        }
    }
}