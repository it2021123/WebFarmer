<template>
  <body>
  <div class="container1">
    <div class="card1">
      <p class="user-info">Logged in as: <strong>{{ applicationStore.userData.username }}</strong></p>
      <div class="col-12">
        <div class="header">
          <h1>Home</h1>
        </div>
        <div class="card2">
          <ul>
            <li>Welcome to Eagrotis App</li>
            <li>Logged in as: <strong>{{ applicationStore.userData.name }}</strong></li>
            <br><br>
            <li v-if="applicationStore.isAdmin()"><router-link to="/users" class="link">Users</router-link></li>
            <li v-if="applicationStore.isAdmin()"><router-link to="/getFarmers" class="link">Farmers</router-link></li>
            <li v-if="applicationStore.isAdmin()"><router-link to="/getEmployees" class="link">Employees</router-link></li>
            <li v-if="applicationStore.isEmployee()"><router-link to="/applications" class="link">Applications</router-link></li>
            <li v-if="applicationStore.isFarmer()"><router-link to="/sumbitApplication" class="link">New Application</router-link></li>
            <li v-if="applicationStore.isFarmer()"><router-link to="/get/not" class="link">My Applications</router-link></li>
            <li v-if="applicationStore.isAdmin()"><router-link to="/signup" class="link">Register new user</router-link></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>

<style scoped>
body {
  background: linear-gradient(to right, #7ed56f, #28b485);
  font-family: 'Arial', sans-serif; /* Font family for better compatibility */
  margin: 0; /* Remove default margin */
  height: 100vh; /* Full viewport height */
  display: flex;
  justify-content: center;
  align-items: center;
}

.card1 {
  width: 400px;
  height: 500px;
  padding: 20px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
  border-radius: 12px;
  background: linear-gradient(to bottom, #426022, #8fb250); /* Gradient background for card1 */
}

.user-info {
  color: #fff; /* Text color for user info */
}

.card2 {
  background: linear-gradient(to bottom, #7ca43a, #5c792d); /* Gradient background for card2 */
  width: 360px;
  height: 300px;
  border-radius: 12px;
  text-align: center;
}

.header {
  margin-bottom: 20px;
  color: white;
}

.link {
  display: inline-block;
  padding: 10px 16px;
  background-color: #12273b;
  color: white;
  text-decoration: none;
  margin-right: 12px;
  font-weight: bold;
  font-size: 15px;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.link:hover {
  background-color: #4e6c8c;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: block;
  margin-bottom: 10px;
  color: #fff; /* Text color for list items */
}



</style>


<script setup>
import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const applicationStore = useApplicationStore();

</script>