
<script setup>
import { useSSE } from '~/composables/useSSE';


const { events } = useSSE();
</script>

<template>
<div>
  	<div v-for="event in events" :key="event.id">
   	 	{{ event.message }}
   
  	</div>
	<div id="orders"></div>

</div>
</template>