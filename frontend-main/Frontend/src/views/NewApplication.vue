<!--<template>
  <div>
    <form @submit.prevent="submitForm">
      <label for="cooperativeName">Cooperative Name:</label>
      <input type="text" id="cooperativeName" v-model="applicationForm.agriculturalCooperativeName" required>

      <div>
        <button @click="addElement(applicationForm.members)" type="button">Add Member</button>
        <ul>
          <li v-for="(element, index) in applicationForm.members" :key="index">
            <input type="text" v-model="tempMembers[index]" placeholder="Member name">
            <button @click="removeElement(applicationForm.members, index)">Remove</button>
          </li>
        </ul>
      </div>

      <div>
        <button @click="addElement(applicationForm.products)" type="button">Add Product</button>
        <ul>
          <li v-for="(element, index) in applicationForm.products" :key="index">
            <input type="text" v-model="tempProducts[index]" placeholder="Product name">
            <button @click="removeElement(applicationForm.products, index)">Remove</button>
          </li>
        </ul>
      </div>

      <div>
        <button @click="addElement(applicationForm.cultivationAreas)" type="button">Add Cultivation Area</button>
        <ul>
          <li v-for="(element, index) in applicationForm.cultivationAreas" :key="index">
            <input type="text" v-model="tempCultivationAreas[index]" placeholder="Cultivation Area">
            <button @click="removeElement(applicationForm.cultivationAreas, index)">Remove</button>
          </li>
        </ul>
      </div>

      <button type="submit" :disabled="loading">Submit Application</button>
      <div v-if="loading">Submitting...</div>
      <div v-if="data">Form submitted successfully</div>
      <div v-if="error">Error submitting form: {{ error.message }}</div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRemoteDataPOST } from '@/composables/useRemoteDataPOST';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();

const applicationForm = ref({
  agriculturalCooperativeName: '',
  members: [],
  products: [],
  cultivationAreas: [],
  farmer: ''
});

const tempMembers = ref([]);
const tempProducts = ref([]);
const tempCultivationAreas = ref([]);

const addElement = (list) => {
  list.push('');
};

const removeElement = (list, index) => {
  list.splice(index, 1);
};

const submitForm = async () => {
  const requestData = {
    agriculturalCooperativeName: applicationForm.value.agriculturalCooperativeName,
    members: applicationForm.value.members,
    products: applicationForm.value.products,
    cultivationAreas: applicationForm.value.cultivationAreas,
    farmer: store.userData.username,
  };

  const { data, error, loading } = await useRemoteDataPOST('http://localhost:3030/api/Farmer/submitApplication', true, requestData);

  // Assuming data is not null, check if data contains the expected response
  if (data) {
    console.log('Form submitted successfully:', data);
  } else {
    console.error('Error submitting form:', error.value);
  }
};



</script>
-->
User
<template>

  <div class="container1">
    <div class="card1">
      <h1>New Argiculture Cooperative</h1>
      <form @submit.prevent="submitForm">
        <label for="cooperativeName">Cooperative Name:</label>
        <input type="text" id="cooperativeName" v-model="applicationForm.agriculturalCooperativeName" required>

        <div>
          <button @click="addElement(applicationForm.members)" type="button">Add Member</button>
          <ul>
            <li v-for="(element, index) in applicationForm.members" :key="index">
              <input type="text" v-model="tempMembers[index]" placeholder="Member name">
              <button @click="removeElement(applicationForm.members, index)">Remove</button>
            </li>
          </ul>
        </div>

        <div>
          <button @click="addElement(applicationForm.products)" type="button">Add Product</button>
          <ul>
            <li v-for="(element, index) in applicationForm.products" :key="index">
              <input type="text" v-model="tempProducts[index]" placeholder="Product name">
              <button @click="removeElement(applicationForm.products, index)">Remove</button>
            </li>
          </ul>
        </div>

        <div>
          <button @click="addElement(applicationForm.cultivation_area)" type="button">Add Cultivation Area</button>
          <ul>
            <li v-for="(element, index) in applicationForm.cultivation_area" :key="index">
              <input type="text" v-model="tempCultivation_area[index]" placeholder="Cultivation Area">
              <button @click="removeElement(applicationForm.cultivation_area, index)">Remove</button>
            </li>
          </ul>
        </div>

        <button type="submit" :disabled="loading">Submit Application</button>
        <div v-if="loading">Submitting...</div>
        <div v-if="data">Form submitted successfully</div>
        <div v-if="error">Error submitting form: {{ error.message }}</div>
      </form>
    </div>
  </div>

</template>

<script setup>
import { ref } from 'vue';
import { useRemoteDataPUT } from '@/composables/useRemoteDataUPDATE.js';
import { useApplicationStore } from '@/stores/application.js';
import { useRouter } from 'vue-router';

const router = useRouter();
const store = useApplicationStore();

const applicationForm = ref({
  agriculturalCooperativeName: '',
  members: [],
  products: [],
  cultivation_area: [],
  farmer: ''
});

const tempMembers = ref([]);
const tempProducts = ref([]);
const tempCultivation_area = ref([]);

const addElement = (list) => {
  list.push('');
};

const removeElement = (list, index) => {
  list.splice(index, 1);
};

const submitForm = async () => {
  // Log form data before API call
  console.log('Form data before submission:', applicationForm.value);


  const requestData = {
    agriculturalCooperativeName: applicationForm.value.agriculturalCooperativeName,
    members: tempMembers.value.slice(), // Use tempMembers instead of applicationForm.value.members
    products: tempProducts.value.slice(), // Use tempProducts instead of applicationForm.value.products
    cultivation_area: tempCultivation_area.value.slice()/*tempCultivationAreas.value.slice()*/, // Use tempCultivationAreas instead of applicationForm.value.cultivationAreas
    farmer: store.userData.username,
  };




  const { data, error, loading } =  useRemoteDataPUT('http://localhost:3030/api/Farmer/submitApplication', true, requestData);

  // Assuming data is not null, check if data contains the expected response
  if (data) {
    // Log form data after API call
    console.log('Form data after submission:', data.cultivationAreas);
    console.log('Form submitted successfully:', data);
    router.push({name: 'home'});
  } else {
    console.error('Error submitting form:', error.value);
  }
};
</script>
<style>
.container1 {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to right, #7ed56f, #28b485);
}

.card1 {

  width:900px;
  height:650px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  background-color: #b5dc77; /* Light green background */
  transition: background-color 0.3s ease, box-shadow 0.3s ease;
}
ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-top: 10px;
  display: flex;
  align-items: center;
}

li input {
  margin-right: 10px;
}

button {
  background-color: #47688a;
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #17500a;
}


</style>