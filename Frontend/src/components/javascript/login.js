import axios from 'axios'
import forge from 'node-forge'
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
            username: this.$cookie.get("username") || '',
            password: this.$cookie.get("password") || '',
            errorLogin: '',
            response: '',
        }
    },
    methods: {
        login(username, password) {
            if (username == '') {
                var errorMsg = "Invalid username"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (password == '') {
                var errorMsg = "Invalid password"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            var md = forge.md.sha384.create();
            md.update(password);
            var hash = md.digest().toHex();
            AXIOS.post(`/login/` + username + '/' + hash, {}, {})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data
                    this.errorLogin = ''
                    this.$cookie.set("username", username, { expires: '30min' })
                    this.$cookie.set("password", hash, { expires: '30min' })
                    this.username = this.$cookie.get("username") || ''
                    this.password = this.$cookie.get("password") || ''
                    if (this.response == 'Accepted') {
                        localStorage.setItem('loggedIn', "Administrator")
                        window.location.href = "/";           
                    }
                    else{
                        this.errorLogin = response.data
                        console.log(this.response)
                    }
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorLogin = errorMsg
                });
        }
    }
}