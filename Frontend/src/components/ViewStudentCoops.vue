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
                <th style="padding:10px">Coop ID</th>
                <th style="padding:10px">Semester</th>
                <th style="padding:10px">Start Date</th>
                <th style="padding:10px">End Date</th>
                <th style="padding:10px">Employer Name</th>
                <th style="padding:10px">Company</th>
				        <th style="padding:10px">Reminders</th>
              </tr>

              <tr v-for="coop in coops">
                <td style="padding:10px">
                  <router-link :to="{name: 'ViewCoop', params: {coopId: coop.coopId }}">
                    {{ coop.coopId }}
                  </router-link>
                </td>
                <td style="padding:10px">{{ coop.semester }}</td>
                <td style="padding:10px">{{ coop.startDate }}</td>
                <td style="padding:10px">{{ coop.endDate }}</td>
                <td style="padding:10px">{{ coop.employer.firstName + " " + coop.employer.lastName }}</td>
                <td style="padding:10px">{{ coop.employer.company }}</td>
                <td style="padding:10px">
                  <router-link :to="{name: 'StudentReminder', params: {coopId: coop.coopId }}">
                    {{ "View Reminders" }}
                  </router-link>
                </td>
              </tr>
            </table>
			<h6 align="left" style="padding-top:1.6%">Click coop ID to view and edit forms</h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./javascript/studentCoops.js">
</script>

<style scoped>
  h6 {
      color: #ffffff;
  }
</style>
