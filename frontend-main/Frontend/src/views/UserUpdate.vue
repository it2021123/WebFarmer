<template>
<body>
  <div class="edit-user">
    <h1>Edit User</h1>

    <form @submit.prevent="onFormSubmit">
      <label for="username">Username:</label>
      <input v-model="form.username" type="text" id="username" name="username" >

      <label for="name">Name:</label>
      <input v-model="form.name" type="text" id="name" name="name" >

     <!-- <label for="birthdate">BirthDate:</label>
      <input v-model="form.birthdate" type="date" id="birthdate" name="birthdate" >-->

      <!--<label for="role">Role:</label>
      <input v-model="form.role" type="text" id="role" name="role" >-->

      <button type="submit" :disabled="loading">Update User</button>
    </form>

    <div v-if="data">User updated successfully: {{ data }}</div>
    <div v-if="error">Error updating user: {{ error.message }}</div>
    <div v-if="loading">Updating...</div>
  </div>
</body>
</template>

<script setup>

import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useRemoteDataPUT } from '@/composables/useRemoteDataUPDATE.js';

const router = useRouter();
const userId = router.currentRoute.value.params.id;

const form = ref({
  // Your form fields here
});

const onFormSubmit = async () => {
  const requestData = {
    // Extract data from your form
    username: form.value.username,
    name: form.value.name,
    //birthdate: form.value.birthdate,
    //role: form.value.role
  };

  const { data, error, loading } = useRemoteDataPUT(`http://localhost:3030/api/Admin/users/update-user/${userId}`, true, requestData);

  if (data) {
    console.log('User updated successfully:', data)

    // Redirect to the user details page after successful update
    await router.push({name: 'user', params: {userId: userId}});
  } else {
    console.log('Request Headers:', headers);
    console.error('Error updating user:', error.value);
  }
};
</script>
<style scoped>
body{
  height:600px;
  background: linear-gradient(to right, #7ed56f, #28b485);

}

.edit-user {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #b5dc77;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

h1 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
  box-sizing: border-box;
}

button {
  background-color: #1f4618;
  color: #fff;
  padding: 10px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

</style>