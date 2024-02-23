<script setup>
import { useRemoteDataGET } from '@/composables/useRemoteDataGET.js';

const { data, error, loading } = useRemoteDataGET('http://localhost:3030/api/Admin/employees', true);

</script>

<style>
body {
  background: linear-gradient(to right, #7ed56f, #28b485);
}

.RouterLink {
  background-color: #467303;
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
  background-color: #b5dc77;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  /* Προσθήκη padding για καλύτερη εμφάνιση */
  height: 900px;
  width: 700px;
  margin: 0 auto;
}

table {
  background-color: #467303;
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

td {
  background-color: #66813e;
  padding: 15px;
  text-align: left;
}

th {
  background-color: #467303;
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #467303;
}

thead {
  background-color: #467303;
}

tr:nth-child(even) {
  background-color: #467303;
}

tr:hover {
  background-color: #467303;
}
</style>

<template>
  <body>
<div class="card">
    <h1>Employees</h1>
    <table class="table" v-if="data && data.length > 0">
      <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
        <th></th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(entry, index) in data" :key="index">
        <td>{{ BigInt(entry.id) }}</td>
        <td>{{ entry.username }}</td>
        <td>
          <RouterLink :to="{ name: 'getEmployee', params: { id: entry.id }}" class="RouterLink">
            More Details
          </RouterLink>
        </td>
        <td></td>
        <td></td>
      </tr>
      </tbody>
    </table>
</div>
  </body>
</template>
