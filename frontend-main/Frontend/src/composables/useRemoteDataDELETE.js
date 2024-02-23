/*
import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();

export function useRemoteDataDELETE(url, auth) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(false);

    const headers = {
        'Content-Type': 'application/json'
    };

    if (auth === true) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

    loading.value = true;
    fetch(url, {
        method: 'DELETE',
        headers: headers
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`Network response was not ok (status: ${response.status})`);
            }
            return response.json();
        })
        .then((responseData) => {
            data.value = responseData;
        })
        .catch((err) => {
            error.value = err;
        })
        .finally(() => {
            loading.value = false;
        });

    return { data, error, loading };
}*/
import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();

export function useRemoteDataDELETE(url, auth) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(false);

    const headers = {
        'Content-Type': 'application/json',
    };

    if (auth === true) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

    const requestOptions = {
        method: 'DELETE',
        headers: headers,
        credentials: 'include'
    };

    loading.value = true;

    fetch(url, requestOptions)
        .then(async (response) => {
            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(`Failed to fetch(DELETE) - Status: ${response.status}, Message: ${errorMessage}`);
            }
            return response.json();
        })
        .then((responseData) => {
            data.value = responseData;
        })
        .catch((err) => {
            error.value = err.message;
        })
        .finally(() => {
            loading.value = false;
        });

    return { data, error, loading };
}



