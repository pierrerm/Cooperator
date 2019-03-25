<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">Employer Forms</h1>
          <div id="table">
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">First Name</th>
                <th style="padding:10px">Last Name</th>
                <th style="padding:10px">Email</th>
                <th style="padding:10px">Position</th>
                <th style="padding:10px">Company</th>
                <th style="padding:10px">Location</th>
                <th style="padding:10px">Phone Number</th>
              </tr>
              <tr>
                <td style="padding:10px">{{ employer.firstName }}</td>
                <td style="padding:10px">{{ employer.lastName }}</td>
                <td style="padding:10px">{{ employer.email }}</td>
                <td style="padding:10px">{{ employer.position }}</td>
                <td style="padding:10px">{{ employer.company }}</td>
                <td style="padding:10px">{{ employer.location }}</td>
                <td style="padding:10px">{{ employer.phone }}</td>
              </tr>
            </table>
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">Form Type</th>
                <th style="padding:10px">Form ID</th>
                <th style="padding:10px">Submission Date</th>
                <th style="padding:10px">Coop ID</th>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
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
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./javascript/employerForms.js">
</script>

<style>
</style>
