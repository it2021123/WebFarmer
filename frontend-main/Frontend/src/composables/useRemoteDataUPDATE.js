/*// useRemoteDataPut.js
import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

export async function useRemoteDataPut(url, auth, requestData) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(false);
    const updateSuccess = ref(false);
    const message = ref("");
    const store = useApplicationStore();

    const headers = {
        'Content-Type': 'application/json',
    };

    if (auth) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

    const requestOptions = {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(requestData),
    };

    try {
        loading.value = true;
        const response = await fetch(url, requestOptions);

        if (!response.ok) {
            throw new Error(`Network response was not ok (status: ${response.status})`);
        }

        const responseData = await response.json();
        data.value = responseData;
        updateSuccess.value = true;
        message.value = `User updated successfully: ${responseData.message || ""}`;
    } catch (err) {
        error.value = err;
        updateSuccess.value = false;
        message.value = `Error updating user: ${err.message || ""}`;
    } finally {
        loading.value = false;
    }

    return { data, error, loading, updateSuccess, message };
}*/



////////////////////////////////////////////////////////////////////
import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();

export function useRemoteDataPUT(url, auth, requestData) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(false);

    const headers = {
        'Content-Type': 'application/json'
    };

    if (auth === true) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

    const requestOptions = {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(requestData)
    };

    fetch(url, requestOptions)
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to fetch(PUT)');
            }
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
}
