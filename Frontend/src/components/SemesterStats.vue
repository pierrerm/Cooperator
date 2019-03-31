<template>
<div id="stats">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Semester Stats</h1>
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
                            <th style="padding:10px">First Name</th>
                            <th style="padding:10px">Last Name</th>
                            <th style="padding:10px">Email</th>
                            <th style="padding:10px">McGill ID</th>
                            <th style="padding:10px">Year</th>
                            <th style="padding:10px">Major</th>
                            <th style="padding:10px">Minor</th>
                            <th style="padding:10px">Faculty</th>
                            <th style="padding:10px">Phone</th>
                            <th style="padding:10px">Coop</th>
                        </tr>
                        <tr v-for="student in students">
                            <td style="padding:10px">
                              <router-link :to="{name: 'ViewStudentForms', params: {userId: student.userId }}">
                                {{ student.firstName }}
                              </router-link>
                            </td>
                            <td style="padding:10px">{{ student.lastName }}</td>
                            <td style="padding:10px">{{ student.email }}</td>
                            <td style="padding:10px">{{ student.id }}</td>
                            <td style="padding:10px">{{ student.academicYear }}</td>
                            <td style="padding:10px">{{ student.major }}</td>
                            <td style="padding:10px">{{ student.minor }}</td>
                            <td style="padding:10px">{{ student.faculty }}</td>
                            <td style="padding:10px">{{ student.phone }}</td>
                            <td style="padding:10px">{{ student.coop }}</td>
                        </tr>
                    </table>
                    </div>
					<h3 class="page-title">Form Submission</h3>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">No Forms</th>
                            <th style="padding:10px">One Form</th>
                            <th style="padding:10px">Two Forms</th>
                            <th style="padding:10px">Three Forms</th>
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