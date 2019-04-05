<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">Coop Forms</h1>
          <div id="table">
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">Coop ID</th>
                <th style="padding:10px">Semester</th>
                <th style="padding:10px">Start Date</th>
                <th style="padding:10px">End Date</th>
                <th style="padding:10px">Student Name</th>
                <th style="padding:10px">McGill ID</th>
                <th style="padding:10px">Employer Name</th>
                <th style="padding:10px">Company</th>
              </tr>
              <tr>
                <td style="padding:10px">{{ coop.coopId }}</td>
                <td style="padding:10px">{{ coop.semester }}</td>
                <td style="padding:10px">{{ coop.startDate }}</td>
                <td style="padding:10px">{{ coop.endDate }}</td>
                <router-link :to="{name: 'ViewStudentCoops', params: {userId: coop.student.userId }}">
                  <td style="padding:10px">
                    {{ coop.student.firstName + " " + coop.student.lastName }}
                  </td>
                </router-link>
                <td style="padding:10px">{{ coop.student.id }}</td>
                <router-link :to="{name: 'ViewEmployerCoops', params: {userId: coop.employer.userId }}">
                  <td style="padding:10px">
                    {{ coop.employer.firstName + " " + coop.employer.lastName }}
                  </td>
                </router-link>
                <td style="padding:10px">{{ coop.employer.company }}</td>
              </tr>
            </table>
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">Form Type</th>
                <th style="padding:10px">Form ID</th>
                <th style="padding:10px">Submission Date</th>
                <th style="padding:10px">Coop ID</th>

              </tr>

              <tr v-for="form in forms">
                <td style="padding:10px">
                  <router-link :to="{name: 'ViewForm', params: {formId: form.formId }}">
                    {{ form.formType }}
                  </router-link>
                </td>
                <td style="padding:10px">{{ form.formId }}</td>
                <td style="padding:10px">{{ form.submissionDate }}</td>
                <td style="padding:10px">{{ form.coop }}</td>
              </tr>
            </table>
			<h6 align="left" style="padding-top:1.6%">Click form ID to edit form</h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./javascript/coop.js">
</script>

<style scoped>
</style>
