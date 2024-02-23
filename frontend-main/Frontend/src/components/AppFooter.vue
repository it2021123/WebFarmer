<template>
  <footer>
    <p>&copy; 2024 Eagrotis</p>
  </footer>
</template>


<style>
footer {
  background-color: #4caf50; /* Dark gray background */
  color: white; /* White text for contrast */
  text-align: center;
  pading:10px;
  position: fixed;
  bottom: 0;
  width: 100%;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5); /* Σκίαση */
  transition: background-color 0.3s ease; /* Αλλαγή χρώματος με smooth transition */

}

footer:hover {
  cursor: pointer; /* Change cursor to pointer on hover */
}

/* Additional color for the text */
footer p {
  color: #bfbfbf; /* Light gray text */
}


</style>

<script setup>
</script>
