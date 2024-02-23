
<script setup>
import { RouterLink } from 'vue-router';
import { useApplicationStore } from '@/stores/application.js';
const applicationStore = useApplicationStore();
</script>


<style scoped>
/* Στυλ για το header */
.mainHeader {
  height: 100px;
  background-color: #4caf50; /* Darker green color */
  padding: 10px 0; /* Reduced padding for a sleeker look */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle box-shadow for depth */
}

/* Στυλ για το container */
.headerContainer {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 15px;
}

/* Στυλ για τα κουμπιά σύνδεσης/αποσύνδεσης */
.nav-item {
  display: flex;
  align-items: center;
}

/* Στυλ για το λογότυπο */
.logo {
  color: #fff; /* White color for the logo text */
  font-size: 2rem; /* Increased font size for a bold appearance */
  font-weight: bold;
  text-decoration: none;
}

.logo:hover {
  color: #34346c;
}

/* Στυλ για τα κουμπιά του μενού */
.nav-pills {
  margin: 0; /* Remove default margin for a cleaner look */
}

.nav-link {
  color: #fff; /* White color for the link text */
  margin: 0 15px; /* Increased margin for better spacing */
  font-weight: bold;
}

.nav-link:hover {
  color: #34346c;
}

</style>

<template>
  <header class="mainHeader">
    <div class="headerContainer">
      <div class="d-flex flex-wrap justify-content-between align-items-center">
        <router-link to="/" class="logo">
          E-AGROTIS
        </router-link>
        <ul class="nav nav-pills">
          <li class="nav-item" v-if="applicationStore.isAuthenticated === true">
            <router-link :to="{ name: 'profile' }" class="nav-link"
            >Profile
              <span style="font-size: 12px"
              >({{ applicationStore.userData?.username }})</span
              ></router-link
            >
          </li>
          <li class="nav-item" v-if="applicationStore.isAuthenticated === false">
            <router-link :to="{ name: 'login' }" class="nav-link"
            >Login</router-link
            >
          </li>
          <li class="nav-item" v-if="applicationStore.isAuthenticated === true">
            <router-link :to="{ name: 'logout' }" class="nav-link"
            >Logout</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </header>
</template>
