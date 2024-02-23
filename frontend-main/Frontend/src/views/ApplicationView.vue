<script setup>

import router from "@/router/index.js";
import {useRemoteDataGET} from "@/composables/useRemoteDataGET.js";

const appId = router.currentRoute.value.params.appId;


const { data, error, loading } = useRemoteDataGET(`http://localhost:3030/api/Employee/application/${appId}`, true);

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
.RouterLink:hover{
  background-color: #3e3e6b;
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


.card {
  background-color: #b5dc77;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px; /* Προσθήκη padding για καλύτερη εμφάνιση */
  height: 900px;
  width:700px;
  margin: 0 auto;
}
table {
  background-color: #3b5615;
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

td {
  background-color: #ffffff;
  padding: 15px;
  text-align: left;

}
th{
  background-color: #467303;
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #467303;
}
thead{
  background-color: #467303;
}

tr:nth-child(even) {
  background-color: #ffffff;
}

tr:hover {
  background-color: #ffffff;
}
</style>
<!--
<template>
  <div class="bg-body-tertiary">
    <div class="container">
      <div class="row py-4 px-3">
        <div class="col-12">
          <div class="mb-4">
            <h1 class="fs-3">Application's Details</h1>
          </div>
          <div v-if="loading">Loading...</div>
          <div v-if="error">Error loading data: {{ error.message }}</div>
          <div v-if="data">
            <table class="table">
              <thead>
              <tr>
                <th>Field</th>
                <th>Value</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(value, field) in data" :key="field">
                <td>{{ field }}</td>
                <td>{{ value }}</td>
              </tr>
              </tbody>
            </table>
            <div>
              <RouterLink :to="{ name: 'check/application', params: { id: appId }}">Update</RouterLink>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
-->
<template>
  <body>
<div class="container">
  <div class="row py-4 px-3">

    <div class="card">

            <h1 class="fs-3">Application's Details</h1>

          <div v-if="loading">Loading...</div>
          <div v-if="error">Error loading data: {{ error.message }}</div>
          <div v-if="data">
            <table class="table">
              <thead>
              <tr>
                <th>Field</th>
                <th>Value</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(value, field) in data" :key="field">
                <td>{{ field }}</td>
                <td>
                  <!-- Check if the field is 'employee' or 'farmer' and display the relevant information -->
                  <template v-if="field === 'employee' || field === 'farmer'">
                    {{ value?.name }} ({{ value?.username }})
                  </template>
                  <template v-else>
                    {{ value }}
                  </template>
                </td>
              </tr>
              </tbody>
            </table>
            <div>
              <RouterLink :to="{ name: 'check/application', params: { id: appId }}" class="RouterLink">Update</RouterLink>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>
