<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">View & Edit Form</h1>

          <div class="half left" id="table">
            <table class="info-table" style="width: 90%;">
              <div>
                <tr>
                  <th style="padding:10px;width: 300px">Form Type</th>
                  <td style="padding:10px">{{ form.formType }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Form ID</th>
                  <td style="padding:10px">{{ form.formId }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Submission Date</th>
                  <td style="padding:10px">{{ form.submissionDate }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Coop ID</th>
                  <td style="padding:10px">{{ form.coop }}</td>
                </tr>
              </div>

              <div v-if="form.formType === 'Coop Evaluation'">
                <tr>
                  <th style="padding:10px;width: 300px">Employer Evaluation</th>
                  <td style="padding:10px">{{ form.employerEvaluation }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Software Technologies</th>
                  <td style="padding:10px">{{ form.softwareTechnologies }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Work Experience</th>
                  <td style="padding:10px">{{ form.workExperience }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Useful Courses</th>
                  <td style="padding:10px">{{ form.usefulCourses }}</td>
                </tr>
              </div>

              <div v-else-if="form.formType === 'Student Evaluation'">
                <tr>
                  <th style="padding:10px;width: 300px">Student Performance</th>
                  <td style="padding:10px">{{ form.studentPerformance }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Student Work Experience</th>
                  <td style="padding:10px">{{ form.studentWorkExperience }}</td>
                </tr>
              </div>

              <div v-else-if="form.formType === 'Tasks Workload Report'">
                <tr>
                  <th style="padding:10px;width: 300px">Hours per week</th>
                  <td style="padding:10px">{{ form.hoursPerWeek }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Tasks</th>
                  <td style="padding:10px">{{ form.tasks }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Training</th>
                  <td style="padding:10px">{{ form.training }}</td>
                </tr>
                <tr>
                  <th style="padding:10px;width: 300px">Wage</th>
                  <td style="padding:10px">{{ form.wage }}</td>
                </tr>
              </div>
            </table>
          </div>
          <div class="half right" data-aos="fade-up">
            <div class="container" align="center">
              <hr>
              <input
                class="login-text"
                type="text"
                placeholder="Submission Date (dd-mm-yyyy)"
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
                       value="Edit"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>
              <div v-else-if="form.formType === 'Coop Evaluation'">
                <input @click="editCoopEvaluation(submissionDate, workExperience, employerEvaluation, softwareTechnologies, usefulCourses)"
                       type="submit"
                       value="Edit"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>
              <div v-else-if="form.formType === 'Student Evaluation'">
                <input @click="editStudentEvaluation(submissionDate, studentPerformance, studentWorkExperience)"
                       type="submit"
                       value="Edit"
                       class="btn btn-primary py-2 px-4 text-white"
                />
              </div>
              <div v-else-if="form.formType === 'Tasks Workload Report'">
                <input @click="editTasksWorkloadReport(submissionDate, hoursPerWeek, tasks, training, wage)"
                       type="submit"
                       value="Edit"
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
</template>

<script src="./javascript/editForm.js">
</script>

<style>
</style>
