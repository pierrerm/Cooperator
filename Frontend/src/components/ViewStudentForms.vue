<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">Student Forms</h1>
          <div id="table">
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">First Name</th>
                <th style="padding:10px">Last Name</th>
                <th style="padding:10px">Email</th>
                <th style="padding:10px">McGill ID</th>
                <th style="padding:10px">Year</th>
                <th style="padding:10px">Major</th>
                <th style="padding:10px">Minor</th>
                <th style="padding:10px">Faculty</th>
                <th style="padding:10px">Phone</th>
              </tr>
              <tr>
                <td style="padding:10px">{{ student.firstName }}</td>
                <td style="padding:10px">{{ student.lastName }}</td>
                <td style="padding:10px">{{ student.email }}</td>
                <td style="padding:10px">{{ student.id }}</td>
                <td style="padding:10px">{{ student.academicYear }}</td>
                <td style="padding:10px">{{ student.major }}</td>
                <td style="padding:10px">{{ student.minor }}</td>
                <td style="padding:10px">{{ student.faculty }}</td>
                <td style="padding:10px">{{ student.phone }}</td>
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
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./javascript/studentForms.js">
</script>

<style scoped>
</style>