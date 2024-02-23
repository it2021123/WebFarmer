<script setup>
import { useApplicationStore } from '@/stores/application.js';
const { userData } = useApplicationStore();
</script>

<template>
  <div>
    <div class="container">
      <div class="profile-card">
        <div class="col-12">
          <div class="header">
            <h1 class="profile-title">User Profile</h1>
          </div>
          <div class="content">
            <template v-if="userData">
              <div class="info-item">
                <strong>Username:</strong> {{ userData.username }}
              </div>
              <div class="info-item" v-if="userData.name">
                <strong>Name:</strong> {{ userData.name }}
              </div>
              <div class="info-item" v-if="userData.role">
                <strong>Role:</strong> {{ userData.role}}
              </div>
              <div class="info-item" v-if="userData.birthDate">
                <strong>Date of Birth:</strong> {{ userData.birthDate }}
              </div>
            </template>
            <template v-else>
              <p>No user data available.</p>
            </template>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
body {
  background: linear-gradient(to right, #7ed56f, #28b485);
  margin: 0;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(to right, #7ed56f, #28b485);
}

.profile-card {
  width: 400px;
  height:550px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  background-color: #add26b;
  transition: box-shadow 0.3s ease;
}

.profile-card:hover {
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}

.header {
  color: #ffffff;
  text-align: center;
  margin-bottom: 20px;
}

.profile-title {
  font-size: 2.5rem;
  color: #ffffff;
}

.content {
  background-color: rgba(123, 155, 80, 0.9);
height:370px;
  text-align: center;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  padding: 20px;
  border-radius: 8px;
}

.info-item {
  margin-bottom: 15px;
  font-size: 1.2rem;
  color: #ffffff;
  font-weight: bold;
}
</style>
