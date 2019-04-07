<template>
<div id="stats">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Semester Stats</h1>
                <div align="center" style="padding-bottom:5%">
                <input
                    class="login-text"
                    type="text"
                    placeholder="Term (eg: 'winter2019')"
                    v-model="term"
                />
                <input @click="getStats(term)"
                    type="submit"
                    value="Submit"
                    class="btn btn-primary py-2 px-4 text-white"
                />
                </div>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">Active Coops</th>
                            <th style="padding:10px">Completion Index</th>
                            <th style="padding:10px">Average Form Submission</th>
                            <th style="padding:10px">Problematic Students</th>
                        </tr>
                        <tr v-for="s in stats">
                            <td style="padding:10px">{{ s.activeCoops }}</td>
                            <td style="padding:10px">{{ s.completionIndex }}</td>
                            <td style="padding:10px">{{ s.averageFormSubmission }}</td>
                            <td style="padding:10px">{{ s.problematicStudents }}</td>
                        </tr>
                    </table>
                <h3 class="page-title">Incomplete Coops</h3>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">Coop ID</th>
                            <th style="padding:10px">Semester</th>
                            <th style="padding:10px">Start Date</th>
                            <th style="padding:10px">End Date</th>
                            <th style="padding:10px">Employer Name</th>
                            <th style="padding:10px">Company</th>
				            <th style="padding:10px">Reminders</th>
                            <th style="padding:10px">Progress</th>
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
                            <td style="padding:10px">{{ coop.progress }}</td>
                        </tr>
                    </table>
                    </div>
					<h3 class="page-title">Form Submission</h3>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">Acceptance Forms</th>
                            <th style="padding:10px">Coop Evaluation Forms</th>
                            <th style="padding:10px">Student Evaluation Forms</th>
                            <th style="padding:10px">Tasks and Workload Reports</th>
                        </tr>
                        <tr v-for="fs in formStats">
                            <td style="padding:10px">{{ fs.noForms }}</td>
                            <td style="padding:10px">{{ fs.oneForm }}</td>
                            <td style="padding:10px">{{ fs.twoForms }}</td>
                            <td style="padding:10px">{{ fs.threeForms }}</td>
                        </tr>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script src="./javascript/stats.js"></script>