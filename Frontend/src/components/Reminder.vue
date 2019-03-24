<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">All Reminders</h1>
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
                            <td style="padding:5px">{{ reminder.coop.student.firstName }}</td>
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

<style>
</style>
