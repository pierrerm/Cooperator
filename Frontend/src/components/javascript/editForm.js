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
      forms: [],
      form: ''
    }
  },

  created: function() {
    AXIOS.get('/forms')
      .then(response => {
        this.forms = response.data;
        // Find student by userId
        for (var i in this.forms) {
          if (this.forms[i].formId === this.$route.params.formId) {
            this.form = this.forms[i]
          }
        }
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
  methods: {
    editAcceptanceForm(submissionDate){
      AXIOS.put('/form/' + this.form.formId + '/acceptanceForm/submissionDate/' + submissionDate,{},{})
        .then(response => {
          this.form = response.data;
        });
    },

    editCoopEvaluation(submissionDate, workExperience, employerEvaluation, softwareTechnologies, usefulCourses){
      AXIOS.put('/form/' + this.form.formId + '/coopEvaluation/submissionDate/' + submissionDate,{},{})
        .then(response => {
          this.form = response.data;
        });
      AXIOS.put('/form/' + this.form.formId + '/coopEvaluation/workExperience/' + workExperience,{},{})
        .then(response => {
          this.form = response.data;
        });
      AXIOS.put('/form/' + this.form.formId + '/coopEvaluation/employerEvaluation/' + employerEvaluation,{},{})
        .then(response => {
          this.form = response.data;
        });
      AXIOS.put('/form/' + this.form.formId + '/coopEvaluation/softwareTechnologies/' + softwareTechnologies,{},{})
        .then(response => {
          this.form = response.data;
        });
      AXIOS.put('/form/' + this.form.formId + '/coopEvaluation/usefulCourses/' + usefulCourses,{},{})
        .then(response => {
          this.form = response.data;
        });
    },

    editStudentEvaluation(submissionDate, studentPerformance, studentWorkExperience){
      AXIOS.put('/form/' + this.form.formId + '/studentEvaluation/submissionDate/' + submissionDate,{},{})
        .then(response => {
          this.form = response.data;
        });
    AXIOS.put('/form/' + this.form.formId + '/studentEvaluation/studentPerformance/' + studentPerformance,{},{})
        .then(response => {
          this.form = response.data;
        });
    AXIOS.put('/form/' + this.form.formId + '/studentEvaluation/studentWorkExperience/' + studentWorkExperience,{},{})
        .then(response => {
          this.form = response.data;
        });
    },
    editTasksWorkloadReport(submissionDate, hoursPerWeek, tasks, training, wage){
      AXIOS.put('/form/' + this.form.formId + '/tasksWorkloadReport/submissionDate/' + submissionDate,{},{})
        .then(response => {
          this.form = response.data;
        });
    AXIOS.put('/form/' + this.form.formId + '/tasksWorkloadReport/hoursPerWeek/' + hoursPerWeek,{},{})
        .then(response => {
          this.form = response.data;
        });
    AXIOS.put('/form/' + this.form.formId + '/tasksWorkloadReport/tasks/' + tasks,{},{})
        .then(response => {
          this.form = response.data;
        });
    AXIOS.put('/form/' + this.form.formId + '/tasksWorkloadReport/training/' + training,{},{})
        .then(response => {
          this.form = response.data;
        });
    AXIOS.put('/form/' + this.form.formId + '/tasksWorkloadReport/wage/' + wage,{},{})
        .then(response => {
          this.form = response.data;
        });
    }
  }
}
