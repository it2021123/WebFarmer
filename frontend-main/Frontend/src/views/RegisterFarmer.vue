<template>
  <body>
  <div class="card1">
    <h1>Register as Farmer</h1>
    <div class="card2">
    <form @submit.prevent="signUpFarmer">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" required />

      <label for="name">Name:</label>
      <input type="text" id="name" v-model="name" required />

      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required />

      <label for="birthDate">Birth Date:</label>
      <input type="date" id="birthDate" v-model="birthDate" required />

      <button type="submit" :disabled="loading">Sign Up</button>
    </form>

    <div v-if="error" class="error-message">{{ error }}</div>
    <div v-if="data" class="success-message">{{ data.message }}</div>
  </div></div></body>
</template>

<script setup>
import { ref } from 'vue';
import { useRemoteDataPOST } from '@/composables/useRemoteDataPOST.js';
import { useRouter } from 'vue-router';

const router = useRouter();
const username = ref('');
const name = ref('');
const password = ref('');
const birthDate = ref('');
const loading = ref(false);
const error = ref(null);
const data = ref(null);

const signUpFarmer = async () => {
  loading.value = true;
  error.value = null;

  const response = useRemoteDataPOST('http://localhost:3030/api/auth/farmerSignUp', false, {
    username: username.value,
    name: name.value,
    password: password.value,
    birthDate: birthDate.value,
  });

  if (response.error) {
    error.value = response.error;

    router.push({name: 'home'});
  } else {
    data.value = response.data;
    // Optionally, you can redirect to a different page after successful signup
    router.push({name: 'login'});
  }

  loading.value = false;
};
</script>
<style scoped>
body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f3f68a; /* Grayish background */
}

.card1 {
  max-width: 400px;
  width: 100%;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  background-color: #b5dc77; /* Light green background */
  transition: background-color 0.3s ease, box-shadow 0.3s ease;
}

.card1:hover {
  background-color: #add26b; /* Lighter green on hover */
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}

.card2 {
  background-color: #71933a; /* Darker green for card2 */
  text-align: center;
  padding: 15px;
  border-radius: 8px;
  margin-top: 15px;
}

label {
  display: block;
  margin-bottom: 8px; /* Slightly increased margin */
  font-weight: bold;
  color: #2a3f85; /* Dark blue color for heading */
}
h1{
  color: #415fc4; /* Dark blue color for heading */
}
input {
  width: 100%;
  padding: 10px; /* Slightly increased padding */
  margin-bottom: 15px; /* Increased bottom margin */
  font-size: 1rem;
  border: 1px solid #ced4da;
  border-radius: 4px;
}

button {
  background-color: #3f51b5;
  color: #fff;
  padding: 12px 18px; /* Slightly increased padding */
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.error-message {
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 4px;
  padding: 12px; /* Slightly increased padding */
  margin-bottom: 15px; /* Increased bottom margin */
}

.success-message {
  color: #155724;
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
  border-radius: 4px;
  padding: 12px; /* Slightly increased padding */
  margin-bottom: 15px; /* Increased bottom margin */
}

</style>
