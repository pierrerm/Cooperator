<template>
  <div id="cooperator">
    <h2>People</h2>
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
          <input type="text" v-model="newPerson" placeholder="Person Name">
        </td>
        <td>
          <button @click="createPerson(newPerson)">Create Person</button>
        </td>
        <td>
          <button @click="getPerson()">Get Person</button>
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

<style>
  #cooperator {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>
