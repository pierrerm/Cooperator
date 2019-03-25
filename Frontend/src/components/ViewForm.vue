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
                placeholder="Submission Date"
                v-model="submissionDate"
              />
              <div v-if="form.formType === 'Coop Evaluation'">
                <input
                  class="login-text"
                  type="number"
                  placeholder="Employer Evaluation"
                  v-model="employerEvaluation"
                />
                <input
                  class="login-text"
                  type="text"
                  placeholder="Software Technologies"
                  v-model="softwareTechnologies"
                />
                <input
                  class="login-text"
                  type="text"
                  placeholder="Work Experience"
                  v-model="workExperience"
                />
                <input
                  class="login-text"
                  type="text"
                  placeholder="Useful Courses"
                  v-model="usefulCourses"
                />
              </div>
              <div v-else-if="form.formType === 'Student Evaluation'">
                <input
                  class="login-text"
                  type="number"
                  placeholder="Student Performance"
                  v-model="studentPerformance"
                />
                <input
                  class="login-text"
                  type="text"
                  placeholder="Student Work Experience"
                  v-model="studentWorkExperience"
                />
              </div>
              <div v-else-if="form.formType === 'Tasks Workload Report'">
                <input
                  class="login-text"
                  type="number"
                  placeholder="Hours Per Week"
                  v-model="hoursPerWeek"
                />
                <input
                  class="login-text"
                  type="text"
                  placeholder="Tasks"
                  v-model="tasks"
                />
                <input
                  class="login-text"
                  type="text"
                  placeholder="Training"
                  v-model="training"
                />
                <input
                  class="login-text"
                  type="number"
                  placeholder="Wage"
                  v-model="wage"
                />
              </div>

              <div v-if="form.formType === 'Acceptance Form'">
                <input @click="editAcceptanceForm(submissionDate)"
                       type="submit"
                       value="Register"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>
              <div v-else-if="form.formType === 'Coop Evaluation'">
                <input @click="editCoopEvaluation(submissionDate, workExperience, employerEvaluation, softwareTechnologies, usefulCourses)"
                       type="submit"
                       value="Register"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>
              <div v-else-if="form.formType === 'Student Evaluation'">
                <input @click="editStudentEvaluation(submissionDate, studentPerformance, studentWorkExperience)"
                       type="submit"
                       value="Register"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>
              <div v-else-if="form.formType === 'Tasks Workload Report'">
                <input @click="editTasksWorkloadReport(submissionDate, hoursPerWeek, tasks, training, wage)"
                       type="submit"
                       value="Register"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>

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
