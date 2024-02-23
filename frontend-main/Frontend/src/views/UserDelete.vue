<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useRemoteDataDELETE } from '@/composables/useRemoteDataDELETE.js';

const loading = ref(false);
const router = useRouter();
const userIdFromPath = ref(null);

const onFormSubmit = async () => {
  loading.value = true;

  // Retrieve the id from the route params and set it to userIdFromPath
  userIdFromPath.value = router.currentRoute.value.params.id;

  // Log the userIdFromPath
  console.log('userIdFromPath:', userIdFromPath.value);

  // Construct the delete endpoint using the userIdFromPath
  const deleteEndpoint = `http://localhost:3030/api/Admin/users/delete-user/${userIdFromPath.value}`;

  // Use the delete function with the constructed endpoint
  useRemoteDataDELETE(deleteEndpoint, true);
  router.push({name: '/users'})
};
</script>

<template>
  <div class="body">
    <div class="container1">
<div class="card1">
          <div >
            <h1 class="fs-3">Delete User</h1>
          </div>
          <div>
            <h1>{{ userIdFromPath }}</h1>
          </div>
          <div class="spinner-border" role="status" v-if="loading">
            <span class="visually-hidden">Loading...</span>
          </div>
          <form v-else>
            <br>
            <button @click="onFormSubmit" type="button" class="btn btn-danger">
              Delete
            </button>
          </form>
        </div>
        </div>
  </div>
</template>
<style>
.body {
  background: linear-gradient(to right, #7ed56f, #28b485);

}

.container1 {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.card1 {
  width: 400px;
  height: 450px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  background-color: #add26b;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: box-shadow 0.3s ease;
}

.btn-danger {
  background-color: #dc3545;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
  margin-top: 20px;
}

.btn-danger:hover {
  background-color: #bd2130;
}

.spinner-border {
  margin-top: 20px;
  color: #131c3b;
}
</style>
