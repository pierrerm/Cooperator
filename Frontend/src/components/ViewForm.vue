<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">AcceptanceForm Forms</h1>
          <div id="table">
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">Form Type</th>
                <th style="padding:10px">Form ID</th>
                <th style="padding:10px">Submission Date</th>
                <th style="padding:10px">Coop ID</th>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
              </tr>

              <tr v-for="attribute in form">
                <td style="padding:10px">Acceptance Form</td>
                <td style="padding:10px">{{ form.formId }}</td>
                <td style="padding:10px">{{ form.submissionDate }}</td>
                <td style="padding:10px">{{ form.coop }}</td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
                <td style="padding:10px"></td>
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

<script src="./javascript/editForm.js">
</script>

<style>
</style>
