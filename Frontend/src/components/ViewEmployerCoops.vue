<template>
  <div id="id">
    <div class="site-wrap">
      <div class="site-section" style="padding-top:10%">
        <div class="container" style="align:center">
          <h1 class="page-title">Employer Coops</h1>
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
                <th style="padding:10px">Coop ID</th>
                <th style="padding:10px">Semester</th>
                <th style="padding:10px">Start Date</th>
                <th style="padding:10px">End Date</th>
                <th style="padding:10px">Student Name</th>
                <th style="padding:10px">Location</th>
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
                <td style="padding:10px">{{ coop.student.firstName + " " + coop.student.lastName }}</td>
                <td style="padding:10px">{{ coop.location }}</td>
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

<script src="./javascript/employerCoops.js">
</script>

<style scoped>
</style>