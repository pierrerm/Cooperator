<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">All Students</h1>
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
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script src="./javascript/students.js">
</script>

<style>
</style>
