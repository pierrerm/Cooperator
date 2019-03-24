<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">All Reminders</h1>
                <div id="table">
                    <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">Urgency</th>
                        </tr>
                        <tr v-for="reminder in reminders">
                            <td style="padding:10px">{{ reminder.urgency }}</td>
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
