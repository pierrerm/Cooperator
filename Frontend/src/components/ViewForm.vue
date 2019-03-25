<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">View/Edit Form</h1>

          <div id="table">
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">Form Type</th>
                <th style="padding:10px">Form ID</th>
                <th style="padding:10px">Submission Date</th>
                <th style="padding:10px">Coop ID</th>
                <div v-if="form.formType === 'Coop Evaluation'">
                  <td style="padding:10px">Employer Evaluation</td>
                  <td style="padding:10px">Software Technologies</td>
                  <td style="padding:10px">Work Experience</td>
                  <td style="padding:10px">Useful Courses</td>
                </div>
                <div v-else-if="form.formType === 'Student Evaluation'">
                  <td style="padding:10px">Student Performance</td>
                  <td style="padding:10px">Student Work Experience</td>
                </div>
                <div v-else-if="form.formType === 'Tasks Workload Report'">
                  <td style="padding:10px">Hours per week</td>
                  <td style="padding:10px">Tasks</td>
                  <td style="padding:10px">Training</td>
                  <td style="padding:10px">Wage</td>
                </div>
              </tr>

              <tr>
                <td style="padding:10px">{{ form.formType }}</td>
                <td style="padding:10px">{{ form.formId }}</td>
                <td style="padding:10px">{{ form.submissionDate }}</td>
                <td style="padding:10px">{{ form.coop }}</td>
                <div v-if="form.formType === 'Coop Evaluation'">
                  <td style="padding:10px">{{ form.employerEvaluation }}</td>
                  <td style="padding:10px">{{ form.softwareTechnologies }}</td>
                  <td style="padding:10px">{{ form.workExperience }}</td>
                  <td style="padding:10px">{{ form.usefulCourses }}</td>
                </div>
                <div v-else-if="form.formType === 'Student Evaluation'">
                  <td style="padding:10px">{{ form.studentPerformance }}</td>
                  <td style="padding:10px">{{ form.studentWorkExperience }}</td>
                </div>
                <div v-else-if="form.formType === 'Tasks Workload Report'">
                  <td style="padding:10px">{{ form.hoursPerWeek }}</td>
                  <td style="padding:10px">{{ form.tasks }}</td>
                  <td style="padding:10px">{{ form.training }}</td>
                  <td style="padding:10px">{{ form.wage }}</td>
                </div>
              </tr>
            </table>
          </div>
          <div class="half right" data-aos="fade-up">
            <div class="container" align="center">
              <hr>
              <input
                class="login-text"
                type="text"
                placeholder="First Name"
                v-model="firstName"
              />
              <input
                class="login-text"
                type="text"
                placeholder="Last Name"
                v-model="lastName"
              />
              <input
                class="login-text"
                type="text"
                placeholder="Email"
                v-model="email"
              />
              <input
                class="login-text"
                type="password"
                placeholder="Password"
                v-model="password"
              />
              <input @click="register(firstName, lastName, email, password, selected)"
                     type="submit"
                     value="Register"
                     class="btn btn-primary py-2 px-4 text-white"
              />
              <br>
              <span v-if="errorRegister" style="color:red">Error: {{errorRegister}} </span>
              <span v-if="response" style="color:green">Success: {{response}} </span>
            </div>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./javascript/editForm.js">
</script>

<style>
</style>
