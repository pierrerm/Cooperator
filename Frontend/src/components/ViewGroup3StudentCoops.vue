<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">Student Coops</h1>
          <div id="table">
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">First Name</th>
                <th style="padding:10px">Last Name</th>
                <th style="padding:10px">Email</th>
              </tr>
              <tr>
                <td style="padding:10px">{{ student.firstName }}</td>
                <td style="padding:10px">{{ student.lastName }}</td>
                <td style="padding:10px">{{ student.email }}</td>
              </tr>
            </table>
            <table class="info-table" style="width: 100%; height: 100%;">
              <tr>
                <th style="padding:10px">Coop ID</th>
                <th style="padding:10px">Semester</th>
                <th style="padding:10px">Start Date</th>
                <th style="padding:10px">End Date</th>
                <th style="padding:10px">Company</th>
                <th style="padding:10px">Location</th>
                <th style="padding:10px">Position</th>
              </tr>

              <tr v-for="coop in coops">
                <td style="padding:10px">{{ coop.coopID }}</td>
                <td style="padding:10px">{{ coop.academicSemester }}</td>
                <td style="padding:10px">{{ coop.startDate }}</td>
                <td style="padding:10px">{{ coop.endDate }}</td>
                <td style="padding:10px">{{ coop.company }}</td>
                <td style="padding:10px">{{ coop.location }}</td>
                <td style="padding:10px">{{ coop.position }}</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./javascript/studentCoopsGroup3.js">
</script>

<style scoped>
</style>
