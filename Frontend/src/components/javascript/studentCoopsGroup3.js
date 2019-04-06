import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://sturegistration-backend-009b01.herokuapp.com/'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  data() {
    return {
      students: [],
      student: '',
      coops: []
    }
  },

  created: function () {
    AXIOS.get('student/' + this.$route.params.userId)
      .then(response => {
        this.student = response.data;
        var nameArray = this.student.coopUserName.split(" ");
        this.student.firstName = nameArray[0];
        this.student.lastName = nameArray[1];
        this.student.email = this.student.coopUserEmail;
      })
    AXIOS.get('allInfo/' + this.$route.params.userId)
      .then(response => {
        for (var i = 0; i < response.data.length; i++) {
          var c = response.data[i];
          var array = c.coopName.split("@");
          c.position = array[0];
          c.company = array[1];
          this.coops.push(c)
      }
      })


  }
}
