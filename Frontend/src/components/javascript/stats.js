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
            coops: [],
            formStats: [],
            term: ''
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
			AXIOS.get(`/coop/problem/` + term , {}, {})
				.then(response => {
                    for(var i = 0; i < response.data.length ; i++){
                        var coop = response.data[i]
                        coop.progress = (coop.form.length * 25).toString().concat("%");
                        this.coops.push(coop)
                    }
				});
			AXIOS.get(`/form/type/` + term , {}, {})
				.then(response => {
					this.formStats = response.data
				});
        }
    }
}