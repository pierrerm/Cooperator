import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-060606.herokuapp.com/'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  data() {
    return {
      employers: [],
      employer: {},
      coops: []
    }
  },

  created: function() {
      this.employer.company = this.$route.params.company;
      this.employer.email = this.$route.params.email;
      AXIOS.get('/coopTerm/employer/' + this.$route.params.userId)
      .then(response => {
        this.coops = response.data;
      })
  }
}
