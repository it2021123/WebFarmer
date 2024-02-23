<template>
  <div>
    <h1>Edit Employee</h1>

    <form @submit.prevent="onFormSubmit">
      <label for="username">Username:</label>
      <input v-model="form.username" type="text" id="username" name="username" >

      <label for="name">Name:</label>
      <input v-model="form.name" type="text" id="name" name="name" >

      <!--<label for="birthdate">BirthDate:</label>
      <input v-model="form.birthdate" type="date" id="birthdate" name="birthdate" >-->

      <!--<label for="role">Role:</label>
      <input v-model="form.role" type="text" id="role" name="role" >-->

      <button type="submit" :disabled="loading">Update User</button>
    </form>

    <div v-if="data">User updated successfully: {{ data }}</div>
    <div v-if="error">Error updating user: {{ error.message }}</div>
    <div v-if="loading">Updating...</div>
  </div>
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

  const { data, error, loading } = useRemoteDataPUT(`http://localhost:3030/api/Admin/employees/update-employee/${userId}`, true, requestData);

  if (data) {
    console.log('User updated successfully:', data);

    await router.push({name: 'getEmployee', params: {employeeId: userId}})
  } else {
    console.log('Request Headers:', headers);
    console.error('Error updating user:', error.value);
  }
};
</script>