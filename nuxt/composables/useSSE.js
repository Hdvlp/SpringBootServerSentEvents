import { onMounted, onBeforeUnmount, ref } from "vue";

export function useSSE() {
  const eventSource = ref(null);
  const events = ref([]);

  onMounted(() => {
    eventSource.value = new EventSource("http://localhost:8080/api/v1/sse/subscribe");


    eventSource.value.addEventListener("message", (event) => {
      console.log("[✅ Event received]", event.data);
      events.value.push(JSON.parse(event.data)); 
    });

    eventSource.value.addEventListener("order", (event) => {
      const orderResponse = ("[✅ Event received]", event.data);
      console.log(orderResponse);
      document.querySelector("#orders").innerHTML = document.querySelector("#orders").innerHTML + "<div>" + orderResponse + "</div>";
      events.value.push(JSON.parse(event.data)); 
    });

    eventSource.value.onopen = () => console.log("[🔗 SSE connection opened]");
    eventSource.value.onerror = (err) => console.warn("[❌ SSE error]", err);
  });

  onBeforeUnmount(() => {
    if (eventSource.value) {
      eventSource.value.close();
      console.log("[🔒 SSE connection closed]");
    }
  });

  return { events };
}