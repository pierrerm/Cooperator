<template>
  <div id="cooperator">
    <h2>Cooperator</h2>
    <table>
      <tr v-for="person in people" >
        <td>{{ person.name }}</td>
        <td>
          <ul>
            <li v-for="event in person.events">
              {{event.name}}
            </li>
          </ul>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newPerson" placeholder="Student Name">
        </td>
        <td>
          <button @click="createPerson(newPerson)">Create Student</button>
        </td>
        <td>
          <button @click="getPerson()">Get Student</button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="errorPerson" style="color:red">Error: {{errorPerson}}</span>
    </p>
  </div>
</template>

<script src="./administration.js">
</script>

<style lang="scss">
@import '../style/css/style.css';
</style>