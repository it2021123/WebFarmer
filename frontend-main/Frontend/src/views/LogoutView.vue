<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useApplicationStore } from '@/stores/application.js';

const router = useRouter();
const { clearUserData } = useApplicationStore();

const loading = ref(false);

const onFormSubmit = () => {
    // Perform a logout by flushing user data stored in tab state (pinia) and local storage (browser).
    // REMEMBER: authentication is stateless.
    // That is, if users store a valid JWT they can use it until is expired.
    // We cannot actually perform a logout because JWT cannot be invalided.
    // A solution is to blacklist the JWT until is expired.
    loading.value = true;
    clearUserData();
    setTimeout(function () {}, 2000); // Simulate a remote request.
    router.push({ name: 'login' });
};
</script>

<template>
  <body>
  <div class="card">
    <div class="mb-4">
      <h1 class="fs-3">Logout</h1>
    </div>
    <div class="spinner-border" role="status" v-if="loading">
      <span class="visually-hidden">Loading...</span>
    </div>
    <form v-else>
      <button @click="onFormSubmit" type="submit" class="btn btn-primary">
        Logout
      </button>
    </form>
  </div>
  </body>
</template>

<style>
body {
  background: linear-gradient(to right, #7ed56f, #28b485);
}

.card {
  max-width: 400px;
  height:520px;
  margin: 0 auto;
  padding: 20px;
  background-color: #7b9b50;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  text-align: center;
}

h1 {
  font-size: 1.5rem;
  color: #f6f4f4;
  margin-bottom: 20px;
}

.spinner-border {
  margin-top: 20px;
  color: #007bff;
}

.btn-primary {
  background-color: #a60b10;
  color: #fff;
  padding: 10px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-primary:hover {
  background-color: #f10722;
}
</style>