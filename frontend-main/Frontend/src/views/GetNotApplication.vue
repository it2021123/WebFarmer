<template>
  <div class="container">
  <div class="card">
    <h1>Notifications</h1>
    <div  v-if="loading">Loading...</div>
    <div v-if="error">Error loading notifications: {{ error.message }}</div>
    <div class="list" v-if="data && data.length > 0">
      <ul>
        <li class="notify" v-for="notification in data" :key="notification.id">
          {{ notification.agriculturalCooperativeName }} - {{ notification.status }} - {{ notification.comments}}
        </li>
      </ul>
    </div>
    <div v-if="data && data.length === 0">No notifications available.</div>
  </div>
  </div>
</template>

<script setup>
import { useRemoteDataGET } from '@/composables/useRemoteDataGET.js';

const { data, error, loading } = useRemoteDataGET('http://localhost:3030/api/Farmer/get/not', true);
</script>
<style>
.container {
  background: linear-gradient(to right, #7ed56f, #28b485);
  width: 300%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.card {
  max-width: 600px;
  width: 100%;
  padding: 20px;
  background-color: #8fb250;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  text-align: center;
}



.list {
  margin-top: 20px;
}

ul {
  list-style-type: none;
  padding: 0;
}

.notify {
  margin-top: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #303b62;
  color:white;
}

.notify:hover {
  background-color: #f9f9f9;
  color:black;
}
</style>