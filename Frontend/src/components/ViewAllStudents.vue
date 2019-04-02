<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">All Students</h1>
                <div align="center" style="padding-bottom:5%">
                <input
                    class="login-text"
                    type="text"
                    placeholder="Term (eg: 'winter2019')"
                    v-model="term"
                />
                <h6 align="left">Leave empty to view all students </h6>
                <input @click="getActiveStudents(term)"
                    type="submit"
                    value="Submit"
                    class="btn btn-primary py-2 px-4 text-white"
                />
                </div>
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
                              <router-link :to="{name: 'ViewStudentCoops', params: {userId: student.userId }}">
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
                    <h6 align="left" style="padding-top:1.6%">Click student name to view individual coops and forms</h6>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script src="./javascript/students.js">
</script>


<style scoped>
  h6 {
      color: #ffffff;
  }
</style>