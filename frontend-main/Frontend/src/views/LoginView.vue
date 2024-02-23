<script setup>
// @EXERCISE: If user is authenticated redirect to the requested URL.
// @EXERCISE: If user is not authenticated, keep the requested URL and after a successful authentication redirect to the requested resource.
import { onBeforeMount, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useApplicationStore } from '@/stores/application.js';

const router = useRouter();
const { setUserData, persistUserData, isAuthenticated} = useApplicationStore();

const loading = ref(false);
const credentials = ref({
    username: '',
    password: ''
});
const authenticationFailed = ref(false);

const onFormSubmit = () => {
    loading.value = true;
    authenticationFailed.value = false;

    fetch('http://localhost:3030/api/auth/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials.value)
    })
        .then((response) => {
            if (response.ok) {
                response.json().then((data) => {
                    setUserData(data);
                    persistUserData();
                    router.push({ name: 'home' });
                });
            } else {
                authenticationFailed.value = true;
            }
        })
        .catch((err) => {
            console.warn(err);
            authenticationFailed.value = true;
        })
        .finally(() => {
            loading.value = false;
        });
};

onBeforeMount(() => {
    if (isAuthenticated === true) {
        router.push({ name: 'home' });
    }
});
</script>

<template>
    <div class="body">
        <div class="card1">
          <h1 class="fs-3">  Login</h1>
            <div class="row py-4 px-3">
                <div class="card2">
                    <div class="spinner-border" role="status" v-if="loading">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                    <form v-else>
                        <div class="mb-2" v-if="authenticationFailed">
                            <div class="alert alert-danger" role="alert">
                                Authentication failed!
                            </div>
                        </div>
                        <div class="mb-2">
                            <label for="usernameFormControl" class="form-label mb-1"
                                >Username</label
                            >
                            <input
                                v-model="credentials.username"
                                type="text"
                                class="form-control"
                                id="usernameFormControl"
                            />
                        </div>
                        <div class="mb-2">
                            <label for="passwordFormControl" class="form-label mb-1"
                                >Password</label
                            >
                            <input
                                v-model="credentials.password"
                                type="password"
                                class="form-control"
                                id="passwordFormControl"
                            />
                        </div>
                        <button @click="onFormSubmit" type="submit" class="btn btn-primary">
                            Login
                            <span class="fst-italic" v-if="credentials.username"
                                >as {{ credentials.username }}</span
                            >
                        </button>
                    </form>
                    <div>
                      <p>Are you a farmer but haven't register already? Click <RouterLink :to="{name: 'signupFarmer'}" class="Routerlink">here</RouterLink></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>

.body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to right, #7ed56f, #28b485);

}

.card1 {
  max-width: 400px;
  width: 100%;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  background-color: #b5dc77; /* Light green background */
  transition: box-shadow 0.3s ease;
}

.card1:hover {
  background-color: #add26b;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}

.card2 {
  background-color: #71933a; /* Darker green for card2 */
  text-align: center;
}

.fs-3 {
  font-size: 1.5rem;
  color: #2a3f85; /* Dark blue color for heading */
}

.spinner-border {
  display: inline-block;
  width: 2rem;
  height: 2rem;
  border-width: 0.25em;
  border-color: #2a3f85 transparent #2a3f85 transparent;
  margin-right: 5px;
}

.mb-2 {
  margin-bottom: 10px;
}

.form-label {
  font-size: 0.875rem;
  font-weight: bold;
  color: #2a3f85; /* Dark blue color for form labels */
}

.form-control {
  width: 100%;
  padding: 8px;
  font-size: 1rem;
  border: 1px solid #2a3f85; /* Dark blue border for form controls */
  border-radius: 4px;
  margin-bottom: 10px;
}

.btn-primary {
  background-color: #2a3f85;
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #1c2b4a; /* Darker blue on hover */
}

.fst-italic {
  font-style: italic;
  color: #ffffff; /* White color for italic text */
}

.alert-danger {
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 4px;
  padding: 8px;
  margin-bottom: 10px;
}

.Routerlink {
  color: #2a3f85;
  text-decoration: none;
}

.Routerlink:hover {
  text-decoration: underline;
  color: #439af1; /* Darker blue on hover for links */
}


</style>