<template>
<div id="stats">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Semester Stats</h1>
                <select v-model="selected">
                <option value="Active Student">Active Student</option>
                <option value="Active Coop">Active Coop</option>
                <option value="Active Coop Completed">Active Coop Completed</option>
                <option value="Active Coop Completed Student">Active Coop Completed Student</option>
                <option value="Coop Completed">Coop Completed</option>
                <option value="Coop Completed Student">Coop Completed Student</option>
                </select>
                <input
                    class="login-text"
                    type="text"
                    placeholder="Term"
                    v-model="term"
                />
                <input
                    class="login-text"
                    type="text"
                    placeholder="Student"
                    v-model="student"
                />
                <input @click="getStats(term)"
                    type="submit"
                    value="Submit"
                    class="btn btn-primary py-2 px-4 text-white"
                />
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr v-if="selected">
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
                        <tr v-for="student in stats">
                            <td style="padding:10px">{{ student.firstName }}</td>
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
            </div>
        </div>
    </div>
    </div>
</template>

<script src="./javascript/stats.js"></script>