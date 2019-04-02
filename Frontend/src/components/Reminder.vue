<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">All Reminders</h1>
                <div align="center" style="padding-bottom:5%">
                <input
                    class="login-text"
                    type="text"
                    placeholder="Subject"
                    v-model="subject"
                    />
                
                <h6 align="left" color="#ffffff">Date</h6>
                <input
                    class="login-text"
                    type="date"
                    placeholder="Date"
                    v-model="date"
                    />

                <h6 align="left" color="#ffffff">Deadline</h6>
                <input
                    class="login-text"
                    type="date"             
                    placeholder="Deadline"
                    v-model="deadline"
                    />
                
                <input
                    class="login-text"
                    type="text"
                    placeholder="Description"
                    v-model="description"
                    />
                
                <input
                    class="login-text"
                    type="number"
                    min="1"
                    max = "3"
                    placeholder="Urgency"
                    v-model="urgency"
                    />

                <input
                    class="login-text"
                    type="number"
                    placeholder="CoopID"
                    v-model="coopId"
                    />

                <input @click="sendReminder(subject, date, deadline, description, urgency, coopId)"
                        type="submit"
                        value="Send Reminder"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                </div>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:5px">Urgency</th>
                            <th style="padding:5px">Subject</th>
                            <th style="padding:5px">Description</th>
                            <th style="padding:5px">Coop ID</th>
                            <th style="padding:5px">Student Name</th>
                            <th style="padding:5px">Student Id</th>
                        </tr>
                        <tr v-for="reminder in reminders">
                            <td style="padding:5px">{{ reminder.urgency }}</td>
                            <td style="padding:5px">{{ reminder.subject }}</td>
                            <td style="padding:5px">{{ reminder.description }}</td>
                            <td style="padding:5px">{{ reminder.coop.coopId }}</td>
                            <td style="padding:5px">{{ reminder.coop.student.firstName + " " + reminder.coop.student.lastName}}</td>
                            <td style="padding:5px">{{ reminder.coop.student.id }}</td>
                        </tr>
                    </table>
                </div>
                
            </div>
        </div>
    </div>
</div>
</template>

<script src="./javascript/reminder.js">
</script>

<style scoped>
  h6 {
      color: #ffffff;
  }
</style>
