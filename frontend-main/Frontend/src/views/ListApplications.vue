
<template>
  <body>
  <div class="card">
    <div v-if="loading">Loading...</div>
    <div v-if="error">Error loading applications: {{ error.message }}</div>
    <div><h1>Applications</h1></div>
      <table class="table" v-if="data && data.length > 0">
        <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Status</th>
          <th></th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="entry in data" :key="entry.id">
          <td>{{ BigInt(entry.id) }}</td>
          <td>{{ entry.agriculturalCooperativeName }}</td>
          <td>{{entry.status}}</td>
          <td><RouterLink :to="{ name: 'application', params: { appId: entry.id }}" class="RouterLink">More Details</RouterLink></td>
          <td></td>
          <td></td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-if="data && data.length === 0">No applications available.</div>
  </body>

</template>

<script setup>
import { ref } from 'vue';
import { useRemoteDataGET } from '@/composables/useRemoteDataGET';

const { data, error, loading } = useRemoteDataGET('http://localhost:3030/api/Employee/applications', true);
</script>

<style>
body{
  background: linear-gradient(to right, #7ed56f, #28b485);

}


.RouterLink {
  background-color: #467303;;
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  transition: background-color 0.3s;
}

.RouterLink:hover {
  background-color: #0056b3;
}

.card {
  background-color: #79964b;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px; /* Προσθήκη padding για καλύτερη εμφάνιση */
  height: 900px;
  width:700px;
  margin: 0 auto;
}
.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

td {

  padding: 15px;
  text-align: left;
}

th {

  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #467303;
}


</style>