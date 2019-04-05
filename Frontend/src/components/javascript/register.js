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
    name: 'register',
    data() {
        return {
            firstName: '',
            lastName: '',
            userId: '',
            email: '',
            password: '',
            errorRegister: '',
            response: '',
        }
    },
    created: function () {
    
    },
    methods: {
        register(firstName, lastName, userId, email, password) {
            if (firstName == '') {
                var errorMsg = "Invalid last name"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (lastName == '') {
                var errorMsg = "Invalid last name"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (userId == '') {
                var errorMsg = "Invalid User ID"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (email == '') {
                var errorMsg = "Invalid email"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (password == '') {
                var errorMsg = "Invalid password"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            var md = forge.md.sha384.create();
            md.update(password);
            var hash = md.digest().toHex();
            AXIOS.post(`/admin/` + firstName + "/" + lastName + "/" + userId + "/" + email + "/" + hash, {}, {})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data  
                    console.log(this.response)
                    if(this.response.firstName == null) {
                        this.errorRegister = 'UserID is not a Valid Admin ID!'
                        this.response = ''
                    } else {
                        this.response = 'Admin Created!'
                        this.errorRegister = ''
                    }
                    this.firstName= ''
                    this.lastName= ''
                    this.userId = ''
                    this.email= ''
                    this.password= ''
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorRegister = errorMsg
                    this.response = ''
                });

            
        }
    }
}